package com.gxuwz.fx.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.gxuwz.fx.pojo.WorkerAddress;

/**
 * 配送人员地址Mapper接口
 * @author fengx
 */
@Mapper
public interface WorkerAddressMapper {

    /**
     * 判断工作者地理位置是否存在
     * @param phonenum
     * @return
     */
    boolean existwa(String phonenum);

    /**
     * 更新工作者状态-工作
     * @param phonenum
     * @return
     */
    int updateStatusforwork(String phonenum);

    /**
     * 添加工作者地理位置
     * @param wa
     * @return
     */
    int addfirst(WorkerAddress wa);

    /**
     * 修改地理位置
     * @param wa
     * @return
     */
    int updateadd(WorkerAddress wa);

    /**
     * 更新工作者状态-休息
     * @param phonenum
     * @return
     */
    int updateStatusforstop(String phonenum);

    /**
     * 查询订单附近的所有工作者
     * @param longitude
     * @param latitude
     * @return
     */
    List<WorkerAddress> select_worker(double longitude, double latitude);

    /**
     * 工作者是否处于工作状态
     * @param phonenum
     * @return
     */
    boolean statusforwork(String phonenum);

    /**
     * 查询工作者地理位置
     * @param phonenum
     * @return
     */
    WorkerAddress select_wolnglat(String phonenum);

    /**
     * 查询订单(商家)最近的一位工作者
     * @param longitude
     * @param latitude
     * @return
     */
    List<WorkerAddress> select_oneworker(double longitude, double latitude);

    /**
     * 获取所有在线工作者坐标
     * @return
     */
    List<WorkerAddress> get_allwa();

}