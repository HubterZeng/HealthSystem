package entity;

public class FriendsKey {
    private Integer userid;

    private Integer doctorid;

    public FriendsKey(Integer userid, Integer doctorid) {
        this.userid = userid;
        this.doctorid = doctorid;
    }

    public FriendsKey() {
        super();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(Integer doctorid) {
        this.doctorid = doctorid;
    }
}