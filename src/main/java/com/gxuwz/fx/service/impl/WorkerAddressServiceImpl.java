package com.gxuwz.fx.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.gxuwz.fx.mapper.WorkerAddressMapper;
import com.gxuwz.fx.pojo.WorkerAddress;
import com.gxuwz.fx.service.WorkerAddressService;

/**
 * 配送人员地址业务实现层
 * @author fengx
 */
@Service
public class WorkerAddressServiceImpl implements WorkerAddressService {

    @Autowired
    private WorkerAddressMapper wam;

    /**
     * 工作者地理位置是否存在
     * @param phonenum
     * @return
     */
    @Override
    public boolean existwa(String phonenum) {
        return wam.existwa(phonenum);
    }

    /**
     * 进入工作状态
     * @param phonenum
     * @return
     */
    @Override
    public int work(String phonenum) {
        return wam.updateStatusforwork(phonenum);
    }

    /**
     * 工作者第一次工作上传地理位置
     * @param wa
     * @return
     */
    @Override
    public int addfirst(WorkerAddress wa) {
        return wam.addfirst(wa);
    }

    /**
     * 工作者开工上传更新地理位置(5秒一次)
     * @param wa
     * @return
     */
    @Override
    public int updateadd(WorkerAddress wa) {
        return wam.updateadd(wa);
    }

    /**
     * 进入休息状态
     * @param phonenum
     * @return
     */
    @Override
    public int stop(String phonenum) {
        return wam.updateStatusforstop(phonenum);
    }

    /**
     * 查询订单附近的正在接单的工作者
     * @param longitude
     * @param latitude
     * @return
     */
    @Override
    public String getWorkWa(double longitude, double latitude) {
        List<WorkerAddress> list = wam.select_worker(longitude, latitude);
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    /**
     * 工作者是否处于工作者状态
     * @param phonenum
     * @return
     */
    @Override
    public boolean iswork(String phonenum) {
        return wam.statusforwork(phonenum);
    }

    /**
     * 查询工作者地理位置
     * @param phonenum
     * @return
     */
    @Override
    public WorkerAddress select_wolnglat(String phonenum) {
        return wam.select_wolnglat(phonenum);
    }

    /**
     * 查询指派一名工作者
     * @param longitude
     * @param latitude
     * @return
     */
    @Override
    public String getOneWorkWa(double longitude, double latitude) {
        List<WorkerAddress> list = wam.select_oneworker(longitude, latitude);
        // 取排序第一的数据
        WorkerAddress wa = list.get(0);
        Gson gson = new Gson();
        String json = gson.toJson(wa);
        return json;
    }

    /**
     * 获取所有在线工作者坐标
     * @return
     */
    public List<WorkerAddress> get_allwa() {
        List<WorkerAddress> list = wam.get_allwa();
        return list;
    }

}
