package com.careHome.pojo;

public class LiveInfo {
    private Integer lid;
    private String lname;
    private Integer lage;
    private String lsex;
    private Integer uid;
    private Integer lstate;
    private String uname;
    private Integer deleted;

    @Override
    public String toString() {
        return "LiveInfo{" +
                "lid=" + lid +
                ", lname='" + lname + '\'' +
                ", lage=" + lage +
                ", lsex='" + lsex + '\'' +
                ", uid=" + uid +
                ", lstate=" + lstate +
                ", uname='" + uname + '\'' +
                ", deleted=" + deleted +
                '}';
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public LiveInfo(Integer lid, String lname, Integer lage, String lsex, Integer uid, Integer lstate, String uname, Integer deleted) {
        this.lid = lid;
        this.lname = lname;
        this.lage = lage;
        this.lsex = lsex;
        this.uid = uid;
        this.lstate = lstate;
        this.uname = uname;
        this.deleted = deleted;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public Integer getLage() {
        return lage;
    }

    public void setLage(Integer lage) {
        this.lage = lage;
    }

    public String getLsex() {
        return lsex;
    }

    public void setLsex(String lsex) {
        this.lsex = lsex;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getLstate() {
        return lstate;
    }

    public void setLstate(Integer lstate) {
        this.lstate = lstate;
    }

    public LiveInfo() {
    }

}
