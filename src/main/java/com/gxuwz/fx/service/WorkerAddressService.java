package com.gxuwz.fx.service;

import com.gxuwz.fx.pojo.WorkerAddress;

public interface WorkerAddressService {
	
	/**
	 * 1工作者地理位置是否存在
	 */
	public boolean existwa(String phonenum);
	
	/**
	 *2进入工作状态
	 * @param phonenum
	 * @return
	 */
    public int work(String phonenum);
	
	/**
	 * 3工作者第一次工作上传地理位置
	 */
	public int addfirst(WorkerAddress wa);
	
	/**
	 *4 工作者开工上传更新地理位置(5秒一次)
	 */
	public int updateadd(WorkerAddress wa); 
	
	/**
     * 5进入休息状态
     * @param phonenum
     * @return
     */
    public int stop(String phonenum);
	
	/**
	 * 6查询订单附近的正在接单的工作者
	 */
	public String getWorkWa(double longitude,double latitude);
	
	/**
	 * 7工作者是否处于工作状态
	 */
	public boolean iswork(String phonenum);
	
	/**
	 *8 查询工作者地理位置
	 * @param phonenum
	 * @return
	 */
	public WorkerAddress select_wolnglat(String phonenum);
	
	/**
	 * 9查询指派一名工作者
	 */
	public String getOneWorkWa(double longitude,double latitude);
	
	
	
	
	
	
    
    

}
