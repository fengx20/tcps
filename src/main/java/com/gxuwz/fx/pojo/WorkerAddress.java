package com.gxuwz.fx.pojo;


import java.io.Serializable;

/**
 * 配置人员地理位置
 * @author fengx
 */
public class WorkerAddress implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private int id;
    /**
     * 手机号
     */
    private String phonenum;
    /**
     * 经度
     */
    private double longitude;
    /**
     * 纬度
     */
    private double latitude;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
