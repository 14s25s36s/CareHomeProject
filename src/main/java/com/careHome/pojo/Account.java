package com.careHome.pojo;

public class Account {
    private Integer aid;
    private String useraccount;
    private String password;
    private Integer permissions;

    @Override
    public String toString() {
        return "Account{" +
                "aid=" + aid +
                ", useraccount='" + useraccount + '\'' +
                ", password='" + password + '\'' +
                ", permissions=" + permissions +
                '}';
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

    public Account(Integer aid, String useraccount, String password, Integer permissions) {
        this.aid = aid;
        this.useraccount = useraccount;
        this.password = password;
        this.permissions = permissions;
    }
}
