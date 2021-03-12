package com.gxuwz.fx.web;

import com.gxuwz.fx.pojo.ShopOrder;
import com.gxuwz.fx.pojo.WorkerAddress;
import com.gxuwz.fx.pojo.WorkerOrder;
import com.gxuwz.fx.service.*;
import com.gxuwz.fx.utils.RedisUtil;
import com.gxuwz.fx.utils.WebSocketUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

/**
 * 商家订单业务控制层
 * @author fengx
 */
@RestController
public class ShopOrderController {

    @Autowired
    private ShopOrderService sos;
    @Autowired
    private WorkerService ws;
    @Autowired
    private WorkerAddressService was;
    @Autowired
    private WorkerOrderService wos;
    @Autowired
    private WebSocketUtil wsu;
    @Autowired
    private RedisUtil ru;
    @Autowired
    private WorkerGradeService wgs;

    /**
     * 商家查询订单
     * @param keyid
     * @return
     */
    @PostMapping("/shopcxorder/{keyid}")
    public String shopcxOrder(@PathVariable("keyid") String keyid){
        // 判断此订单是否已被接单
        if (sos.isnorec(keyid)) {
            return "isno";
        } else {
            // 查询此工作者订单并返回
            JSONObject obj = JSONObject.fromObject(wos.shfindWorkerOrderByKey(keyid));
            return obj.toString();
        }
    }

    /**
     * 群抢单：工作者查看订单详情（从缓存中）
     * @param keyid
     * @return
     */
    @PostMapping("/findOrderById/{keyid}")
    public String findOrderById(@PathVariable("keyid") String keyid) {
        // 此订单抢单还没有结束（判断订单是否还存在）
        if (ru.iskey(keyid)) {
            new JSONObject();
            // 获得缓存中该订单的数据
            JSONObject obj = JSONObject.fromObject(ru.getjson(keyid));
            // 如果订单还没被抢
            if (obj.getInt("status") == 0) {
                // 返回订单数据
                return ru.getjson(keyid);
            } else {
                // 订单已被抢
                return "isrush";
            }
        } else {
            return "isrush";
        }
    }

    /**
     * 抢到单工作者查看订单详情(从数据库查询)
     * @param keyid
     * @return
     */
    @PostMapping("/getOrderById/{keyid}")
    public String getOrderById(@PathVariable("keyid") String keyid) {
        // 查询此订单数据
        JSONObject json = JSONObject.fromObject(sos.findShopOrderAllByKeyId(keyid));
        // json对象转换为json字符串
        return json.toString();
    }

    /**
     * 工作者接指派的单
     * @param phonenum
     * @return
     */
    @PostMapping("/acceptorder/{phonenum}/{keyid}")
    public String acceptorder(@PathVariable("phonenum") String phonenum, @PathVariable("keyid") String keyid) {
        if (wos.updateStatusToRe(phonenum, keyid) == 1) {
            sos.updateStatusRe(keyid);
            return "success";
        } else {
            return "failed";
        }
    }

    /**
     * 工作者开始配送
     * @param phonenum
     * @return
     */
    @PostMapping("/startorder/{phonenum}/{keyid}")
    public String startorder(@PathVariable("phonenum") String phonenum, @PathVariable("keyid") String keyid) {
        // 工作者只能有一个配送中的订单
        if (!wos.findworkpsz(phonenum)) {
            if (wos.updateStatusToWo(phonenum, keyid) == 1) {
                return "success";
            } else {
                return "failed";
            }
        } else {
            return "onlyone";
        }
    }

    /**
     * 工作者获取配送中订单
     * @param phonenum
     * @return
     */
    @PostMapping("/getpszorder/{phonenum}")
    public String getpszorder(@PathVariable("phonenum") String phonenum) {
        // 工作者为工作状态
        if (was.iswork(phonenum)) {
            // 查询该工作者地理位置(传到前台计算距离)
            WorkerAddress wa = was.select_wolnglat(phonenum);
            ArrayList<WorkerOrder> list;
            // 获取该工作者配送中订单
            list = wos.findWorkerOrderAllByPhWo(phonenum);
            // List数组转为Json数组
            JSONArray listArray = JSONArray.fromObject(list);
            if (listArray.size() > 0) {
                for (int i = 0; i < listArray.size(); i++) {
                    // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                    JSONObject job = listArray.getJSONObject(i);
                    job.put("wolng", wa.getLongitude());
                    job.put("wolat", wa.getLatitude());
                }
            }
            return JSONArray.fromObject(listArray).toString();
        } else {
            return "isrelax";
        }
    }

    /**
     * 工作者完成配送
     * @param phonenum
     * @return
     */
    @PostMapping("/endorder/{phonenum}/{keyid}")
    public String endorder(@PathVariable("phonenum") String phonenum, @PathVariable("keyid") String keyid) {
        if (wos.updateStatusRu(phonenum, keyid) == 1) {
            if (sos.updateStatusFi(keyid) == 1) {
                wgs.updateywc(phonenum);
                return "success";
            } else {
                return "failed";
            }
        } else {
            return "failed";
        }
    }

    /**
     * 配送中工作者查看订单详情(从数据库查询)
     * @param keyid
     * @return
     */
    @PostMapping("/getPszOrderById/{keyid}/{phonenum}")
    public String getPszOrderById(@PathVariable("keyid") String keyid, @PathVariable("phonenum") String phonenum) {
        // 查询此订单数据
        JSONObject json = JSONObject.fromObject(sos.findShopOrderAllByKeyId(keyid));
        WorkerAddress wa = was.select_wolnglat(phonenum);
        json.put("wolng", wa.getLongitude());
        json.put("wolat", wa.getLatitude());
        json.put("wophonenum", phonenum);
        // json对象转换为json字符串
        return json.toString();
    }

    /**
     * 工作者获取已配送订单
     * @param phonenum
     * @return
     */
    @PostMapping("/getypsorder/{phonenum}")
    public String getypsorder(@PathVariable("phonenum") String phonenum) {
        // 判断是否为工作状态
        if (was.iswork(phonenum)) {
            ArrayList<WorkerOrder> list;
            // 获取该工作者配送中订单
            list = wos.findWorkerEndOrderAllByPh(phonenum);
            JSONArray listArray = JSONArray.fromObject(list);
            return JSONArray.fromObject(listArray).toString();
        } else {
            return "isrelax";
        }
    }

    /**
     * 抢单模式：商家订单接口
     * @param so
     * @return
     */
    @PostMapping("/addorder")
    public String addOrder(@RequestBody ShopOrder so) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        so.setSystime(dateString);
        // 订单写入数据库
        if (sos.addOrder(so) == 1) {
            // 查询订单三公里内在接单的工作者
            String jsonworker = was.getWorkWa(so.getLongitude(), so.getLatitude());
            // Json字符串转为Json数组
            JSONArray ja = JSONArray.fromObject(jsonworker);
            // 遍历查询到的工作者手机号向移动端发送数据
            if (ja.size() > 0) {
                for (int i = 0; i < ja.size(); i++) {
                    // 遍历jsonarray 数组，把每一个对象转成 json 对象
                    JSONObject job = ja.getJSONObject(i);
                    // 得到每个对象中的属性值
                    double lng2 = (double) job.get("longitude");
                    double lat2 = (double) job.get("latitude");
                    String phonenum = (String) job.get("phonenum");
                    // redis中存储的key值
                    int radomid = (int) (Math.random() * 1000000000);
                    String id = String.valueOf(radomid);
                    // 实体类转为Json对象，将工作者信息放入生成工作者订单放入缓存中
                    JSONObject json = JSONObject.fromObject(so);
                    json.put("wolng", lng2);
                    json.put("wolat", lat2);
                    json.put("phonenum", phonenum);
                    String jsonorder = json.toString();
                    // 抢单模式：订单进入缓存，30分钟
                    ru.setattime(id, jsonorder, 1800);
                    wsu.sendOrder(phonenum, "订单来啦");
                }
                // 下单成功
                return "success";
            } else {
                // 下单失败
                return "no";
            }
        } else {
            // 订单未写入数据库（保证订单进入数据库保存）
            return "ordernosave";
        }
    }

    /**
     * 获取缓存中该工作者的订单
     * @param
     * @return
     */
    @PostMapping("/getorder/{phonenum}")
    public String getOrder(@PathVariable("phonenum") String phonenum) {
        // 判断是否为工作状态
        if (was.iswork(phonenum)) {
            // 该手机号的订单列表
            ArrayList<String> list = new ArrayList<>();
            // 连接本地的Redis服务
            @SuppressWarnings("resource")
            Jedis jedis = new Jedis("localhost", 6379);
            // 设置密码
            jedis.auth("19971114abc");
            System.out.println("连接成功");
            // 查看服务是否运行
            System.out.println("服务正在运行: " + jedis.ping());
            // 获取缓存中所有key
            Set<String> keys = jedis.keys("*");
            // 遍历每一个key，获得value
            for (String key : keys) {
                // 处理缓存中乱码数据
                if (key.length() < 8) {
                    ru.delstr(key);
                } else {
                    // 获取每个key的value值
                    String keyvalue = ru.getjson(key);
                    // 字符串转为json对象
                    JSONObject jsObj = JSONObject.fromObject(keyvalue);
                    // 设置redisid为缓存中每一个数据的key
                    jsObj.put("redisid", key);
                    String all = jsObj.toString();
                    // 找出此工作者的订单
                    if (phonenum.equals(jsObj.get("phonenum").toString())) {
                        // 添加到此手机号的订单列表
                        list.add(all);
                    }
                }
            }
            // list数组转为Json数组
            JSONArray listArray = JSONArray.fromObject(list);
            return JSONArray.fromObject(listArray).toString();
        } else {
            return "isrelax";
        }
    }

    /**
     * 获取数据库中中该工作者的订单（订单-待配送）（订单-配送中）
     * @param
     * @return
     */
    @PostMapping("/getdborder/{phonenum}/{sort}")
    public String getDbOrder(@PathVariable("phonenum") String phonenum, @PathVariable("sort") String sort) {
        // 工作者为工作状态
        if (was.iswork(phonenum)) {
            // 查询该工作者地理位置(传到前台计算距离)
            WorkerAddress wa = was.select_wolnglat(phonenum);
            ArrayList<WorkerOrder> list = new ArrayList<>();
            // 查询的是非指派的订单（订单-待配送）
            if (sort.equals("nozhipai")) {
                // 数据库中该手机号的订单列表
                list = wos.findWorkerOrderAllByPh(phonenum);
                // 查询的是指派单
            } else if (sort.equals("zhipai")) {
                list = wos.findWorkerOrderAllByPhSo(phonenum);
            }
            JSONArray listArray = JSONArray.fromObject(list);
            // 工作者订单添加工作者地理位置
            if (listArray.size() > 0) {  //
                for (int i = 0; i < listArray.size(); i++) {
                    // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                    JSONObject job = listArray.getJSONObject(i);
                    job.put("wolng", wa.getLongitude());
                    job.put("wolat", wa.getLatitude());
                }
            }
            return JSONArray.fromObject(listArray).toString();
        } else {
            return "isrelax";
        }
    }

    /**
     * 群抢单：工作者进行抢单
     * @param
     * @return
     */
    @PostMapping("/rushOrderById/{datastr}")
    public String rushOrderById(@PathVariable("datastr") String datastr) {
        // 字符串转为Json对象
        JSONObject getdata = JSONObject.fromObject(datastr);
        // 缓存中此订单抢单未结束
        if (ru.iskey(getdata.getString("redisid"))) {
            new JSONObject();
            // 数据库中该订单未被接
            if (!sos.findStatusReByKeyId(getdata.getString("keyid"))) {
                // 更新订单状态：已被抢
                sos.updateStatusRe(getdata.getString("keyid"));
                // 获取工作者姓名
                String name = ws.getworkername(getdata.getString("phonenum"));
                // 查询此订单数据
                JSONObject json = JSONObject.fromObject(sos.findShopOrderAllByKeyId(getdata.getString("keyid")));
                // 订单状态：未配送
                json.put("status", 0);
                // 类别：群抢单
                json.put("sort", 0);
                // 加入抢单者手机号
                json.put("phonenum", getdata.getString("phonenum"));
                // 加入抢单者姓名
                json.put("name", name);
                // 工作者抢单时间
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateString = formatter.format(date);
                json.put("systime", dateString);
                // 转换成WorkerOrder实体类，生成工作者订单存入数据库
                WorkerOrder wo = (WorkerOrder) JSONObject.toBean(json, WorkerOrder.class);
                // 工作者订单在数据库不存在
                if (!wos.existBykeyid(getdata.getString("keyid"), getdata.getString("phonenum"))) {
                    // 工作者的订单写入数据库
                    wos.addOrder(wo);
                } else {
                    // 已存在的订单修改工作者信息
                    wos.updateGiOrder(wo);
                }
                // json对象转换为json字符串
                String jsonorder = json.toString();
                // 更新缓存中的订单数据：订单已被抢，5s后自动删除
                ru.setattime(getdata.getString("redisid"), jsonorder, 5);
                return "success";
            } else {
                // 已被抢，直接删除缓存中订单
                ru.delstr(getdata.getString("redisid"));
                // 订单已被抢
                return "isrush";
            }
        } else {
            // 返回时删除这一订单项
            return "isrush";
        }
    }

    /**
     * 工作者放弃订单（第二次无人抢、被放弃则指派）
     * @param keyid
     * @return
     */
    @PostMapping("/giveuporder/{phonenum}/{keyid}")
    public String giveuporder(@PathVariable("phonenum") String phonenum, @PathVariable("keyid") String keyid) {
        if (!sos.findSortByKeyId(keyid)) {
            // sort:0 -> 1
            sos.updateSortByKeyId(keyid);
            // status:1->0
            sos.updateStatusGi(keyid);
            // 更新工作者订单状态 status:0->2
            wos.updateStatusGi(phonenum, keyid);
            // 查询订单信息
            JSONObject json = JSONObject.fromObject(sos.findShopOrderAllByKeyId(keyid));
            // 转换成WorkerOrder实体类
            ShopOrder so = (ShopOrder) JSONObject.toBean(json, ShopOrder.class);
            // 查询订单三公里内所有在接单的工作者
            String jsonworker = was.getWorkWa(json.getDouble("longitude"), json.getDouble("latitude"));
            JSONArray ja = JSONArray.fromObject(jsonworker);
            // 遍历查询到的工作者手机号向移动端发送数据
            if (ja.size() > 0) {
                for (int i = 0; i < ja.size(); i++) {
                    // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                    JSONObject job = ja.getJSONObject(i);
                    // 得到 每个对象中的属性值
                    double lng2 = (double) job.get("longitude");
                    double lat2 = (double) job.get("latitude");
                    String phonenum1 = (String) job.get("phonenum");
                    // 第一次放弃的订单重新放入缓存中进行抢单
                    int radomid = (int) (Math.random() * 100000000);
                    String id = String.valueOf(radomid);
                    JSONObject jsonso = JSONObject.fromObject(so);
                    jsonso.put("wolng", lng2);
                    jsonso.put("wolat", lat2);
                    jsonso.put("phonenum", phonenum1);
                    String jsonorder = jsonso.toString();
                    // 返回群抢单缓存：订单进入缓存，3分钟
                    ru.setattime(id, jsonorder, 180);
                    wsu.sendOrder(phonenum1, "订单来啦");
                }
                // 返回订单缓存成功
                return "success";
            } else {
                // 返回订单缓存失败
                return "no";
            }
        } else {
            // 第二次放弃直接指派
            // 更新工作者订单状态
            wos.updateStatusGi(phonenum, keyid);
            // 查询订单商家地理位置
            JSONObject json = JSONObject.fromObject(sos.findShopOrderAllByKeyId(keyid));
            // 通过订单位置查询附近最近的一位工作者
            String oneWorker = was.getOneWorkWa(json.getDouble("longitude"), json.getDouble("latitude"));
            JSONObject ja = JSONObject.fromObject(oneWorker);
            String phonenum2 = (String) ja.get("phonenum");
            String name = ws.getworkername(phonenum2);
            json.put("status", 4);
            json.put("sort", 1);
            // 加入指派者手机号
            json.put("phonenum", phonenum2);
            // 加入被指派工作者姓名
            json.put("name", name);
            // 转换成WorkerOrder实体类
            WorkerOrder wo = (WorkerOrder) JSONObject.toBean(json, WorkerOrder.class);
            if (!wos.existBykeyid(keyid, phonenum2)) {
                // 转换成工作者的订单写入数据库
                wos.addOrder(wo);
            } else {
                wos.updateWoOrder(wo);
            }
            sos.updateStatusGi(keyid);
            wsu.sendOrder(phonenum2, "指派单来啦");
            // 指派成功
            return "zhipaisucc";
        }
    }

    /**
     * 工作者中转订单
     * @param
     * @return
     */
    @PostMapping("/zzorder/{keyid}")
    public String zzorder(@RequestBody WorkerAddress wa, @PathVariable("keyid") String keyid) {
        wgs.updatewwc(wa.getPhonenum());
        // 查询订单商家信息
        JSONObject json = JSONObject.fromObject(sos.findShopOrderAllByKeyId(keyid));
        // 通过订单位置查询附近最近的一位工作者,转为Json对象
        String oneWorker = was.getOneWorkWa(wa.getLongitude(), wa.getLatitude());
        JSONObject ja = JSONObject.fromObject(oneWorker);
        String phonenum2 = (String) ja.get("phonenum");
        String name = ws.getworkername(phonenum2);
        json.put("status", 4);
        json.put("sort", 1);
        // 加入指派者手机号
        json.put("phonenum", phonenum2);
        // 加入抢单者姓名
        json.put("name", name);
        // 转换成WorkerOrder实体类
        WorkerOrder wo = (WorkerOrder) JSONObject.toBean(json, WorkerOrder.class);
        if (!wos.existBykeyid(keyid, phonenum2)) {
            // 转换成工作者的订单写入数据库
            wos.addOrder(wo);
        } else {
            wos.updateWoOrder(wo);
        }
        sos.updateStatusGi(keyid);
        wsu.sendOrder(phonenum2, "指派单来啦");
        // 指派成功
        return "zhipaisucc";
    }

    /**
     * 获取历史订单
     * @param
     * @return
     */
    @PostMapping("/getlsdd/{phonenum}")
    public String getlsdd(@PathVariable("phonenum") String phonenum) {
        ArrayList<WorkerOrder> list;
        // 数据库中该手机号的订单列表
        list = wos.findWorkerOrderAllLSDD(phonenum);
        JSONArray listArray = JSONArray.fromObject(list);
        return JSONArray.fromObject(listArray).toString();
    }

    /*PC端*/

    /**
     * 数据统计
     * @return
     */
    @GetMapping("/getshopnum")
    public String getshopnum() {
        return sos.getallshopnum();
    }

    /**
     * 获取所有已完成订单
     * @return
     */
    @PostMapping("/web_getallywcshoporder")
    public String web_getallywcshoporder() {
        String jsonallywcshoporder = sos.web_getallywcshoporder();
        JSONArray ja = JSONArray.fromObject(jsonallywcshoporder);
        if (ja.size() > 0) {
            return jsonallywcshoporder;
        } else {
            return "noywcshoporder";
        }
    }

    /**
     * 获取所有未完成订单
     * @return
     * @throws Exception
     */
    @PostMapping("/web_getallwwcshoporder")
    public String web_getallwwcshoporder() {
        String jsonallwwcshoporder = sos.web_getallwwcshoporder();
        JSONArray ja = JSONArray.fromObject(jsonallwwcshoporder);
        if (ja.size() > 0) {
            return jsonallwwcshoporder;
        } else {
            return "nowwcshoporder";
        }
    }

    /**
     * 获取一个订单
     * @param keyid
     * @return
     */
    @PostMapping("/web_getoneshoporder/{keyid}")
    public String web_getoneshoporder(@PathVariable("keyid") String keyid) {
        String jsononeshoporder = sos.web_getoneshoporder(keyid);
        JSONArray ja = JSONArray.fromObject(jsononeshoporder);
        if (ja.size() > 0) {
            return jsononeshoporder;
        } else {
            return "nothisshoporder";
        }
    }

    /**
     * 删除一个订单
     * @return
     */
    @PostMapping("/web_deleteoneorder/{keyid}")
    public String web_deleteoneorder(@PathVariable("keyid") String keyid) {
        if (sos.web_deleteorder(keyid) == 1) {
            return "success";
        } else {
            return "failed";
        }
    }

}