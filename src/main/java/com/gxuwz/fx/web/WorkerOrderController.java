package com.gxuwz.fx.web;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gxuwz.fx.pojo.WorkerOrder;
import com.gxuwz.fx.service.WorkerOrderService;
import net.sf.json.JSONArray;

@RestController
public class WorkerOrderController {

    @Autowired
    WorkerOrderService wos;

    /**
     * 1获取所有群抢单
     *
     * @param
     * @return
     * @throws Exception
     */
    @PostMapping("/getallqqd")
    public String getallqqd() throws Exception {
        String jsonallqqd = wos.getallqqd();
        JSONArray ja = JSONArray.fromObject(jsonallqqd);
        if (ja.size() > 0) {
            return jsonallqqd;
        } else {
            return "noqqd";
        }

    }

    /**
     * 2获取所有群抢单
     *
     * @param
     * @return
     * @throws Exception
     */
    @PostMapping("/getallzpd")
    public String getallzpd() throws Exception {
        String jsonallzpd = wos.getallzpd();
        JSONArray ja = JSONArray.fromObject(jsonallzpd);
        if (ja.size() > 0) {
            return jsonallzpd;
        } else {
            return "nozpd";
        }

    }

    /**
     * 3获取一个订单信息
     *
     * @param
     * @return
     * @throws Exception
     */
    @PostMapping("/cxoneorder/{keyid}")
    public String cxoneorder(@PathVariable("keyid") String keyid) throws Exception {
        String jsononeorder = wos.cxoneorder(keyid);
        JSONArray ja = JSONArray.fromObject(jsononeorder);
        if (ja.size() > 0) {
            return jsononeorder;
        } else {
            return "noxx";
        }

    }

    /**
     * 4删除一个订单
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/web_deleteonewoorder/{keyid}")
    public String web_deleteonewoorder(@PathVariable("keyid") String keyid) throws Exception {
        if (wos.web_deletewoorder(keyid) == 1) {
            return "success";
        } else {
            return "failed";
        }
    }


    /**
     * 获取今日收入明细
     *
     * @param
     * @return
     */
    @PostMapping("/gettoday_srmx/{phonenum}")
    public String gettoday_srmx(@PathVariable("phonenum") String phonenum) throws Exception {
        ArrayList<WorkerOrder> list = new ArrayList<WorkerOrder>();
        list = wos.gettoday_srmx(phonenum);  //数据库中该手机号的订单列表
        JSONArray listArray = JSONArray.fromObject(list);
        String jsonstr = JSONArray.fromObject(listArray).toString();
        return jsonstr;
    }

    /**
     * 获取本周收入明细
     *
     * @param
     * @return
     */
    @PostMapping("/getweek_srmx/{phonenum}")
    public String getweek_srmx(@PathVariable("phonenum") String phonenum) throws Exception {
        ArrayList<WorkerOrder> list = new ArrayList<WorkerOrder>();
        list = wos.getweek_srmx(phonenum);  //数据库中该手机号的订单列表
        JSONArray listArray = JSONArray.fromObject(list);
        String jsonstr = JSONArray.fromObject(listArray).toString();
        return jsonstr;
    }

    /**
     * 获取本月收入明细
     *
     * @param
     * @return
     */
    @PostMapping("/getmonth_srmx/{phonenum}")
    public String getmonth_srmx(@PathVariable("phonenum") String phonenum) throws Exception {
        ArrayList<WorkerOrder> list = new ArrayList<WorkerOrder>();
        list = wos.getmonth_srmx(phonenum);  //数据库中该手机号的订单列表
        JSONArray listArray = JSONArray.fromObject(list);
        String jsonstr = JSONArray.fromObject(listArray).toString();
        return jsonstr;
    }
}
