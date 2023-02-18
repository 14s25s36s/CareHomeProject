package com.careHome.pojo;

public class Account {
    private Integer aid;
    private String useraccount;
    private String password;
    private Integer permissions;
    private Integer astate;
    private Integer deleted;

    @Override
    public String toString() {
        return "Account{" +
                "aid=" + aid +
                ", useraccount='" + useraccount + '\'' +
                ", password='" + password + '\'' +
                ", permissions=" + permissions +
                ", astate=" + astate +
                ", deleted=" + deleted +
                '}';
    }

    public Integer getAstate() {
        return astate;
    }

    public void setAstate(Integer astate) {
        this.astate = astate;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Account(Integer aid, String useraccount, String password, Integer permissions, Integer astate, Integer deleted) {
        this.aid = aid;
        this.useraccount = useraccount;
        this.password = password;
        this.permissions = permissions;
        this.astate = astate;
        this.deleted = deleted;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getUseraccount() {
        return useraccount;
    }

    public void setUseraccount(String useraccount) {
        this.useraccount = useraccount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPermissions() {
        return permissions;
    }

    public void setPermissions(Integer permissions) {
        this.permissions = permissions;
    }

    public Account() {
    }

}
