package com.gxuwz.fx.service;

public interface WorkerGradeService {
	
	/**
	 * 添加所有数据
	 * @param wg
	 * @return
	 */
	public int addwg(String phonenum);
	
	/**
	 * 增加已完成数
	 * @param phonenum
	 * @return
	 */
	public int updateywc(String phonenum);
	
	/**
	 * 增加未完成数
	 * @param phonenum
	 * @return
	 */
	public int updatewwc(String phonenum);
	
	/**
	 * 获取所有数据
	 * @param phonenum
	 * @return
	 */
	public String getWorkerGrade(String phonenum);
	

}
