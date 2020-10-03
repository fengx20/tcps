package com.gxuwz.fx.pojo;


/**
 * 工作者地址
 *
 * @author Administrator
 */
public class WorkerAddress {

    private int id;
    private String phonenum;
    private double longitude;
    private double latitude;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the phonenum
     */
    public String getPhonenum() {
        return phonenum;
    }

    /**
     * @param phonenum the phonenum to set
     */
    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    /**
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }


}
