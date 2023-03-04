package com.careHome.pojo;

import java.util.Date;

public class LiveInfo {
    private Integer lid;
    private String lname;
    private Date lage;
    private String lsex;
    private Integer uid;
    private String lstate;
    private String uname;
    private Integer careuid;
    private String careuname;
    private Integer deleted;

    @Override
    public String toString() {
        return "LiveInfo{" +
                "lid=" + lid +
                ", lname='" + lname + '\'' +
                ", lage=" + lage +
                ", lsex='" + lsex + '\'' +
                ", uid=" + uid +
                ", lstate='" + lstate + '\'' +
                ", uname='" + uname + '\'' +
                ", careuid=" + careuid +
                ", careuname='" + careuname + '\'' +
                ", deleted=" + deleted +
                '}';
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

    public Date getLage() {
        return lage;
    }

    public void setLage(Date lage) {
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

    public String getLstate() {
        return lstate;
    }

    public void setLstate(String lstate) {
        this.lstate = lstate;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Integer getCareuid() {
        return careuid;
    }

    public void setCareuid(Integer careuid) {
        this.careuid = careuid;
    }

    public String getCareuname() {
        return careuname;
    }

    public void setCareuname(String careuname) {
        this.careuname = careuname;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public LiveInfo(Integer lid, String lname, Date lage, String lsex, Integer uid, String lstate, String uname, Integer careuid, String careuname, Integer deleted) {
        this.lid = lid;
        this.lname = lname;
        this.lage = lage;
        this.lsex = lsex;
        this.uid = uid;
        this.lstate = lstate;
        this.uname = uname;
        this.careuid = careuid;
        this.careuname = careuname;
        this.deleted = deleted;
    }

    public LiveInfo() {
    }
}
