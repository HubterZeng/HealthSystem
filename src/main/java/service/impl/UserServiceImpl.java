package service.impl;

import dao.ConsHistoryMapper;
import dao.PatientMapper;
import entity.ConsHistory;
import entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserService;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    PatientMapper patientMapper;
    @Autowired
    ConsHistoryMapper consHistoryMapper;
    @Override
    public boolean register(Patient patient) {
        if(patientMapper.insert(patient)==1)
            return true;
        return false;
    }
    @Override
    public boolean login(String userAccount, String password) {
        Patient patient =patientMapper.selectByAccount(userAccount);
        if(patient!=null){
            if(patient.getPassworduser().equals(password)){
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean resetPassword(String userAccount) {
        return false;
    }

    @Override
    public Patient getPatientInfo(String userAccount) {
        return patientMapper.selectByAccount(userAccount);

    }

    @Override
    public List<ConsHistory> consuleHistory(String userName) {
        return consHistoryMapper.selectByUserId(patientMapper.selectByAccount(userName).getUserid());
    }
    @Override
    public boolean addConsule(ConsHistory consHistory){
        if(consHistoryMapper.insert(consHistory)==1)
            return true;
        return false;
    }


}
