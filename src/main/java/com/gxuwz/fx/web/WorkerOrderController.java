package com.gxuwz.fx.web;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gxuwz.fx.pojo.WorkerOrder;
import com.gxuwz.fx.service.WorkerOrderService;
import net.sf.json.JSONArray;

/**
 * 业务控制层
 * @author fengx
 */
@RestController
public class WorkerOrderController {

    @Autowired
    private WorkerOrderService wos;

    /**
     * 获取所有群抢单
     * @param
     * @return
     * @throws Exception
     */
    @PostMapping("/getallqqd")
    public String getallqqd() {
        String jsonallqqd = wos.getallqqd();
        JSONArray ja = JSONArray.fromObject(jsonallqqd);
        if (ja.size() > 0) {
            return jsonallqqd;
        } else {
            return "noqqd";
        }
    }

    /**
     * 获取所有群抢单
     * @param
     * @return
     * @throws Exception
     */
    @PostMapping("/getallzpd")
    public String getallzpd() {
        String jsonallzpd = wos.getallzpd();
        JSONArray ja = JSONArray.fromObject(jsonallzpd);
        if (ja.size() > 0) {
            return jsonallzpd;
        } else {
            return "nozpd";
        }
    }

    /**
     * 获取一个订单信息
     * @param
     * @return
     * @throws Exception
     */
    @PostMapping("/cxoneorder/{keyid}")
    public String cxoneorder(@PathVariable("keyid") String keyid) {
        String jsononeorder = wos.cxoneorder(keyid);
        JSONArray ja = JSONArray.fromObject(jsononeorder);
        if (ja.size() > 0) {
            return jsononeorder;
        } else {
            return "noxx";
        }

    }

    /**
     * 删除一个订单
     * @return
     * @throws Exception
     */
    @PostMapping("/web_deleteonewoorder/{keyid}")
    public String web_deleteonewoorder(@PathVariable("keyid") String keyid) {
        if (wos.web_deletewoorder(keyid) == 1) {
            return "success";
        } else {
            return "failed";
        }
    }

    /**
     * 获取今日收入明细
     * @param
     * @return
     */
    @PostMapping("/gettoday_srmx/{phonenum}")
    public String gettoday_srmx(@PathVariable("phonenum") String phonenum) {
        ArrayList<WorkerOrder> list = new ArrayList<WorkerOrder>();
        // 数据库中该手机号的订单列表
        list = wos.gettoday_srmx(phonenum);
        JSONArray listArray = JSONArray.fromObject(list);
        String jsonstr = JSONArray.fromObject(listArray).toString();
        return jsonstr;
    }

    /**
     * 获取本周收入明细
     * @param
     * @return
     */
    @PostMapping("/getweek_srmx/{phonenum}")
    public String getweek_srmx(@PathVariable("phonenum") String phonenum) {
        ArrayList<WorkerOrder> list = new ArrayList<WorkerOrder>();
        // 数据库中该手机号的订单列表
        list = wos.getweek_srmx(phonenum);
        JSONArray listArray = JSONArray.fromObject(list);
        String jsonstr = JSONArray.fromObject(listArray).toString();
        return jsonstr;
    }

    /**
     * 获取本月收入明细
     * @param
     * @return
     */
    @PostMapping("/getmonth_srmx/{phonenum}")
    public String getmonth_srmx(@PathVariable("phonenum") String phonenum) {
        ArrayList<WorkerOrder> list = new ArrayList<WorkerOrder>();
        // 数据库中该手机号的订单列表
        list = wos.getmonth_srmx(phonenum);
        JSONArray listArray = JSONArray.fromObject(list);
        String jsonstr = JSONArray.fromObject(listArray).toString();
        return jsonstr;
    }

}
