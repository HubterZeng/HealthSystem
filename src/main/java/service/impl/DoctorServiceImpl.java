package service.impl;

import dao.ConsHistoryMapper;
import dao.ConsTreatmentMapper;
import dao.DoctorMapper;
import dao.ReadHistoryMapper;
import entity.ConsHistory;
import entity.ConsTreatment;
import entity.Doctor;
import entity.ReadHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.DoctorService;
import java.util.ArrayList;
import java.util.List;
@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    DoctorMapper doctorMapper;
    @Autowired
    ReadHistoryMapper readHistoryMapper;
    @Autowired
    ConsTreatmentMapper consTreatmentMapper;
    @Autowired
    ConsHistoryMapper consHistoryMapper;

    @Override
    public boolean register(Doctor doctor) {
        if(doctorMapper.insert(doctor)==1)
            return true;
        return false;
    }
    @Override
    public boolean docLogin(String userName, String password) {
        Doctor doctor=doctorMapper.selectByUserAccount(userName);
        if(doctor!=null){
            doctor.getPassworduser().equals(password);
            return true;
        }
        return false;
    }

    @Override
    public boolean resetPassword(String userAccount) {
        return false;
    }

    @Override
    public Doctor getDocInfo(String userAccount) {
       return doctorMapper.selectByUserAccount(userAccount);
    }

    @Override
    public List<ConsHistory> readHistory(String account) {
        List<ReadHistory> list =new ArrayList<>();
        List<ConsHistory> consHistoryList=new ArrayList<>();
        Doctor doctor;
        if((doctor = doctorMapper.selectByUserAccount(account))!=null){
            list.addAll(readHistoryMapper.selectByDocId(doctor.getDocid()));
            list.forEach(
                    e->consHistoryList.add(consHistoryMapper.selectByPrimaryKey(e.getConsId()))
            );
        }
        return consHistoryList;
    }
    @Override
    public boolean addConsTreatment(ConsTreatment consTreatment){
        if( consTreatmentMapper.insert(consTreatment)==1){
            ReadHistory readHistory=new ReadHistory();
            readHistory.setConsId(consTreatment.getConsId());
            readHistory.setDocid(consTreatment.getDocid());
            readHistoryMapper.insert(readHistory);
            return true;
        }

        return false;
    }

    @Override
    public ConsTreatment getConsTreatment(int consid) {
        return  consTreatmentMapper.selectByPrimaryKey(consid);
    }
}
