package com.careHome.pojo;

public class Province {
    private Integer id;
    private Integer provinceid;
    private String province;

    @Override
    public String toString() {
        return "Province{" +
                "id=" + id +
                ", provinceid=" + provinceid +
                ", province='" + province + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(Integer provinceid) {
        this.provinceid = provinceid;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Province(Integer id, Integer provinceid, String province) {
        this.id = id;
        this.provinceid = provinceid;
        this.province = province;
    }

    public Province() {
    }
}
