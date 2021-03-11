package com.gxuwz.fx.service;

import java.util.List;

import com.gxuwz.fx.pojo.WorkerAddress;

/**
 * 配送人员地址业务接口层
 * @author fengx
 */
public interface WorkerAddressService {

    /**
     * 判断工作者地理位置是否存在
     * @param phonenum
     * @return
     */
    boolean existwa(String phonenum);

    /**
     * 进入工作状态
     * @param phonenum
     * @return
     */
    int work(String phonenum);

    /**
     * 工作者第一次工作上传地理位置
     * @param wa
     * @return
     */
    int addfirst(WorkerAddress wa);

    /**
     * 工作者开工上传更新地理位置(5秒一次)
     * @param wa
     * @return
     */
    int updateadd(WorkerAddress wa);

    /**
     * 进入休息状态
     * @param phonenum
     * @return
     */
    int stop(String phonenum);

    /**
     * 查询订单附近的正在接单的工作者
     * @param longitude
     * @param latitude
     * @return
     */
    String getWorkWa(double longitude, double latitude);

    /**
     * 工作者是否处于工作状态
     * @param phonenum
     * @return
     */
    boolean iswork(String phonenum);

    /**
     * 查询工作者地理位置
     * @param phonenum
     * @return
     */
    WorkerAddress select_wolnglat(String phonenum);

    /**
     * 查询指派一名工作者
     * @param longitude
     * @param latitude
     * @return
     */
    String getOneWorkWa(double longitude, double latitude);

    /**
     * 获取所有在线工作者坐标
     * @return
     */
    List<WorkerAddress> get_allwa();

}
