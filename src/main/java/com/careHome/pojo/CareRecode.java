package com.careHome.pojo;

import java.util.Date;

public class CareRecode {
    private Integer careid;
    private Integer uid;
    private String uname;
    private Integer lid;
    private String lname;
    private Date caredate;
    private String careinfo;
    private Integer deleted;

    @Override
    public String toString() {
        return "CareRecode{" +
                "careid=" + careid +
                ", uid=" + uid +
                ", uname='" + uname + '\'' +
                ", lid=" + lid +
                ", lname='" + lname + '\'' +
                ", caredate=" + caredate +
                ", careinfo='" + careinfo + '\'' +
                ", deleted=" + deleted +
                '}';
    }

    public Integer getCareid() {
        return careid;
    }

    public void setCareid(Integer careid) {
        this.careid = careid;
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

    public Date getCaredate() {
        return caredate;
    }

    public void setCaredate(Date caredate) {
        this.caredate = caredate;
    }

    public String getCareinfo() {
        return careinfo;
    }

    public void setCareinfo(String careinfo) {
        this.careinfo = careinfo;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public CareRecode() {
    }

    public CareRecode(Integer careid, Integer uid, String uname, Integer lid, String lname, Date caredate, String careinfo, Integer deleted) {
        this.careid = careid;
        this.uid = uid;
        this.uname = uname;
        this.lid = lid;
        this.lname = lname;
        this.caredate = caredate;
        this.careinfo = careinfo;
        this.deleted = deleted;
    }
}
