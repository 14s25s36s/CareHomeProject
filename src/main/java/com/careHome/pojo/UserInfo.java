package com.careHome.pojo;

public class UserInfo {
    private Integer uid;
    private String uname;
    private String usex;
    private Integer uage;
    private String uaddress;
    private Integer ustate;
    private Integer aid;
    private Integer permissions;
    private Integer deleted;

    @Override
    public String toString() {
        return "UserInfo{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", usex='" + usex + '\'' +
                ", uage=" + uage +
                ", uaddress='" + uaddress + '\'' +
                ", ustate=" + ustate +
                ", aid=" + aid +
                ", permissions=" + permissions +
                ", deleted=" + deleted +
                '}';
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public UserInfo(Integer uid, String uname, String usex, Integer uage, String uaddress, Integer ustate, Integer aid, Integer permissions, Integer deleted) {
        this.uid = uid;
        this.uname = uname;
        this.usex = usex;
        this.uage = uage;
        this.uaddress = uaddress;
        this.ustate = ustate;
        this.aid = aid;
        this.permissions = permissions;
        this.deleted = deleted;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUsex() {
        return usex;
    }

    public void setUsex(String usex) {
        this.usex = usex;
    }

    public Integer getUage() {
        return uage;
    }

    public void setUage(Integer uage) {
        this.uage = uage;
    }

    public String getUaddress() {
        return uaddress;
    }

    public void setUaddress(String uaddress) {
        this.uaddress = uaddress;
    }

    public Integer getUstate() {
        return ustate;
    }

    public void setUstate(Integer ustate) {
        this.ustate = ustate;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getPermissions() {
        return permissions;
    }

    public void setPermissions(Integer permissions) {
        this.permissions = permissions;
    }

    public UserInfo() {
    }


}
