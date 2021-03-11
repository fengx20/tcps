package com.gxuwz.fx.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.gxuwz.fx.pojo.ShopOrder;

/**
 * 商家订单Mapper接口
 * @author fengx
 */
@Mapper
public interface ShopOrderMapper {

    /*app端*/
    /**
     * 添加订单
     * @param so
     * @return
     */
    int add(ShopOrder so);

    /**
     * 查询单条订单信息
     * @param keyid
     * @return
     */
    ShopOrder findShopOrderAllByKeyId(String keyid);

    /**
     * 更新订单状态：1（已被接）
     * @param keyid
     * @return
     */
    int updateStatusReByKeyId(String keyid);

    /**
     * 更新订单状态：0（未被接或被放弃）
     * @param keyid
     * @return
     */
    int updateStatusGiByKeyId(String keyid);

    /**
     * 更新订单状态：第二次无人接或被放弃（下一步：直接指派）
     * @param keyid
     * @return
     */
    int updateStatusInTwice(String keyid);

    /**
     * 更新订单种类（更新为指派单）
     * @param keyid
     * @return
     */
    int updateSortByKeyId(String keyid);

    /**
     * 更新订单状态（订单已完成）
     * @param keyid
     * @return
     */
    int updateStatusFi(String keyid);

    /**
     * 判断订单是否已被工作者接
     * @param keyid
     * @return
     */
    boolean isnorec(String keyid);

    /**
     * 判断是否为第二次单（是否被放弃过）
     * @param keyid
     * @return
     */
    boolean findStatusTwiceByKeyId(String keyid);

    /**
     * 判断订单是否已被接
     * @param keyid
     * @return
     */
     boolean findStatusReByKeyId(String keyid);

    /**
     * 判断订单是否准备被指派（当sort字段为1时）
     * @param keyid
     * @return
     */
    boolean findSortByKeyId(String keyid);

    /*PC端*/
    /**
     * 今日订单数
     * @return
     */
    int getallshoptoday();

    /**
     * 今日完成订单数
     * @return
     */
    int getallshopfitoday();

    /**
     * 今日指派单数
     * @return
     */
    int getallshopzptoday();

    /**
     * 本月订单数
     * @return
     */
    int getallshopmonth();

    /**
     * 总完成订单数
     * @return
     */
    int getallshopfi();

    /**
     * 总订单数
     * @return
     */
    int getallshopnum();

    /**
     * 获取所有已完成订单
     * @return
     */
    List<ShopOrder> web_getallywcshoporder();

    /**
     * 获取所有未完成订单
     * @return
     */
    List<ShopOrder> web_getallwwcshoporder();

    /**
     * 查询一个订单
     * @return
     */
    List<ShopOrder> web_getoneshoporder(String keyid);

    /**
     * 删除一个订单
     * @return
     */
    int web_deleteorder(String keyid);

}