package com.careHome.pojo;

public class LStateName {
    private Integer id;
    private Integer lstate;
    private String lstatename;
    private Integer deleted;

    @Override
    public String toString() {
        return "LStateName{" +
                "id=" + id +
                ", lstate=" + lstate +
                ", lstatename='" + lstatename + '\'' +
                ", deleted=" + deleted +
                '}';
    }

    public LStateName(Integer id, Integer lstate, String lstatename, Integer deleted) {
        this.id = id;
        this.lstate = lstate;
        this.lstatename = lstatename;
        this.deleted = deleted;
    }

    public LStateName() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLstate() {
        return lstate;
    }

    public void setLstate(Integer lstate) {
        this.lstate = lstate;
    }

    public String getLstatename() {
        return lstatename;
    }

    public void setLstatename(String lstatename) {
        this.lstatename = lstatename;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
