package com.gxuwz.fx.service;

import java.io.IOException;

public interface YzmService {

    /**
     * 1向手机发送验证码并保存
     *
     * @param Phonenum
     * @return
     * @throws IOException
     */
    public String sendYzm(String Phonenum) throws IOException;

    /**
     * 2验证手机号及验证码
     *
     * @param phonenum
     * @param code
     * @return
     */
    public boolean verification(String phonenum, String code);


}
