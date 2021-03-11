package com.gxuwz.fx.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.gxuwz.fx.mapper.ShopOrderMapper;
import com.gxuwz.fx.pojo.ShopOrder;
import com.gxuwz.fx.service.ShopOrderService;
import net.sf.json.JSONObject;

/**
 * 商家订单业务实现层
 * @author fengx
 */
@Service
public class ShopOrderServiceImpl implements ShopOrderService {

    @Autowired
    private ShopOrderMapper som;
    @Resource
    private RedisTemplate<String, Object> rt;

    /**
     * 添加商家订单
     * @param so
     * @return
     */
    @Override
    public int addOrder(ShopOrder so) {
        return som.add(so);
    }

    /**
     * 订单是否被接
     * @param keyid
     * @return
     */
    @Override
    public boolean isnorec(String keyid) {
        return som.isnorec(keyid);
    }

    /**
     * 更新订单状态：订单已被接
     * @param keyid
     * @return
     */
    @Override
    public int updateStatusRe(String keyid) {
        return som.updateStatusReByKeyId(keyid);
    }

    /**
     * 更新订单状态：订单被放弃
     * @param keyid
     * @return
     */
    @Override
    public int updateStatusGi(String keyid) {
        return som.updateStatusGiByKeyId(keyid);
    }

    /**
     * 查询单个订单所有信息
     * @param keyid
     * @return
     */
    @Override
    public ShopOrder findShopOrderAllByKeyId(String keyid) {
        return som.findShopOrderAllByKeyId(keyid);
    }

    /**
     * 判断是否为第二次单
     * @param keyid
     * @return
     */
    public boolean findStatusTwiceByKeyId(String keyid) {
        return som.findStatusTwiceByKeyId(keyid);
    }

    /**
     * 判断订单是否已被接
     * @param keyid
     * @return
     */
    public boolean findStatusReByKeyId(String keyid) {
        return som.findStatusReByKeyId(keyid);
    }

    /**
     * 更新订单状态：第二次无人抢或被放弃
     * @param keyid
     * @return
     */
    public int updateStatusInTwice(String keyid) {
        return som.updateStatusInTwice(keyid);
    }

    /**
     * 更新订单种类
     * @param keyid
     * @return
     */
    public int updateSortByKeyId(String keyid) {
        return som.updateSortByKeyId(keyid);
    }

    /**
     * 判断订单是否准备被指派
     * @param keyid
     * @return
     */
    public boolean findSortByKeyId(String keyid) {
        return som.findSortByKeyId(keyid);
    }

    /**
     * 订单已完成
     * @param keyid
     * @return
     */
    public int updateStatusFi(String keyid) {
        return som.updateStatusFi(keyid);
    }

    /*PC端*/
    /**
     * 统计订单数
     * @return
     */
    @Override
    public String getallshopnum() {
        int numtd = som.getallshoptoday();
        // 订单数
        String numtdstr = String.valueOf(numtd);
        int finumtd = som.getallshopfitoday();
        // 完成数
        String finumtdstr = String.valueOf(finumtd);
        int zpnumtd = som.getallshopzptoday();
        // 指派订单数
        String zpnumtdstr = String.valueOf(zpnumtd);
        int nummonth = som.getallshopmonth();
        // 本月订单数
        String nummonthstr = String.valueOf(nummonth);
        int finumall = som.getallshopfi();
        // 总完成订单数
        String finumallstr = String.valueOf(finumall);
        int numall = som.getallshopnum();
        // 总订单数
        String numallstr = String.valueOf(numall);
        JSONObject json = new JSONObject();
        json.put("numtdstr", numtdstr);
        json.put("finumtdstr", finumtdstr);
        json.put("zpnumtdstr", zpnumtdstr);
        json.put("nummonthstr", nummonthstr);
        json.put("finumallstr", finumallstr);
        json.put("numallstr", numallstr);
        String jsonnum = json.toString();
        return jsonnum;
    }

    /**
     * 获取所有已完成订单
     * @return
     */
    public String web_getallywcshoporder() {
        List<ShopOrder> list = som.web_getallywcshoporder();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    /**
     * 获取所有未完成订单
     * @return
     */
    public String web_getallwwcshoporder() {
        List<ShopOrder> list = som.web_getallwwcshoporder();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    /**
     * 查询一个订单
     * @return
     */
    public String web_getoneshoporder(String phonenum) {
        List<ShopOrder> list = som.web_getoneshoporder(phonenum);
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    /**
     * 删除一个订单
     * @return
     */
    public int web_deleteorder(String keyid) {
        return som.web_deleteorder(keyid);
    }

}
