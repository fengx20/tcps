package com.gxuwz.fx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.gxuwz.fx.mapper.WorkerGradeMapper;
import com.gxuwz.fx.pojo.WorkerGrade;
import com.gxuwz.fx.service.WorkerGradeService;

/**
 * 配送人员业绩业务实现层
 * @author fengx
 */
@Service
public class WorkerGradeServiceImpl implements WorkerGradeService {

    @Autowired
    private WorkerGradeMapper wgm;

    /**
     * 添加所有数据
     * @param
     */
    public void addwg(String phonenum) {
        wgm.add(phonenum);
    }

    /**
     * 增加已完成数
     * @param phonenum
     */
    public void updateywc(String phonenum) {
        wgm.updateywc(phonenum);
    }

    /**
     * 增加未完成数
     * @param phonenum
     */
    public void updatewwc(String phonenum) {
        wgm.updatewwc(phonenum);
    }

    /**
     * 获取所有数据
     * @param phonenum
     * @return
     */
    public String getWorkerGrade(String phonenum) {
        WorkerGrade wg = wgm.getworkergrade(phonenum);
        Gson gson = new Gson();
        return gson.toJson(wg);
    }

}