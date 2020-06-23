package com.gxuwz.fx.service;

import java.util.ArrayList;
import com.gxuwz.fx.pojo.WorkerOrder;

public interface WorkerOrderService {

	/**
	 * 商家查询订单状态
	 * @param keyid
	 * @return
	 */
	public WorkerOrder shfindWorkerOrderByKey(String keyid);

	/**
	 * 1查询该工作者所有订单
	 * @param
	 * @return
	 */
	public ArrayList<WorkerOrder> findWorkerOrderAllByPh(String phonenum);

	/**
	 * 添加生成的工作者订单
	 * @param wo
	 * @return
	 */
	public int addOrder(WorkerOrder wo);

	/**
	 * 3更新工作者订单状态： 工作者放弃订单：2
	 * @param phonenum
	 * @param keyid
	 * @return
	 */
	public int updateStatusGi(String phonenum, String keyid);

	/**
	 *4 查询该工作者的指派订单
	 * @param
	 * @return
	 */
	public ArrayList<WorkerOrder> findWorkerOrderAllByPhSo(String phonenum);

	/**
	 * 5更新订单：形成指派单
	 * @param
	 * @return
	 */
	public int updateGiOrder(WorkerOrder wo);

	/**
	 * 6此订单是否存在
	 * @param keyid
	 * @return
	 */
	public boolean existBykeyid(String keyid,String phonenum);

	/**
	 * 7更新工作者订单状态：工作者已完成订单：1
	 */
	public int updateStatusRu(String phonenum , String keyid);

	/**
	 * 8指派单接单
	 * @param keyid
	 * @return
	 */
	public int updateStatusToRe(String phonenum,String keyid);

	/**
	 * 9更新工作者已存在订单
	 * @param
	 * @return
	 */
	public int updateWoOrder(WorkerOrder wo);

	/**
	 * 10工作者开始配送订单
	 * @param keyid
	 * @return
	 */
	public int updateStatusToWo(String phonenum,String keyid);

	/**
	 *11 查询该工作者的配送中订单
	 * @param
	 * @return
	 */
	public ArrayList<WorkerOrder> findWorkerOrderAllByPhWo(String phonenum);

	/**
	 *12 查询该工作者的配送中订单
	 * @param
	 * @return
	 */
	public ArrayList<WorkerOrder> findWorkerEndOrderAllByPh(String phonenum);

	/**
	 *12 查询该工作者历史订单
	 * @param
	 * @return
	 */
	public ArrayList<WorkerOrder> findWorkerOrderAllLSDD(String phonenum);

	/**
	 *查询是否有未完成的订单
	 * @param
	 * @return
	 */
	public boolean findworkpsz(String phonenum);

	/**
	 *13 获得今日数据
	 * @param
	 * @return
	 */
	public int gettoday(String phonenum);

	/**
	 *13 获得今日数据
	 * @param
	 * @return
	 */
	public int gettoday_qq_fi(String phonenum);

	/**
	 *13 获得今日数据
	 * @param
	 * @return
	 */
	public int gettoday_zp(String phonenum);

	/**
	 *13 获得今日数据
	 * @param
	 * @return
	 */
	public int gettoday_zp_fi(String phonenum);



	/**
	  * 获得今日收入明细
	 * @param
	 * @return
	 */
	public ArrayList<WorkerOrder> gettoday_srmx(String phonenum);

	/**
	  * 获得本周收入明细
	 * @param
	 * @return
	 */
	public ArrayList<WorkerOrder> getweek_srmx(String phonenum);

	/**
	  * 获得本月收入明细
	 * @param
	 * @return
	 */
	public ArrayList<WorkerOrder> getmonth_srmx(String phonenum);




	/**
	 *14 获取一周内数据
	 * @param
	 * @return
	 */
	public int getweek(String phonenum);

	/**
	 *14 获取一周内数据
	 * @param
	 * @return
	 */
	public int getweek_fi(String phonenum);

	/**
	 *14 获取一周内数据
	 * @param
	 * @return
	 */
	public int getweek_zp(String phonenum);

	/**
	 *14 获取一周内数据
	 * @param
	 * @return
	 */
	public int getweek_zp_fi(String phonenum);



	/**
	 *15 获取一个月内数据
	 * @param
	 * @return
	 */
	public int getmonth(String phonenum);

	/**
	 *15 获取一个月内数据
	 * @param
	 * @return
	 */
	public int getmonth_fi(String phonenum);

	/**
	 *15 获取一个月内数据
	 * @param
	 * @return
	 */
	public int getmonth_zp(String phonenum);

	/**
	 *15 获取一个月内数据
	 * @param
	 * @return
	 */
	public int getmonth_zp_fi(String phonenum);

	/**
	 *16 查询该工作者的收入(当日)
	 * @param
	 * @return
	 */
	public int shouru_today(String phonenum);

	/**
	 *16 查询该工作者的收入(本周)
	 * @param
	 * @return
	 */
	public int shouru_week(String phonenum);

	/**
	 *16 查询该工作者的收入(当日)
	 * @param
	 * @return
	 */
	public int shouru_month(String phonenum);



	//Web端

	/**
	 * 1获取所有群抢单
	 * @return
	 */
	public String getallqqd();

	/**
	 * 2获取所有指派单
	 * @return
	 */
	public String getallzpd();

	/**
	 * 3获取一个订单信息
	 * @return
	 */
	public String cxoneorder(String keyid);

	/**
     * 4删除一个订单
     * @return
     */
    public int web_deletewoorder(String keyid);

}
