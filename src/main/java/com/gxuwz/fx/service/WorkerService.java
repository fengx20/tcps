package com.gxuwz.fx.service;

import com.gxuwz.fx.pojo.Worker;

public interface WorkerService {
	
	/**
	 * 1判断工作者手机号是否已存在
	 * @param phonenum
	 * @return
	 */
	public boolean existph(String phonenum);
	
	/**
	 * 2工作者注册
	 * @param worker
	 * @return
	 */
	public int regist(Worker worker);
	
	/**
	 * 3工作者登录验证
	 * @param phonenum
	 * @param password
	 * @return
	 */
	public boolean login(String phonenum,String password);
	
	/**
	 * 4工作者是否已认证
	 * @param Phonenum
	 * @return
	 */
	 public boolean certification(String Phonenum);
	 
	 /**
     * 5判断是否存在此身份证号
     * @param phonenum
     * @return
     */
	 public boolean existIdCardNum(String phonenum,String idcardnum);
	 
	 /**
     *6 修改密码
     * @param phonenum
     * @return
     */
	 public int updatepsd(String phonenum,String password);
	 
	 /**
	 * 7工作者是否正在审核
	 * @param Phonenum
	 * @return
	 */
	public boolean woreview(String Phonenum);
	
	 /**
	  * 8工作者上传信息认证
	  */
    public int certificationupload(Worker woker);
    
    /**
     * 9获取工作者姓名
     * @param phonenum
     * @return
     */
    public String getworkername(String phonenum);
	
    /**
     * 10修改资料
     * @param phonenum
     * @return
     */
    public int updatezl(Worker worker);
    
    //Web端
	
    /**
     * 1获取所有已认证工作者
     * @return
     */
    public String web_getallcertworker();
    
    /**
     * 2获取所有未认证工作者
     * @return
     */
    public String web_getallnocertworker();

    /**
     * 3获取所有已停用工作者
     * @return
     */
    public String web_getallstopworker();
    
    /**
     * 3获取所有正在审核工作者
     * @return
     */
    public String web_getallshworker();
    
    /**
     * 4停用工作者
     * @return
     */
    public int web_stopworker(String phonenum);
    
    /**
     * 5获取一名工作者
     * @return
     */
    public String web_getoneworker(String phonenum);
    
    /**
          *获取一名工作者信息（监控中心）
     * @return
     */
    public Worker web_getoneworkerjk(String phonenum);
    
    /**
     * 6修改一名工作者信息
     * @return
     */
    public int web_editoneworker(Worker wo);
    
    /**
     * 7审核通过
     * @return
     */
    public int web_shtgworker(String phonenum);

}
