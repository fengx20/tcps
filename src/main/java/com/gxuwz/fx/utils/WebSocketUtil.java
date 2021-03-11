package com.gxuwz.fx.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gxuwz.fx.server.WebSocketServer;

@Service
public class WebSocketUtil {

    @Autowired
    WebSocketServer wss;

    public void sendOrder(String phonenum, String order) {
        wss.sendToUser(phonenum, order);
    }

    public String getNowTime() {
        return wss.getNowTime();
    }

}
