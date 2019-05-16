package entity;

public class Doctor {
    private Integer docid;

    private String username;

    private String sex;

    private Integer age;

    private Integer workyears;

    private String workplace;

    private String address;

    private String accountuser;

    private String passworduser;

    private String questionforreset;

    public Doctor(Integer docid, String username, String sex, Integer age, Integer workyears, String workplace, String address, String accountuser, String passworduser, String questionforreset) {
        this.docid = docid;
        this.username = username;
        this.sex = sex;
        this.age = age;
        this.workyears = workyears;
        this.workplace = workplace;
        this.address = address;
        this.accountuser = accountuser;
        this.passworduser = passworduser;
        this.questionforreset = questionforreset;
    }

    public Doctor() {
        super();
    }

    public Integer getDocid() {
        return docid;
    }

    public void setDocid(Integer docid) {
        this.docid = docid;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getWorkyears() {
        return workyears;
    }

    public void setWorkyears(Integer workyears) {
        this.workyears = workyears;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace == null ? null : workplace.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
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

    public String getQuestionforreset() {
        return questionforreset;
    }

    public void setQuestionforreset(String questionforreset) {
        this.questionforreset = questionforreset == null ? null : questionforreset.trim();
    }
}