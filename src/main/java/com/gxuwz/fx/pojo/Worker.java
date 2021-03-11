package com.gxuwz.fx.pojo;

import java.io.Serializable;

/**
 * 配送人员
 * @author fengx
 */
public class Worker implements Serializable {

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
     * 密码
     */
    private String password;
    /**
     * 身份证号
     */
    private String idcardnum;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private String sex;
    /**
     * 城市
     */
    private String city;
    /**
     * 状态
     */
    private String status;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdcardnum() {
        return idcardnum;
    }

    public void setIdcardnum(String idcardnum) {
        this.idcardnum = idcardnum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
