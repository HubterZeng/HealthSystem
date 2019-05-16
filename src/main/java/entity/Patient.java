package entity;

public class Patient {
    private Integer userid;

    private String username;

    private String sex;

    private String accountuser;

    private String passworduser;

    private String address;

    private String userPhone;

    private Integer age;

    private String questionforreset;

    public Patient(Integer userid, String username, String sex, String accountuser, String passworduser, String address, String userPhone, Integer age, String questionforreset) {
        this.userid = userid;
        this.username = username;
        this.sex = sex;
        this.accountuser = accountuser;
        this.passworduser = passworduser;
        this.address = address;
        this.userPhone = userPhone;
        this.age = age;
        this.questionforreset = questionforreset;
    }

    public Patient() {
        super();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getAccountuser() {
        return accountuser;
    }

    public void setAccountuser(String accountuser) {
        this.accountuser = accountuser == null ? null : accountuser.trim();
    }

    public String getPassworduser() {
        return passworduser;
    }

    public void setPassworduser(String passworduser) {
        this.passworduser = passworduser == null ? null : passworduser.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getQuestionforreset() {
        return questionforreset;
    }

    public void setQuestionforreset(String questionforreset) {
        this.questionforreset = questionforreset == null ? null : questionforreset.trim();
    }
}