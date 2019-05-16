package entity;

public class ConsHistory {
    private Integer id;

    private Integer userid;

    private String qustitle;

    private String quscontent;

    public ConsHistory(Integer id, Integer userid, String qustitle, String quscontent) {
        this.id = id;
        this.userid = userid;
        this.qustitle = qustitle;
        this.quscontent = quscontent;
    }

    public ConsHistory() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getQustitle() {
        return qustitle;
    }

    public void setQustitle(String qustitle) {
        this.qustitle = qustitle == null ? null : qustitle.trim();
    }

    public String getQuscontent() {
        return quscontent;
    }

    public void setQuscontent(String quscontent) {
        this.quscontent = quscontent == null ? null : quscontent.trim();
    }
}