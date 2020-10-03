package com.gxuwz.fx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.gxuwz.fx.mapper.WorkerGradeMapper;
import com.gxuwz.fx.pojo.WorkerGrade;
import com.gxuwz.fx.service.WorkerGradeService;

@Service
public class WorkerGradeServiceImpl implements WorkerGradeService {

    @Autowired
    WorkerGradeMapper wgm;

    /**
     * 添加所有数据
     *
     * @param
     * @return
     */
    public int addwg(String phonenum) {
        return wgm.add(phonenum);
    }

    /**
     * 增加已完成数
     *
     * @param phonenum
     * @return
     */
    public int updateywc(String phonenum) {
        return wgm.updateywc(phonenum);
    }

    /**
     * 增加未完成数
     *
     * @param phonenum
     * @return
     */
    public int updatewwc(String phonenum) {
        return wgm.updatewwc(phonenum);
    }

    /**
     * 获取所有数据
     *
     * @param phonenum
     * @return
     */
    public String getWorkerGrade(String phonenum) {
        WorkerGrade wg = wgm.getworkergrade(phonenum);
        Gson gson = new Gson();
        String json = gson.toJson(wg);
        return json;
    }

}
