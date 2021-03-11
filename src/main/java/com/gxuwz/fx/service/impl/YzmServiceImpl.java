package com.gxuwz.fx.service.impl;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gxuwz.fx.mapper.YzmMapper;
import com.gxuwz.fx.service.YzmService;

/**
 * 验证码业务实现层
 * @author fengx
 */
@Service
public class YzmServiceImpl implements YzmService {

    @Autowired
    private YzmMapper ym;

    /**
     * 向手机发送验证码并保存
     * @param Phonenum
     * @return
     * @throws IOException
     */
    @Override
    public String sendYzm(String Phonenum) throws IOException {
        String result = "";
        return result;
    }

    /**
     * 验证手机号及验证码
     * @param phonenum
     * @param code
     * @return
     */
    @Override
    public boolean verification(String phonenum, String code) {
        return ym.existByYzm(phonenum, code);
    }

}
