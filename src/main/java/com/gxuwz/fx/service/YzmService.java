package com.gxuwz.fx.service;

import java.io.IOException;

/**
 * 验证码业务接口层
 * @author fengx
 */
public interface YzmService {

    /**
     * 向手机发送验证码并保存
     * @param Phonenum
     * @return
     * @throws IOException
     */
    String sendYzm(String Phonenum) throws IOException;

    /**
     * 验证手机号及验证码
     * @param phonenum
     * @param code
     * @return
     */
    boolean verification(String phonenum, String code);

}
