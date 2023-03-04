package com.careHome.pojo;

public class Care {
    private Integer id;
    private Integer careuid;
    private String careuname;
    private Integer deleted;

    @Override
    public String toString() {
        return "Care{" +
                "id=" + id +
                ", careuid=" + careuid +
                ", careuname='" + careuname + '\'' +
                ", deleted=" + deleted +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Care(Integer id, Integer careuid, String careuname, Integer deleted) {
        this.id = id;
        this.careuid = careuid;
        this.careuname = careuname;
        this.deleted = deleted;
    }

    public Care() {
    }
}
