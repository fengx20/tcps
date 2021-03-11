package com.gxuwz.fx.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.gxuwz.fx.pojo.WorkerGrade;

/**
 * 配送人员绩效Mapper接口
 * @author fengx
 */
@Mapper
public interface WorkerGradeMapper {

    /**
     * 添加
     * @param
     * @return
     */
    int add(String phonenum);

    /**
     * 增加已完成数
     * @param phonenum
     * @return
     */
    int updateywc(String phonenum);

    /**
     * 增加未完成数
     * @param phonenum
     * @return
     */
    int updatewwc(String phonenum);

    /**
     * 获取所有数据
     * @param phonenum
     * @return
     */
    WorkerGrade getworkergrade(String phonenum);

}
