package com.gxuwz.fx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.gxuwz.fx.mapper.WorkerAddressMapper;
import com.gxuwz.fx.pojo.WorkerAddress;
import com.gxuwz.fx.service.WorkerAddressService;

@Service
public class WorkerAddressServiceImpl implements WorkerAddressService {
	
	@Autowired WorkerAddressMapper wam;
	
	
	/**
	 * 1工作者地理位置是否存在
	 */
	@Override
	public boolean existwa(String phonenum){
		return wam.existwa(phonenum);
	}
	
	/**
	 * 2进入工作状态
	 * @param phonenum
	 * @return
	 */
	@Override
    public int work(String phonenum) {
		return wam.updateStatusforwork(phonenum);
	}
    
	/**
	 * 3工作者第一次工作上传地理位置
	 */
	@Override
	public int addfirst(WorkerAddress wa) {
		return wam.addfirst(wa);
	}
	
	/**
	 * 工作者开工上传更新地理位置(5秒一次)
	 */
	@Override
	public int updateadd(WorkerAddress wa) {
		return wam.updateadd(wa);
	}
	
	/**
     * 5进入休息状态
     * @param phonenum
     * @return
     */
	@Override
    public int stop(String phonenum) {
    	return wam.updateStatusforstop(phonenum);
    }
    
	/**
	 * 6查询订单附近的正在接单的工作者
	 */
	@Override
	public String getWorkWa(double longitude, double latitude) {
		List<WorkerAddress> list = wam.select_worker(longitude, latitude);
		Gson gson = new Gson();  
	    String json = gson.toJson(list); 
        return json;
	}
	
	/**
	 * 7工作者是否处于工作者状态
	 */
	@Override
	public boolean iswork(String phonenum) {
		return wam.statusforwork(phonenum);
	}
	
	/**
	 *8 查询工作者地理位置
	 * @param phonenum
	 * @return
	 */
	@Override
	public WorkerAddress select_wolnglat(String phonenum) {
		return wam.select_wolnglat(phonenum);
	}
	
	/**
	 * 9查询指派一名工作者
	 */
	@Override
	public String getOneWorkWa(double longitude,double latitude) {
		List<WorkerAddress> list = wam.select_oneworker(longitude, latitude);
		WorkerAddress wa =  list.get(0);   //取排序第一的数据
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
		//Gson gson = new Gson();
		//String json = gson.toJson(list);
		return list;
	}
	
	
	
	
	
	
    
	

}
