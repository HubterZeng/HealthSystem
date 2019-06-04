package controller;
import Utils.FormToBean;
import com.alibaba.fastjson.JSON;
import entity.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.DoctorService;
import service.SystemService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/system")
public class HealthController {
    @Autowired
    private  UserService userService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private  SystemService systemService;
    /*前端页面添加userID,UserAccount 唯一性验证按钮*/
    @RequestMapping(value="/uniqueCheck")
    @ResponseBody
    public String uniqueCheckAccount(@RequestBody String json1){
        Map maps = (Map)JSON.parse(json1);
        String userAccount=(String)maps.get("accountUser");
        Patient patient=userService.getPatientInfo(userAccount);
        Doctor doctor=doctorService.getDocInfo(userAccount);
        Map<String ,String> map=new HashMap<>();

        if(patient!=null||doctor!=null){
            map.put("status","false");
        }
        map.put("status","true");
        return  JSON.toJSONString(map);
    }
    /*注册只返回成功和失败*/
    @RequestMapping(value="/docRegister")
    @ResponseBody
    public String docRegister(HttpServletRequest httpServletRequest,HttpServletResponse res){
        Doctor doctor=FormToBean.requestToBean(httpServletRequest, Doctor.class);
        Map<String,Boolean> map=new HashMap<String ,Boolean>();
        map.put("states",doctorService.register(doctor));
        String json= JSON.toJSONString(map);
        return json;
    }
    @RequestMapping(value="/patientRegister")
    @ResponseBody
    public String patientRegister(HttpServletRequest httpServletRequest,HttpServletResponse res){
        Patient patient=FormToBean.requestToBean(httpServletRequest, Patient.class);
        Map<String,Boolean> map=new HashMap<String ,Boolean>();
        map.put("states",userService.register(patient));
        String json= JSON.toJSONString(map);
        return json;
    }
    @RequestMapping(value="/login")
    @ResponseBody
    public String login(@RequestBody String json1 , HttpServletResponse res, HttpServletRequest req){
        //  contenttype
        Map maps = (Map)JSON.parse(json1);
        String userName=(String)maps.get("userName");
        String password= (String) maps.get("password");
        // res.setHeader("Access-Control-Allow-Origin","*");
        boolean status;//登录是否成功
        boolean bool_doc,bool_patient;
        bool_doc=doctorService.docLogin(userName,password);
        bool_patient=userService.login(userName, password);
        status= bool_doc||bool_patient;
        Map<String ,String> map=new HashMap<>();
        if(status){
            map.put("loginStatus","true");
            HttpSession session=req.getSession();
          if(bool_doc){
              map.put("userType","doctor");
              session.setAttribute("info",doctorService.getDocInfo(userName));
              session.setAttribute("userAccount",doctorService.getDocInfo(userName).getAccountuser());
              session.setAttribute("userId",doctorService.getDocInfo(userName).getDocid());
          } else{
              session.setAttribute("info",userService.getPatientInfo(userName));
              session.setAttribute("userAccount",userService.getPatientInfo(userName).getAccountuser());
              session.setAttribute("userId",userService.getPatientInfo(userName).getUserid());
              map.put("userType","patient");
          }
        }
        String json= JSON.toJSONString(map);
        return json;
    }
    /*登录后，根据Session获取自己的信息*/
    @RequestMapping("/getUserInfo")
    @ResponseBody
    public String getUserInfo(HttpServletRequest req){
       return  JSON.toJSONString(req.getSession(false).getAttribute("info")) ;
    }
    /*登录后，根据Session获取自己的朋友信息*/
    @RequestMapping("/getFriends")
    @ResponseBody
    public String getFriends(HttpServletRequest req){
        List<Friends> list=new ArrayList<>();
        list=systemService.getFriends(req.getSession().getAttribute("userAccount").toString());
        return JSON.toJSONString(list);
    }
    @RequestMapping(value="/addConsule")
    @ResponseBody
    /*表单要是一个CoonsHistory的实体*/
    public String addConsule(HttpServletRequest req){
        ConsHistory consHistory=FormToBean.requestToBean(req, ConsHistory.class);
        Map<String,String > map=new HashMap<>();
        String str;
        if(userService.addConsule(consHistory))
            str="true";
        else  str="false";
        map.put("status",str);
        return JSON.toJSONString(map);
    }
    /*病人查询自己的咨询历史*/
    @RequestMapping(value="/consuleHistory")
    @ResponseBody
    public String consuleHistory(HttpServletRequest req){
        List<ConsHistory> consHistoryList=new ArrayList<>();
        String userAccount=req.getSession().getAttribute("userAccount").toString();
        userService.consuleHistory(userAccount).forEach(
                e-> consHistoryList.add(e)
                );
        String json=JSON.toJSONString(consHistoryList);
        return  json;
    }

    @RequestMapping(value="/addFriends")
    @ResponseBody
    public String addFriends(@RequestBody String userAccount1){

        Map maps = (Map)JSON.parse(userAccount1);
        String userAccount=(String)maps.get("userAccount");
        int userId=(Integer)maps.get("userid");
        Map<String,String > map=new HashMap<>();
        String str=(systemService.addFriends(userId,userAccount)?"true":"false");
        map.put("status",str);
        return JSON.toJSONString(map);
    }
    @RequestMapping(value="/findDoctor")
    @ResponseBody
    public String findDoctor(@RequestBody String userAccount1){
        Map maps = (Map)JSON.parse(userAccount1);
        String userAccount=(String)maps.get("userAccount");
        Doctor doctor=systemService.findDoctor(userAccount);
        if(doctor==null)
            return null;
        return JSON.toJSONString(doctor);
    }
    @RequestMapping(value="/findPatient")
    @ResponseBody
    public String findPatient(@RequestBody  String userAccount1){
        Map maps = (Map)JSON.parse(userAccount1);
        String userAccount=(String)maps.get("userAccount");
        Patient patient=systemService.findPatient(userAccount);

        if(patient==null)
            return null;
        return JSON.toJSONString(patient);
    }

    @RequestMapping(value="/forgetPassword")
    @ResponseBody
    public String forgetPassword( @RequestBody String json1){
        Map maps = (Map)JSON.parse(json1);
        String userName=(String)maps.get("accountUser");
        String password= (String) maps.get("password");
        String questionforReset=(String )maps.get("questionforReset");
        Patient patient=userService.getPatientInfo(userName);
        Doctor doctor=doctorService.getDocInfo(userName);
        Map<String ,String> map=new HashMap<>();

        if(patient!=null){
            if(patient.getQuestionforreset().equals(questionforReset)){
                map.put("status","true");
            }else   map.put("status","false");
        }else if(doctor!=null){
            if(doctor.getQuestionforreset().equals(questionforReset))
                map.put("status","true");
            else map.put("status","false");
        }else{
            map.put("status","false");
        }

        return  JSON.toJSONString(map);


    }

    @RequestMapping(value="/lookQuestion")
    @ResponseBody
    public String lookQustion(){
        List<ConsHistory> consHistoryList=new ArrayList<>();
        consHistoryList=systemService.findAllQuestions();
        return JSON.toJSONString(consHistoryList);
    }
    @RequestMapping(value="/addTreatment")
    @ResponseBody
    public String addTreatment( HttpServletRequest req){
        ConsTreatment consTreatment=FormToBean.requestToBean(req, ConsTreatment.class);
        Map<String,String > map=new HashMap<>();
        String str=doctorService.addConsTreatment(consTreatment)?"true":"false";
        map.put("status",str);
        return JSON.toJSONString(map);
    }
    /*医生查看自己的阅读历史*/
    @RequestMapping(value="/readHistory")
    @ResponseBody
    public String readHistory( HttpServletRequest req){
        //返回的数据有误  应该返回阅读过的病例
        List<ConsHistory> consHistoryList=new ArrayList<>();
        String userAccount=req.getSession().getAttribute("userAccount").toString();
        doctorService.readHistory(userAccount).forEach(
                e-> consHistoryList.add(e)
        );
        String json=JSON.toJSONString(consHistoryList);
        return  json;
    }

    @RequestMapping(value="/getMyTreatment")
    @ResponseBody
    public String getMyTreatment(  @RequestBody String json1){
        Map maps = (Map)JSON.parse(json1);
        int consId=(Integer) maps.get("consId");
        String json=JSON.toJSONString(doctorService.getConsTreatment(consId));
        return  json;
    }
  //将id变为用户账号
    @RequestMapping(value="/sendMessage")
    @ResponseBody
    public String sendMessage( @RequestBody String  json1 ){
        Map maps = (Map)JSON.parse(json1);
        String fromId=(String)maps.get("fromId");
        String toId= (String) maps.get("toId");
        String news=(String) maps.get("news");
        Map <String ,String >map=new HashMap<>();
        if(systemService.produce(fromId,toId,news)){
            map.put("status","true");
            return JSON.toJSONString(map);
        }
        map.put("status","false");
        return JSON.toJSONString(map);
        //json1 通信双方的账号 fromId,toId
    }

    @RequestMapping(value="/getMessage")
    @ResponseBody
    public String getMessage(@RequestBody String toId){
        return JSON.toJSONString(systemService.consume(toId));
    }
}

