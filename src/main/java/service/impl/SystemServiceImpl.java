package service.impl;

import dao.*;
import entity.ConsHistory;
import entity.Doctor;
import entity.Friends;
import entity.Patient;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.SystemService;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

@Service
public class SystemServiceImpl implements SystemService {
    @Autowired
    DoctorMapper doctorMapper;
    @Autowired
    PatientMapper patientMapper;
    @Autowired
    FriendsMapper friendsMapper;
    @Autowired
    ConsHistoryMapper consHistoryMapper;
    @Autowired
    MessageMapper messageMapper;

    @Override
    public boolean uniqueAcCheck(String userAccount) {
        return false;
    }

    @Override
    public List<Friends> getFriends(String userAccount) {
        List<Friends> list=new ArrayList<>();
        Doctor doctor;
        Patient patient;
        if((doctor=doctorMapper.selectByUserAccount(userAccount))!=null){
            list.addAll(friendsMapper.selectByDocId(doctor.getDocid()));
        }
        if((patient=patientMapper.selectByAccount(userAccount))!=null) {
            list.addAll( friendsMapper.selectByUserId(patient.getUserid()));
        }
        return list;
    }

    @Override
    public boolean addFriends(int userid,String userAccount){
        Doctor doctor;
        Patient patient;
        //病人添加医生
        if((doctor=doctorMapper.selectByUserAccount(userAccount))!=null){
            String userName=patientMapper.selectByPrimaryKey(userid).getUsername();
            Friends friends=new Friends();
            friends.setFriendsnamedoc(doctor.getUsername());
            friends.setFriendsnameuser(userName);
            friends.setDoctorid(doctor.getDocid());
            friends.setUserid(userid);
            if(friendsMapper.insert(friends)==1)
                return true;
        }
        //医生添加病人
        if((patient=patientMapper.selectByAccount(userAccount))!=null){
            String userName=doctorMapper.selectByPrimaryKey(userid).getUsername();
            Friends friends=new Friends();
            friends.setFriendsnamedoc(userName);
            friends.setFriendsnameuser(patient.getUsername());
            friends.setDoctorid(userid);
            friends.setUserid(patient.getUserid());
            if(friendsMapper.insert(friends)==1)
                return true;
        }
        return false;
    }

    @Override
    public Doctor findDoctor(String userAccount) {
        Doctor doctor=doctorMapper.selectByUserAccount(userAccount);
        if(doctor==null)
             return null;
        return doctor;
    }

    @Override
    public Patient findPatient(String userAccount) {
        Patient patient=patientMapper.selectByAccount(userAccount);
        if (patient==null)
           return null;
        return patient;
    }

    @Override
    public List<ConsHistory> findAllQuestions() {
         return  consHistoryMapper.selectAll();
    }

    @Override
    public List<MessageExt> consume( String toId) {
        DefaultMQPushConsumer consumer =
                new DefaultMQPushConsumer(toId);
        consumer.setNamesrvAddr("47.102.198.100:9876");
        consumer.setVipChannelEnabled(false);
        List<MessageExt> messageList=new ArrayList<>();
        try{
            consumer.subscribe(toId,"*");
            consumer.setConsumeFromWhere(
                    ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext Context) {
                    messageList.addAll(list);
//                                                     Message msg = list.get(0);
//                                                     String topic = msg.getTopic();
//                                                     byte[] body = msg.getBody();
//                                                     String keys = msg.getKeys();
//                                                     System.out.println("keys = " + keys);
//                                                     String tags = msg.getTags();
//                                                     System.out.println("tags = " + tags);
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            consumer.start();
        }catch (Exception e){
            e.printStackTrace();
        }
        consumer.shutdown();
        return messageList;
    }
    @Override
    public boolean produce(String fromId, String toId,String news) {
        boolean isOK=false;
        DefaultMQProducer producer = new DefaultMQProducer("Producer");
        producer.setNamesrvAddr("47.102.198.100:9876");
        producer.setVipChannelEnabled(false);
        try {
            producer.start();
            Message msg = new Message(toId,
                    fromId, "1",
                    news.getBytes());
            SendResult result = producer.send(msg);
            if(result.getSendStatus().equals("SEND_OK")){
               isOK=true;
            }
            entity.Message record=new entity.Message();
            record.setFromid(fromId);
            record.setToid(toId);
            record.setMessagedata(news);
            record.setIsread(true);
            messageMapper.insert(record);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            producer.shutdown();
        }
        return isOK;
    }



}
