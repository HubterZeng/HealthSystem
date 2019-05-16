package service;
import entity.ConsHistory;
import entity.ConsTreatment;
import entity.Doctor;
import entity.ReadHistory;

import javax.print.Doc;
import java.util.List;

public interface DoctorService {
    public List<ConsHistory> readHistory(String userAccount);
    public boolean docLogin(String userAccount,String password);
    public boolean register(Doctor doctor);
    public boolean resetPassword(String userAccount);
    public Doctor getDocInfo(String userAccount);
    public boolean addConsTreatment(ConsTreatment consTreatment);
    public ConsTreatment getConsTreatment(int consid);
}
