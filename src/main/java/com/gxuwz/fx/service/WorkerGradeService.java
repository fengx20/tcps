package com.gxuwz.fx.service;

/**
 * 配送人员绩效业务接口层
 * @author fengx
 */
public interface WorkerGradeService {

    /**
     * 添加所有数据
     * @param
     */
    void addwg(String phonenum);

    /**
     * 增加已完成数
     * @param phonenum
     */
    void updateywc(String phonenum);

    /**
     * 增加未完成数
     * @param phonenum
     */
    void updatewwc(String phonenum);

    /**
     * 获取所有数据
     * @param phonenum
     * @return
     */
    String getWorkerGrade(String phonenum);

}
