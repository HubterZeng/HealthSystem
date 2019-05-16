package service;

import entity.ConsHistory;
import entity.Patient;

import java.util.List;

public interface UserService {
    public List<ConsHistory> consuleHistory(String userAccount);
    public boolean login(String userAccount,String password);
    public boolean register(Patient patient);
    public boolean resetPassword(String userAccount);
    public Patient getPatientInfo(String userAccount);
    public boolean addConsule(ConsHistory consHistory);

}

