package com.careHome.pojo;

public class City {
    private Integer id;
    private Integer cityid;
    private String city;
    private Integer provinceid;

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", cityid=" + cityid +
                ", city='" + city + '\'' +
                ", provinceid=" + provinceid +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCityid() {
        return cityid;
    }

    public void setCityid(Integer cityid) {
        this.cityid = cityid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(Integer provinceid) {
        this.provinceid = provinceid;
    }

    public City(Integer id, Integer cityid, String city, Integer provinceid) {
        this.id = id;
        this.cityid = cityid;
        this.city = city;
        this.provinceid = provinceid;
    }

    public City() {
    }
}
