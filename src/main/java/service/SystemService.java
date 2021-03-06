package service;

import entity.ConsHistory;
import entity.Doctor;
import entity.Friends;
import entity.Patient;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SystemService {
   public boolean uniqueAcCheck(String userAccount);
   public List<Friends> getFriends(String userAccount);
   public boolean addFriends(int userid ,String userAccount);
   public Doctor findDoctor(String userAccount);
   public Patient findPatient(String userAccount);
   public List<ConsHistory> findAllQuestions();
   public List< MessageExt > consume(String toId);
   public boolean produce(String fromId,String toId,String news);

}
