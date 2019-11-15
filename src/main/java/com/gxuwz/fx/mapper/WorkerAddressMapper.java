package com.gxuwz.fx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gxuwz.fx.pojo.WorkerAddress;

@Mapper
public interface WorkerAddressMapper {
	
	/**
	 * 1工作者地理位置是否存在
	 * @param phonenum
	 * @return
	 */
	public boolean existwa(String phonenum);
	
	 /**
     * 2更新工作者状态-工作
     * @param phonenum
     * @return
     */
    public int updateStatusforwork(String phonenum);
	
	/**
	 * 3添加工作者地理位置
	 * @param wa
	 * @return
	 */
	public int addfirst(WorkerAddress wa);
	
	/**
	 * 4修改地理位置
	 * @param wa
	 * @return
	 */
	public int updateadd(WorkerAddress wa);
	
	  /**
     *5 更新工作者状态-休息
     * @param phonenum
     * @return
     */
    public int updateStatusforstop(String phonenum);
	
	/**
	 * 6查询订单附近的所有工作者
	 * @param longitude
	 * @param latitude
	 * @return
	 */
	public List<WorkerAddress> select_worker(double longitude,double latitude);
	
	/**
	 *7 工作者是否处于工作状态
	 * @param phonenum
	 * @return
	 */
	public boolean statusforwork(String phonenum);
	
	/**
	 * 8查询工作者地理位置
	 * @param phonenum
	 * @return
	 */
	public WorkerAddress select_wolnglat(String phonenum);
	
	/**
	 * 9查询订单最近的一位工作者
	 * @param longitude
	 * @param latitude
	 * @return
	 */
	public List<WorkerAddress> select_oneworker(double longitude,double latitude);
	
	
	
	
	
	
    
  

}
