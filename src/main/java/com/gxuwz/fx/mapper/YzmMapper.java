package com.gxuwz.fx.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.gxuwz.fx.pojo.Yzm;

/**
 * 验证码Mapper接口
 * @author fengx
 */
@Mapper
public interface YzmMapper {

    /**
     * 添加验证码
     * @param yzm
     * @return
     */
    int add(Yzm yzm);

    /**
     * 判断手机号和验证码是否存在
     * @param phonenum
     * @param code
     * @return
     */
    boolean existByYzm(String phonenum, String code);

}
