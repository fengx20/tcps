package com.gxuwz.fx.pojo;

import java.io.Serializable;

/**
 * 验证码
 * @author fengx
 */
public class Yzm implements Serializable {

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
     * 验证码
     */
    private String code;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
