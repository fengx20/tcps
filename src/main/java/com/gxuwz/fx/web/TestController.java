package com.gxuwz.fx.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Fengx
 * @date 2021/3/8 0008
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "你好";
    }

}
