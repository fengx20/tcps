package com.gxuwz.fx.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gxuwz.fx.service.YzmService;
import java.io.IOException;

/**
 * 业务控制层
 * @author fengx
 */
@RestController
public class YzmController {

    @Autowired
    private YzmService ys;

    /**
     * 向手机发送验证码
     * @param phonenum
     * @return
     * @throws Exception
     */
    @PostMapping("/sendyzm/{phonenum}")
    public String sendyzm(@PathVariable("phonenum") String phonenum) throws IOException {
        // 发送验证码
        if (ys.sendYzm(phonenum).equals("200")) {
            return "success";
        } else {
            return "failed";
        }
    }

}