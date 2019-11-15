package com.gxuwz.fx.pojo;

import java.io.Serializable;


/**
 * 商家订单
 * @author Administrator
 *
 */
public class ShopOrder implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String keyid;
	private String shopname;
	private String firsttime;
	private String systime;
	private String thing;
	private String remark;
	private String shopphonenum;
	private String sendtime;
	private String reward;
	private String recphonenum;
	private String recname;
	private String recaddress;
	private String shopaddress;
	private double longitude;
	private double latitude;
	private double reclongitude;
	private double reclatitude;
	private int sort;
	private int status;
	
	public ShopOrder() {
		
	}
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
	 * @return the keyid
	 */
	public String getKeyid() {
		return keyid;
	}
	/**
	 * @param keyid the keyid to set
	 */
	public void setKeyid(String keyid) {
		this.keyid = keyid;
	}
	/**
	 * @return the shopname
	 */
	public String getShopname() {
		return shopname;
	}
	/**
	 * @param shopname the shopname to set
	 */
	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	
	
	
	/**
	 * @return the firsttime
	 */
	public String getFirsttime() {
		return firsttime;
	}
	/**
	 * @param firsttime the firsttime to set
	 */
	public void setFirsttime(String firsttime) {
		this.firsttime = firsttime;
	}
	
	
	/**
	 * @return the systime
	 */
	public String getSystime() {
		return systime;
	}
	/**
	 * @param systime the systime to set
	 */
	public void setSystime(String systime) {
		this.systime = systime;
	}
	/**
	 * @return the recaddress
	 */
	public String getRecaddress() {
		return recaddress;
	}
	/**
	 * @param recaddress the recaddress to set
	 */
	public void setRecaddress(String recaddress) {
		this.recaddress = recaddress;
	}
	/**
	 * @return the shopaddress
	 */
	public String getShopaddress() {
		return shopaddress;
	}
	/**
	 * @param shopaddress the shopaddress to set
	 */
	public void setShopaddress(String shopaddress) {
		this.shopaddress = shopaddress;
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
	
	
	/**
	 * @return the sort
	 */
	public int getSort() {
		return sort;
	}
	/**
	 * @param sort the sort to set
	 */
	public void setSort(int sort) {
		this.sort = sort;
	}
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @return the thing
	 */
	public String getThing() {
		return thing;
	}
	/**
	 * @param thing the thing to set
	 */
	public void setThing(String thing) {
		this.thing = thing;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @return the shopphonenum
	 */
	public String getShopphonenum() {
		return shopphonenum;
	}
	/**
	 * @param shopphonenum the shopphonenum to set
	 */
	public void setShopphonenum(String shopphonenum) {
		this.shopphonenum = shopphonenum;
	}
	/**
	 * @return the sendtime
	 */
	public String getSendtime() {
		return sendtime;
	}
	
	
	/**
	 * @return the reward
	 */
	public String getReward() {
		return reward;
	}
	/**
	 * @param reward the reward to set
	 */
	public void setReward(String reward) {
		this.reward = reward;
	}
	/**
	 * @param sendtime the sendtime to set
	 */
	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}
	/**
	 * @return the recphonenum
	 */
	public String getRecphonenum() {
		return recphonenum;
	}
	/**
	 * @param recphonenum the recphonenum to set
	 */
	public void setRecphonenum(String recphonenum) {
		this.recphonenum = recphonenum;
	}
	/**
	 * @return the recname
	 */
	public String getRecname() {
		return recname;
	}
	/**
	 * @param recname the recname to set
	 */
	public void setRecname(String recname) {
		this.recname = recname;
	}
	/**
	 * @return the reclongitude
	 */
	public double getReclongitude() {
		return reclongitude;
	}
	/**
	 * @param reclongitude the reclongitude to set
	 */
	public void setReclongitude(double reclongitude) {
		this.reclongitude = reclongitude;
	}
	/**
	 * @return the reclatitude
	 */
	public double getReclatitude() {
		return reclatitude;
	}
	/**
	 * @param reclatitude the reclatitude to set
	 */
	public void setReclatitude(double reclatitude) {
		this.reclatitude = reclatitude;
	}
	
	
	
	

}
