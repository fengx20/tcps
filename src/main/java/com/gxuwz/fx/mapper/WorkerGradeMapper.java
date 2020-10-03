package com.gxuwz.fx.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gxuwz.fx.pojo.WorkerGrade;

@Mapper
public interface WorkerGradeMapper {

    /**
     * 添加
     *
     * @param
     * @return
     */
    public int add(String phonenum);

    /**
     * 增加已完成数
     *
     * @param phonenum
     * @return
     */
    public int updateywc(String phonenum);

    /**
     * 增加未完成数
     *
     * @param phonenum
     * @return
     */
    public int updatewwc(String phonenum);

    /**
     * 获取所有数据
     *
     * @param phonenum
     * @return
     */
    public WorkerGrade getworkergrade(String phonenum);

}
