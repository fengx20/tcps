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

@Service
public class ShopOrderServiceImpl implements ShopOrderService {
	
	@Autowired ShopOrderMapper som;
	
	@Resource
	private RedisTemplate<String,Object> rt;
	
	/**
	 * 1添加商家订单
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
	 * 2更新订单状态：订单已被接
	 * @param keyid
	 * @return
	 */
	@Override
	public int updateStatusRe(String keyid) {
		return som.updateStatusReByKeyId(keyid);
	}
	
	/**
	 * 3更新订单状态：订单被放弃
	 * @param keyid
	 * @return
	 */
	@Override
	public int updateStatusGi(String keyid) {
		return som.updateStatusGiByKeyId(keyid);
	}
	
	/**
	 * 4查询单个订单所有信息
	 * @param keyid
	 * @return
	 */
	@Override
	public ShopOrder findShopOrderAllByKeyId(String keyid) {
		return som.findShopOrderAllByKeyId(keyid);
	}
	
	/**
	 * 5判断是否为第二次单
	 * @param keyid
	 * @return
	 */
	public boolean findStatusTwiceByKeyId(String keyid) {
		return som.findStatusTwiceByKeyId(keyid);
	}
	
	/**
	 * 6判断订单是否已被接
	 * @param keyid
	 * @return
	 */
	public boolean findStatusReByKeyId(String keyid) {
		return som.findStatusReByKeyId(keyid);
	}
	
	/**
	 * 7更新订单状态：第二次无人抢或被放弃
	 * @param keyid
	 * @return
	 */
    public int updateStatusInTwice(String keyid) {
    	return som.updateStatusInTwice(keyid);
    }
	
	/**
	 * 8更新订单种类
	 * @param keyid
	 * @return
	 */
    public int updateSortByKeyId(String keyid) {
    	return som.updateSortByKeyId(keyid);
    }
    
    /**
	 * 9判断订单是否准备被指派
	 * @param keyid
	 * @return
	 */
	public boolean findSortByKeyId(String keyid) {
		return som.findSortByKeyId(keyid);
	}
	
	/**
	 * 10订单已完成
	 * @param keyid
	 * @return
	 */
	public int updateStatusFi(String keyid) {
		return som.updateStatusFi(keyid);
	}
	
	
	//Web端
	
	/**
	 * 1统计订单数
	 * @return
	 */
	@Override
	public String getallshopnum() {
		int numtd = som.getallshoptoday();
		String numtdstr = String.valueOf(numtd);  //订单数
		int finumtd = som.getallshopfitoday();
		String finumtdstr = String.valueOf(finumtd);  //完成数
		int zpnumtd = som.getallshopzptoday();
		String zpnumtdstr = String.valueOf(zpnumtd);   //指派订单数
		int nummonth = som.getallshopmonth();
		String nummonthstr = String.valueOf(nummonth);   //本月订单数
		int finumall = som.getallshopfi();
		String finumallstr = String.valueOf(finumall);   //总完成订单数
		int numall = som.getallshopnum();
		String numallstr = String.valueOf(numall);   //总订单数
		
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
     * 2获取所有已完成订单
     * @return
     */
    public String web_getallywcshoporder() {
    	List<ShopOrder> list = som.web_getallywcshoporder();
		Gson gson = new Gson();  
	    String json = gson.toJson(list); 
        return json;
    }
    
    /**
     * 3获取所有未完成订单
     * @return
     */
    public String web_getallwwcshoporder() {
    	List<ShopOrder> list = som.web_getallwwcshoporder();
		Gson gson = new Gson();  
	    String json = gson.toJson(list); 
        return json;
    }
	
    /**
     * 4查询一个订单
     * @return
     */
    public String web_getoneshoporder(String phonenum) {
    	List<ShopOrder> list = som.web_getoneshoporder(phonenum);
		Gson gson = new Gson();  
	    String json = gson.toJson(list); 
        return json;
    }
    
    /**
     * 5删除一个订单
     * @return
     */
    public int web_deleteorder(String keyid) {
    	return som.web_deleteorder(keyid);
    }
	
	
	
	
	
	
}
