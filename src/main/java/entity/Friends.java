package entity;

public class Friends extends FriendsKey {
    private String friendsnameuser;

    private String friendsnamedoc;

    public Friends(Integer userid, Integer doctorid, String friendsnameuser, String friendsnamedoc) {
        super(userid, doctorid);
        this.friendsnameuser = friendsnameuser;
        this.friendsnamedoc = friendsnamedoc;
    }

    public Friends() {
        super();
    }

    public String getFriendsnameuser() {
        return friendsnameuser;
    }

    public void setFriendsnameuser(String friendsnameuser) {
        this.friendsnameuser = friendsnameuser == null ? null : friendsnameuser.trim();
    }

    public String getFriendsnamedoc() {
        return friendsnamedoc;
    }

    public void setFriendsnamedoc(String friendsnamedoc) {
        this.friendsnamedoc = friendsnamedoc == null ? null : friendsnamedoc.trim();
    }
}