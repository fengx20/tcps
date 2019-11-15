package com.gxuwz.fx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.gxuwz.fx.pojo.ShopOrder;

@Mapper
public interface ShopOrderMapper {
	
	/**
	 * 插入操作
	 */
	
	/**
	 * 添加订单
	 * @param so
	 * @return
	 */
	public int add(ShopOrder so);
	
	
	
	
	/**
	 * 查询操作
	 */
	
	/**
	 * 查询单条订单信息
	 * @param keyid
	 * @return
	 */
	public ShopOrder findShopOrderAllByKeyId(String keyid);
	
	
	
	
	/**
	 * 更新操作
	 */
	
	/**
	 * 更新订单状态：1（已接）
	 * @param keyid
	 * @return
	 */
	public int updateStatusReByKeyId(String keyid);
	
	/**
	 * 更新订单状态：0（未被接或被放弃）
	 * @param keyid
	 * @return
	 */
	public int updateStatusGiByKeyId(String keyid);
	
	/**
	 * 更新订单状态：第二次无人接或别放弃（下一步：直接指派）
	 * @param keyid
	 * @return
	 */
    public int updateStatusInTwice(String keyid);
    
    /**
	 * 更新订单种类（更新为指派单）
	 * @param keyid
	 * @return
	 */
    public int updateSortByKeyId(String keyid);
    
    /**
	 * 更新订单状态（订单已完成）
	 * @param keyid
	 * @return
	 */
	public int updateStatusFi(String keyid);
	
	
	
	
	/**
	 * 判断
	 */
	
	/**
	 * 判断订单是否被工作者接
	 * @param keyid
	 * @return
	 */
	public boolean isnorec(String keyid);
	
	/**
	 * 判断是否为第二次单（是否被放弃过）
	 * @param keyid
	 * @return
	 */
	public boolean findStatusTwiceByKeyId(String keyid);
	
	/**
	 * 判断订单是否已被接
	 * @param keyid
	 * @return
	 */
	public boolean findStatusReByKeyId(String keyid);
	
    /**
	 * 判断订单是否准备被指派（当sort字段为1时）
	 * @param keyid
	 * @return
	 */
	public boolean findSortByKeyId(String keyid);
	
	
	
	
	
	
	
	//后台管理
	
	/**
	 * 1今日订单数
	 * @return
	 */
	public int getallshoptoday();
	
	/**
	 * 2今日完成订单数
	 * @return
	 */
	public int getallshopfitoday();
	
	/**
	 * 3今日指派单数
	 * @return
	 */
	public int getallshopzptoday();
	
	/**
	 * 4本月订单数
	 * @return
	 */
	public int getallshopmonth();
	
	/**
	 * 5总完成订单数
	 * @return
	 */
	public int getallshopfi();
	
	/**
	 * 6总订单数
	 * @return
	 */
	public int getallshopnum();
	
	/**
     * 7获取所有已完成订单
     * @return
     */
    public List<ShopOrder> web_getallywcshoporder();
    
    /**
     * 8获取所有未完成订单
     * @return
     */
    public List<ShopOrder> web_getallwwcshoporder();
    
    /**
     * 9查询一个订单
     * @return
     */
    public List<ShopOrder> web_getoneshoporder(String keyid);
    
    /**
     * 10删除一个订单
     * @return
     */
    public int web_deleteorder(String keyid);

}
