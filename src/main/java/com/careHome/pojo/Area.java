package com.careHome.pojo;

public class Area {
    private Integer id;
    private Integer areaid;
    private String area;
    private Integer cityid;

    @Override
    public String toString() {
        return "Area{" +
                "id=" + id +
                ", areaid=" + areaid +
                ", area='" + area + '\'' +
                ", cityid=" + cityid +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAreaid() {
        return areaid;
    }

    public void setAreaid(Integer areaid) {
        this.areaid = areaid;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getCityid() {
        return cityid;
    }

    public void setCityid(Integer cityid) {
        this.cityid = cityid;
    }

    public Area(Integer id, Integer areaid, String area, Integer cityid) {
        this.id = id;
        this.areaid = areaid;
        this.area = area;
        this.cityid = cityid;
    }

    public Area() {
    }
}
