package com.gxuwz.fx.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.gxuwz.fx.mapper.WorkerOrderMapper;
import com.gxuwz.fx.pojo.WorkerOrder;
import com.gxuwz.fx.service.WorkerOrderService;

@Service
public class WorkerOrderServiceImpl implements WorkerOrderService {

    @Autowired
    WorkerOrderMapper wom;


    /**
     * 商家查询订单状态
     *
     * @param keyid
     * @return
     */
    public WorkerOrder shfindWorkerOrderByKey(String keyid) {
        return wom.shfindWorkerOrderByKey(keyid);
    }

    /**
     * 1 查询该工作者订单
     *
     * @param
     * @return
     */
    @Override
    public ArrayList<WorkerOrder> findWorkerOrderAllByPh(String phonenum) {
        return wom.findWorkerOrderAllByPh(phonenum);
    }

    /**
     * 2添加生成的工作者订单
     */
    @Override
    public int addOrder(WorkerOrder wo) {
        return wom.add(wo);
    }

    /**
     * 3更新工作者订单状态： 工作者放弃订单：2
     */
    @Override
    public int updateStatusGi(String phonenum, String keyid) {
        return wom.updateStatusGi(phonenum, keyid);
    }

    /**
     * 4 查询该工作者的指派订单
     *
     * @param
     * @return
     */
    @Override
    public ArrayList<WorkerOrder> findWorkerOrderAllByPhSo(String phonenum) {
        return wom.findWorkerOrderAllByPhSo(phonenum);
    }

    /**
     * 5更新订单：形成指派单
     *
     * @param
     * @return
     */
    @Override
    public int updateGiOrder(WorkerOrder wo) {
        return wom.updateGiOrder(wo);
    }

    /**
     * 6此订单是否存在
     *
     * @param keyid
     * @return
     */
    public boolean existBykeyid(String keyid, String phonenum) {
        return wom.existBykeyid(keyid, phonenum);
    }

    /**
     * 7更新工作者订单状态：工作者已完成订单：1
     */
    @Override
    public int updateStatusRu(String phonenum, String keyid) {
        return wom.updateStatus(phonenum, keyid);
    }

    /**
     * 8指派单接单
     *
     * @param keyid
     * @return
     */
    public int updateStatusToRe(String phonenum, String keyid) {
        return wom.updateStatusToRe(phonenum, keyid);
    }

    /**
     * 9更新工作者已存在订单
     *
     * @param
     * @return
     */
    @Override
    public int updateWoOrder(WorkerOrder wo) {
        return wom.updateWoOrder(wo);
    }

    /**
     * 10工作者开始配送订单
     *
     * @param keyid
     * @return
     */
    @Override
    public int updateStatusToWo(String phonenum, String keyid) {
        return wom.updateStatusToWo(phonenum, keyid);
    }

    /**
     * 11 查询该工作者的配送中订单
     *
     * @param
     * @return
     */
    @Override
    public ArrayList<WorkerOrder> findWorkerOrderAllByPhWo(String phonenum) {
        return wom.findWorkerOrderAllByPhWo(phonenum);
    }

    /**
     * 12 查询该工作者的配送中订单
     *
     * @param
     * @return
     */
    @Override
    public ArrayList<WorkerOrder> findWorkerEndOrderAllByPh(String phonenum) {
        return wom.findWorkerEndOrderAllByPh(phonenum);
    }

    /**
     * 12 查询该工作者历史订单
     *
     * @param
     * @return
     */
    @Override
    public ArrayList<WorkerOrder> findWorkerOrderAllLSDD(String phonenum) {
        return wom.findWorkerOrderAllLSDD(phonenum);
    }


    /**
     * 查询是否有未完成的订单
     *
     * @param
     * @return
     */
    @Override
    public boolean findworkpsz(String phonenum) {
        return wom.findworkpsz(phonenum);
    }

    /**
     * 13 获得今日数据
     *
     * @param
     * @return
     */
    @Override
    public int gettoday(String phonenum) {
        return wom.gettoday(phonenum);
    }

    /**
     * 13 获得今日数据
     *
     * @param
     * @return
     */
    @Override
    public int gettoday_qq_fi(String phonenum) {
        return wom.gettoday_qq_fi(phonenum);
    }

    /**
     * 13 获得今日数据
     *
     * @param
     * @return
     */
    @Override
    public int gettoday_zp(String phonenum) {
        return wom.gettoday_zp(phonenum);
    }

    /**
     * 13 获得今日数据
     *
     * @param
     * @return
     */
    @Override
    public int gettoday_zp_fi(String phonenum) {
        return wom.gettoday_zp_fi(phonenum);
    }


    /**
     * 获得今日收入明细
     *
     * @param
     * @return
     */
    public ArrayList<WorkerOrder> gettoday_srmx(String phonenum) {
        return wom.gettoday_srmx(phonenum);
    }

    /**
     * 获得本周收入明细
     *
     * @param
     * @return
     */
    public ArrayList<WorkerOrder> getweek_srmx(String phonenum) {
        return wom.getweek_srmx(phonenum);
    }

    /**
     * 获得本月收入明细
     *
     * @param
     * @return
     */
    public ArrayList<WorkerOrder> getmonth_srmx(String phonenum) {
        return wom.getmonth_srmx(phonenum);
    }


    /**
     * 14 获取一周内数据
     *
     * @param
     * @return
     */
    @Override
    public int getweek(String phonenum) {
        return wom.getweek(phonenum);
    }

    /**
     * 14 获取一周内数据
     *
     * @param
     * @return
     */
    @Override
    public int getweek_fi(String phonenum) {
        return wom.getweek_fi(phonenum);
    }

    /**
     * 14 获取一周内数据
     *
     * @param
     * @return
     */
    @Override
    public int getweek_zp(String phonenum) {
        return wom.getweek_zp(phonenum);
    }

    /**
     * 14 获取一周内数据
     *
     * @param
     * @return
     */
    @Override
    public int getweek_zp_fi(String phonenum) {
        return wom.getweek_zp_fi(phonenum);
    }


    /**
     * 15 获取一个月内数据
     *
     * @param
     * @return
     */
    @Override
    public int getmonth(String phonenum) {
        return wom.getmonth(phonenum);
    }

    /**
     * 15 获取一个月内数据
     *
     * @param
     * @return
     */
    @Override
    public int getmonth_fi(String phonenum) {
        return wom.getmonth_fi(phonenum);
    }

    /**
     * 15 获取一个月内数据
     *
     * @param
     * @return
     */
    @Override
    public int getmonth_zp(String phonenum) {
        return wom.getmonth_zp(phonenum);
    }

    /**
     * 15 获取一个月内数据
     *
     * @param
     * @return
     */
    @Override
    public int getmonth_zp_fi(String phonenum) {
        return wom.getmonth_zp_fi(phonenum);
    }


    /**
     * 16 查询该工作者的收入(当日)
     *
     * @param
     * @return
     */
    @Override
    public int shouru_today(String phonenum) {
        return wom.shouru_today(phonenum);
    }

    /**
     * 16 查询该工作者的收入(本周)
     *
     * @param
     * @return
     */
    @Override
    public int shouru_week(String phonenum) {
        return wom.shouru_week(phonenum);
    }

    /**
     * 16 查询该工作者的收入(当日)
     *
     * @param
     * @return
     */
    @Override
    public int shouru_month(String phonenum) {
        return wom.shouru_month(phonenum);
    }


    //Web端

    /**
     * 1获取所有群抢单
     *
     * @return
     */
    @Override
    public String getallqqd() {
        List<WorkerOrder> list = wom.getallqqd();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    /**
     * 2获取所有指派单
     *
     * @return
     */
    @Override
    public String getallzpd() {
        List<WorkerOrder> list = wom.getallzpd();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    /**
     * 3获取一个订单信息
     *
     * @return
     */
    public String cxoneorder(String keyid) {
        List<WorkerOrder> list = wom.cxoneorder(keyid);
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    /**
     * 4删除一个订单
     *
     * @return
     */
    public int web_deletewoorder(String keyid) {
        return wom.web_deletewoorder(keyid);
    }

}
