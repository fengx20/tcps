package com.gxuwz.fx.pojo;

import java.io.Serializable;

/**
 * 配送人员绩效
 * @author fengx
 */
public class WorkerGrade implements Serializable {

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
     * 已完成订单数量
     */
    private int ywc;
    /**
     * 未完成订单量
     */
    private int wwc;

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

    public int getYwc() {
        return ywc;
    }

    public void setYwc(int ywc) {
        this.ywc = ywc;
    }

    public int getWwc() {
        return wwc;
    }

    public void setWwc(int wwc) {
        this.wwc = wwc;
    }
}
