package com.gxuwz.fx.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gxuwz.fx.pojo.Yzm;

@Mapper
public interface YzmMapper {

    /**
     * 1 添加验证码
     *
     * @param yzm
     * @return
     */
    public int add(Yzm yzm);

    /**
     * 2判断手机号和验证码是否存在
     *
     * @param phonenum
     * @param code
     * @return
     */
    public boolean existByYzm(String phonenum, String code);


}
