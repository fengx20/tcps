package com.gxuwz.fx.mapper;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.gxuwz.fx.pojo.WorkerOrder;

/**
 * 配送人员订单Mapper接口
 * @author fengx
 */
@Mapper
public interface WorkerOrderMapper {

    /**
     * 商家查询订单状态
     * @param keyid
     * @return
     */
    WorkerOrder shfindWorkerOrderByKey(String keyid);

    /**
     * 查询该工作者订单（订单-配送中）
     * @param
     * @return
     */
    ArrayList<WorkerOrder> findWorkerOrderAllByPh(String phonenum);

    /**
     * 添加工作者订单
     * @param wo
     * @return
     */
    int add(WorkerOrder wo);

    /**
     * 更新订单状态为：已放弃：2
     * @param keyid
     * @return
     */
    int updateStatusGi(String phonenum, String keyid);

    /**
     * 查询该工作者的指派订单
     * @param
     * @return
     */
    ArrayList<WorkerOrder> findWorkerOrderAllByPhSo(String phonenum);

    /**
     * 更新订单：形成指派单
     * @param
     * @return
     */
    int updateGiOrder(WorkerOrder wo);

    /**
     * 此订单是否存在
     * @param keyid
     * @return
     */
    boolean existBykeyid(String keyid, String phonenum);

    /**
     * 更新订单状态为：已完成：1
     * @param keyid
     * @return
     */
    int updateStatus(String phonenum, String keyid);

    /**
     * 指派单接单
     * @param keyid
     * @return
     */
    int updateStatusToRe(String phonenum, String keyid);

    /**
     * 更新工作者已存在订单
     * @param
     * @return
     */
    int updateWoOrder(WorkerOrder wo);

    /**
     * 工作者开始配送订单
     * @param keyid
     * @return
     */
    int updateStatusToWo(String phonenum, String keyid);

    /**
     * 查询该工作者的配送中订单
     * @param
     * @return
     */
    ArrayList<WorkerOrder> findWorkerOrderAllByPhWo(String phonenum);

    /**
     * 查询该工作者的配送中订单
     * @param
     * @return
     */
    ArrayList<WorkerOrder> findWorkerEndOrderAllByPh(String phonenum);

    /**
     * 查询该工作者历史订单
     * @param
     * @return
     */
    ArrayList<WorkerOrder> findWorkerOrderAllLSDD(String phonenum);

    /**
     * 查询是否有未完成的订单
     * @param
     * @return
     */
    boolean findworkpsz(String phonenum);

    /**
     * 获得今日数据
     * @param
     * @return
     */
    int gettoday(String phonenum);

    /**
     * 获得今日群抢单数据
     * @param
     * @return
     */
    int gettoday_qq_fi(String phonenum);

    /**
     * 获得今日指派单数据
     * @param
     * @return
     */
    int gettoday_zp(String phonenum);

    /**
     * 获得今日指派单已完成数据
     * @param
     * @return
     */
    int gettoday_zp_fi(String phonenum);

    /**
     * 获得今日收入明细
     * @param
     * @return
     */
    ArrayList<WorkerOrder> gettoday_srmx(String phonenum);

    /**
     * 获得本周收入明细
     * @param
     * @return
     */
    ArrayList<WorkerOrder> getweek_srmx(String phonenum);

    /**
     * 获得本月收入明细
     * @param
     * @return
     */
    ArrayList<WorkerOrder> getmonth_srmx(String phonenum);

    /**
     * 获取一周内数据
     * @param
     * @return
     */
    int getweek(String phonenum);

    /**
     * 获取一周内数据
     * @param
     * @return
     */
    int getweek_fi(String phonenum);

    /**
     * 获取一周内数据
     * @param
     * @return
     */
    int getweek_zp(String phonenum);

    /**
     * 获取一周内数据
     * @param
     * @return
     */
    int getweek_zp_fi(String phonenum);

    /**
     * 获取一个月内数据
     * @param
     * @return
     */
    int getmonth(String phonenum);

    /**
     * 获取一个月内数据
     * @param
     * @return
     */
    int getmonth_fi(String phonenum);

    /**
     * 获取一个月内数据
     * @param
     * @return
     */
    int getmonth_zp(String phonenum);

    /**
     * 获取一个月内数据
     * @param
     * @return
     */
    int getmonth_zp_fi(String phonenum);

    /**
     * 查询该工作者的收入(当日)
     * @param
     * @return
     */
    int shouru_today(String phonenum);

    /**
     * 查询该工作者的收入(本周)
     * @param
     * @return
     */
    int shouru_week(String phonenum);

    /**
     * 查询该工作者的收入(当日)
     * @param
     * @return
     */
    int shouru_month(String phonenum);

    /*PC端*/

    /**
     * 获取所有群抢单
     * @return
     */
    List<WorkerOrder> getallqqd();

    /**
     * 获取所有指派单
     * @return
     */
    List<WorkerOrder> getallzpd();

    /**
     * 获取一个订单信息
     * @return
     */
    List<WorkerOrder> cxoneorder(String keyid);

    /**
     * 删除一个订单
     * @return
     */
    int web_deletewoorder(String keyid);

}
