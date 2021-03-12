package com.gxuwz.fx.service;

import com.gxuwz.fx.pojo.ShopOrder;

/**
 * 商家订单业务接口层
 * @author fengx
 */
public interface ShopOrderService {

    /**
     * 添加商家订单
     * @param so
     * @return
     */
    int addOrder(ShopOrder so);

    /**
     * 判断订单是否被接
     * @param keyid
     * @return
     */
    boolean isnorec(String keyid);

    /**
     * 更新订单状态：订单已被接
     * @param keyid
     */
    void updateStatusRe(String keyid);

    /**
     * 更新订单状态：订单被放弃
     * @param keyid
     */
    void updateStatusGi(String keyid);

    /**
     * 查询单个订单
     * @param keyid
     * @return
     */
    ShopOrder findShopOrderAllByKeyId(String keyid);

    /**
     * 判断订单是否已被接
     * @param keyid
     * @return
     */
    boolean findStatusReByKeyId(String keyid);

    /**
     * 更新订单种类
     * @param keyid
     */
    void updateSortByKeyId(String keyid);

    /**
     * 判断订单是否准备被指派
     * @param keyid
     * @return
     */
    boolean findSortByKeyId(String keyid);

    /**
     * 订单已完成
     * @param keyid
     * @return
     */
    int updateStatusFi(String keyid);

    /*PC端*/
    /**
     * 统计订单数
     * @return
     */
    String getallshopnum();

    /**
     * 获取所有已完成订单
     * @return
     */
    String web_getallywcshoporder();

    /**
     * 获取所有未完成订单
     * @return
     */
    String web_getallwwcshoporder();

    /**
     * 查询一个订单
     * @return
     */
    String web_getoneshoporder(String keyid);

    /**
     * 删除一个订单
     * @return
     */
    int web_deleteorder(String keyid);

}
