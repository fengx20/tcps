package com.gxuwz.fx.service;

import com.gxuwz.fx.pojo.ShopOrder;

public interface ShopOrderService {

	/**
	 * 1添加商家订单
	 * @param so
	 * @return
	 */
	public int addOrder(ShopOrder so);

	/**
	 * 订单是否被接
	 * @param keyid
	 * @return
	 */
	public boolean isnorec(String keyid);

	/**
	 * 2更新订单状态：订单已被接
	 * @param keyid
	 * @return
	 */
	public int updateStatusRe(String keyid);

	/**
	 * 3更新订单状态：订单被放弃
	 * @param keyid
	 * @return
	 */
	public int updateStatusGi(String keyid);

	/**
	 * 4查询单个订单
	 * @param keyid
	 * @return
	 */
	public ShopOrder findShopOrderAllByKeyId(String keyid);

	/**
	 * 5判断是否为第二次单
	 * @param keyid
	 * @return
	 */
	public boolean findStatusTwiceByKeyId(String keyid);

	/**
	 * 6判断订单是否已被接
	 * @param keyid
	 * @return
	 */
	public boolean findStatusReByKeyId(String keyid);

	/**
	 * 7更新订单状态：第二次无人接或别放弃
	 * @param keyid
	 * @return
	 */
    public int updateStatusInTwice(String keyid);

	/**
	 * 8更新订单种类
	 * @param keyid
	 * @return
	 */
    public int updateSortByKeyId(String keyid);

    /**
	 * 9判断订单是否准备被指派
	 * @param keyid
	 * @return
	 */
	public boolean findSortByKeyId(String keyid);

	/**
	 * 10订单已完成
	 * @param keyid
	 * @return
	 */
	public int updateStatusFi(String keyid);



	//Web端

	/**
	 * 1统计订单数
	 * @return
	 */
	public String getallshopnum();

	/**
     * 2获取所有已完成订单
     * @return
     */
    public String web_getallywcshoporder();

    /**
     * 3获取所有未完成订单
     * @return
     */
    public String web_getallwwcshoporder();

    /**
     * 4查询一个订单
     * @return
     */
    public String web_getoneshoporder(String keyid);

    /**
     * 5删除一个订单
     * @return
     */
    public int web_deleteorder(String keyid);

}
