package com.gxuwz.fx.service;

import com.gxuwz.fx.pojo.Worker;

/**
 * 配送人员业务接口层
 * @author fengx
 */
public interface WorkerService {

    /**
     * 判断工作者手机号是否已存在
     * @param phonenum
     * @return
     */
    boolean existph(String phonenum);

    /**
     * 工作者注册
     * @param worker
     * @return
     */
    int regist(Worker worker);

    /**
     * 工作者登录验证
     * @param phonenum
     * @param password
     * @return
     */
    boolean login(String phonenum, String password);

    /**
     * 工作者是否已认证
     * @param Phonenum
     * @return
     */
    boolean certification(String Phonenum);

    /**
     * 判断是否存在此身份证号
     * @param phonenum
     * @return
     */
    boolean existIdCardNum(String phonenum, String idcardnum);

    /**
     * 修改密码
     * @param phonenum
     * @return
     */
    int updatepsd(String phonenum, String password);

    /**
     * 工作者是否正在审核
     * @param Phonenum
     * @return
     */
    boolean woreview(String Phonenum);

    /**
     * 工作者上传信息认证
     * @param woker
     * @return
     */
    int certificationupload(Worker woker);

    /**
     * 获取工作者姓名
     * @param phonenum
     * @return
     */
    String getworkername(String phonenum);

    /**
     * 修改资料
     * @param
     * @return
     */
    int updatezl(Worker worker);

    /*Web端*/
    /**
     * 获取所有已认证工作者
     * @return
     */
    String web_getallcertworker();

    /**
     * 获取所有未认证工作者
     * @return
     */
    String web_getallnocertworker();

    /**
     * 获取所有已停用工作者
     * @return
     */
    String web_getallstopworker();

    /**
     * 获取所有正在审核工作者
     * @return
     */
    String web_getallshworker();

    /**
     * 停用工作者
     * @return
     */
    int web_stopworker(String phonenum);

    /**
     * 获取一名工作者
     * @return
     */
    String web_getoneworker(String phonenum);

    /**
     * 获取一名工作者信息（监控中心）
     * @return
     */
    Worker web_getoneworkerjk(String phonenum);

    /**
     * 修改一名工作者信息
     * @return
     */
    int web_editoneworker(Worker wo);

    /**
     * 审核通过
     * @return
     */
    int web_shtgworker(String phonenum);

}