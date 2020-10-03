package com.gxuwz.fx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.gxuwz.fx.mapper.WorkerMapper;
import com.gxuwz.fx.pojo.Worker;
import com.gxuwz.fx.service.WorkerService;

@Service
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    WorkerMapper wm;


    /**
     * 1判断工作者手机号是否已存在
     *
     * @param phonenum
     * @return
     */
    @Override
    public boolean existph(String phonenum) {
        return wm.existByPhonenum(phonenum);
    }

    /**
     * 2 工作者注册
     *
     * @param worker
     * @return
     */
    @Override
    public int regist(Worker worker) {
        return wm.add(worker);
    }

    /**
     * 3工作者登录验证
     *
     * @param phonenum
     * @param password
     * @return
     */
    @Override
    public boolean login(String phonenum, String password) {
        return wm.existByWorker(phonenum, password);
    }

    /**
     * 4工作者是否已认证
     *
     * @param Phonenum
     * @return
     */
    @Override
    public boolean certification(String Phonenum) {
        return wm.certification(Phonenum);
    }

    /**
     * 5判断是否存在此身份证号
     *
     * @param phonenum
     * @return
     */
    public boolean existIdCardNum(String phonenum, String idcardnum) {
        return wm.existIdCardNum(phonenum, idcardnum);
    }

    /**
     * 6修改密码
     *
     * @param phonenum
     * @return
     */
    public int updatepsd(String phonenum, String password) {
        return wm.updatepsd(phonenum, password);
    }

    /**
     * 7 工作者是否正在审核
     *
     * @param Phonenum
     * @return
     */
    @Override
    public boolean woreview(String Phonenum) {
        return wm.review(Phonenum);
    }

    /**
     * 8 工作者上传信息认证
     */
    @Override
    public int certificationupload(Worker woker) {
        return wm.certificationupdate(woker);
    }

    /**
     * 9获取工作者姓名
     *
     * @param phonenum
     * @return
     */
    @Override
    public String getworkername(String phonenum) {
        return wm.getworkername(phonenum);
    }

    /**
     * 10修改资料
     *
     * @param
     * @return
     */
    @Override
    public int updatezl(Worker worker) {
        return wm.updatezl(worker);
    }

    //Web端

    /**
     * 1获取所有已认证工作者
     *
     * @return
     */
    @Override
    public String web_getallcertworker() {
        List<Worker> list = wm.web_getallcertworker();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    /**
     * 2获取所有未认证工作者
     *
     * @return
     */
    @Override
    public String web_getallnocertworker() {
        List<Worker> list = wm.web_getallnocertworker();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    /**
     * 3获取所有已停用工作者
     *
     * @return
     */
    @Override
    public String web_getallstopworker() {
        List<Worker> list = wm.web_getallstopworker();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    /**
     * 3获取所有正在审核工作者
     *
     * @return
     */
    public String web_getallshworker() {
        List<Worker> list = wm.web_getallshworker();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    /**
     * 4停用工作者
     *
     * @return
     */
    @Override
    public int web_stopworker(String phonenum) {
        return wm.web_stopworker(phonenum);
    }

    /**
     * 5获取一名工作者
     *
     * @return
     */
    @Override
    public String web_getoneworker(String phonenum) {
        List<Worker> list = wm.web_getoneworker(phonenum);
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    /**
     * 获取一名工作者信息（监控中心）
     *
     * @return
     */
    public Worker web_getoneworkerjk(String phonenum) {
        return wm.web_getoneworkerjk(phonenum);
    }

    /**
     * 6修改一名工作者信息
     *
     * @return
     */
    @Override
    public int web_editoneworker(Worker wo) {
        return wm.web_editoneworker(wo);
    }

    /**
     * 7审核通过
     *
     * @return
     */
    public int web_shtgworker(String phonenum) {
        return wm.web_shtgworker(phonenum);
    }


}
