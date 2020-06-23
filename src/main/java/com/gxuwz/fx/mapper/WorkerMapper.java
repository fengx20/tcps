package com.gxuwz.fx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gxuwz.fx.pojo.Worker;

@Mapper
public interface WorkerMapper {

    /*app端*/

	/**
	 * 1判断手机号是否存在
	 * @param phonenum
	 * @return
	 */
    public boolean existByPhonenum(String phonenum);

	/**
	 * 2添加工作者
	 * @param worker
	 * @return
	 */
	public int add(Worker worker);

	/**
     * 3登录验证
     * @param phonenum
     * @param password
     * @return
     */
    public boolean existByWorker(String phonenum,String password);

    /**
     * 4工作者是否已认证
     * @param Phonenum
     * @return
     */
    public boolean certification(String Phonenum);

    /**
     *5 判断是否存在此身份证号
     * @param phonenum
     * @return
     */
    public boolean existIdCardNum(String phonenum,String idcardnum);

    /**
     * 6修改密码
     * @param phonenum
     * @return
     */
    public int updatepsd(String phonenum,String password);

    /**
     *7 工作者是否在审核中
     * @param Phonenum
     * @return
     */
    public boolean review(String Phonenum);

    /**
     * 8工作者上传信息认证
     */
    public int certificationupdate(Worker woker);

    /**
     * 9获取工作者姓名
     * @param phonenum
     * @return
     */
    public String getworkername(String phonenum);

    /**
     * 10修改资料
     * @param
     * @return
     */
    public int updatezl(Worker worker);

    /**
     * 更新工作者认证状态
     * @param phonenum
     * @return
     */
    public int updatestatus(String phonenum);

    /**
     * 获取工作者id
     * @param phonenum
     * @return
     */
    public int getworkerid(String phonenum);



    /*web端*/

    /**
     * 1获取所有已认证工作者
     * @return
     */
    public List<Worker> web_getallcertworker();

    /**
     * 2获取所有未认证工作者
     * @return
     */
    public List<Worker> web_getallnocertworker();

    /**
     * 3获取所有已停用工作者
     * @return
     */
    public List<Worker> web_getallstopworker();

    /**
     * 4获取所有正在审核工作者
     * @return
     */
    public List<Worker> web_getallshworker();

    /**
     * 5停用工作者
     * @return
     */
    public int web_stopworker(String phonenum);

    /**
     * 6获取一名工作者
     * @return
     */
    public List<Worker> web_getoneworker(String phonenum);

    /**
     * 7获取一名工作者
     * @return
     */
    public Worker web_getoneworkerjk(String phonenum);

    /**
     * 8修改一名工作者信息
     * @return
     */
    public int web_editoneworker(Worker wo);

    /**
     * 9审核通过
     * @return
     */
    public int web_shtgworker(String phonenum);

}
