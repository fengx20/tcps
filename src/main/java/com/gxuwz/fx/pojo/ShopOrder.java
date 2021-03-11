package com.gxuwz.fx.pojo;

import java.io.Serializable;

/**
 * 商家订单
 * @author fengx
 */
public class ShopOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private int id;
    /**
     * 订单id
     */
    private String keyid;
    /**
     * 商家名字
     */
    private String shopname;
    /**
     * 第一次下单时间
     */
    private String firsttime;
    /**
     * 系统时间
     */
    private String systime;
    /**
     * 商品
     */
    private String thing;
    /**
     * 备注
     */
    private String remark;
    /**
     * 商家电话
     */
    private String shopphonenum;
    /**
     * 发货时间
     */
    private String sendtime;
    /**
     * 配送费
     */
    private String reward;
    /**
     * 收货人电话
     */
    private String recphonenum;
    /**
     * 收货人姓名
     */
    private String recname;
    /**
     * 收货人地址
     */
    private String recaddress;
    /**
     * 商家地址
     */
    private String shopaddress;
    /**
     * 商家位置经度
     */
    private double longitude;
    /**
     * 商家位置纬度
     */
    private double latitude;
    /**
     * 收货人位置经度
     */
    private double reclongitude;
    /**
     * 收货人位置纬度
     */
    private double reclatitude;
    /**
     * 订单类别
     */
    private int sort;
    /**
     * 订单状态
     */
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKeyid() {
        return keyid;
    }

    public void setKeyid(String keyid) {
        this.keyid = keyid;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getFirsttime() {
        return firsttime;
    }

    public void setFirsttime(String firsttime) {
        this.firsttime = firsttime;
    }

    public String getSystime() {
        return systime;
    }

    public void setSystime(String systime) {
        this.systime = systime;
    }

    public String getThing() {
        return thing;
    }

    public void setThing(String thing) {
        this.thing = thing;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getShopphonenum() {
        return shopphonenum;
    }

    public void setShopphonenum(String shopphonenum) {
        this.shopphonenum = shopphonenum;
    }

    public String getSendtime() {
        return sendtime;
    }

    public void setSendtime(String sendtime) {
        this.sendtime = sendtime;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getRecphonenum() {
        return recphonenum;
    }

    public void setRecphonenum(String recphonenum) {
        this.recphonenum = recphonenum;
    }

    public String getRecname() {
        return recname;
    }

    public void setRecname(String recname) {
        this.recname = recname;
    }

    public String getRecaddress() {
        return recaddress;
    }

    public void setRecaddress(String recaddress) {
        this.recaddress = recaddress;
    }

    public String getShopaddress() {
        return shopaddress;
    }

    public void setShopaddress(String shopaddress) {
        this.shopaddress = shopaddress;
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

    public double getReclongitude() {
        return reclongitude;
    }

    public void setReclongitude(double reclongitude) {
        this.reclongitude = reclongitude;
    }

    public double getReclatitude() {
        return reclatitude;
    }

    public void setReclatitude(double reclatitude) {
        this.reclatitude = reclatitude;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
