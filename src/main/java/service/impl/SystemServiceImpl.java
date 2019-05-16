package service.impl;

import dao.ConsHistoryMapper;
import dao.DoctorMapper;
import dao.FriendsMapper;
import dao.PatientMapper;
import entity.ConsHistory;
import entity.Doctor;
import entity.Friends;
import entity.Patient;
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

}
