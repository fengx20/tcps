package com.gxuwz.fx.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.gxuwz.fx.pojo.Worker;
import com.gxuwz.fx.pojo.WorkerAddress;
import com.gxuwz.fx.service.WorkerAddressService;
import com.gxuwz.fx.service.WorkerGradeService;
import com.gxuwz.fx.service.WorkerOrderService;
import com.gxuwz.fx.service.WorkerService;
import com.gxuwz.fx.service.YzmService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 配送人员业务控制层
 * @author fengx
 */
@RestController
public class WorkerController {

    @Autowired
    private WorkerService ws;
    @Autowired
    private YzmService ys;
    @Autowired
    private WorkerAddressService was;
    @Autowired
    private WorkerOrderService wos;
    @Autowired
    private WorkerGradeService wgs;

    /**
     * 工作者注册（发送手机验证码）
     * @param worker
     * @param yzm
     * @return
     */
    @PostMapping("/regist/{yzm}")
    public String regist(@RequestBody Worker worker, @PathVariable("yzm") String yzm) {
        // 手机号存在
        if (ws.existph(worker.getPhonenum()) == false) {
            // 验证手机验证码
            if (ys.verification(worker.getPhonenum(), yzm) == true) {
                // 注册
                ws.regist(worker);
                return "success";
            } else {
                return "yzmerror";
            }
        } else {
            return "exist";
        }
    }

    /**
     * 工作者登录
     * @param worker
     * @return
     * @throws Exception
     */
    @PostMapping("/login")
    public String login(@RequestBody Worker worker) {
        // 手机号存在
        if (ws.existph(worker.getPhonenum()) == true) {
            if (ws.login(worker.getPhonenum(), worker.getPassword()) == true) {   //手机号、密码验证
                return "success";
            } else {
                return "failed";
            }
        } else {
            return "noexist";
        }
    }

    /**
     * 忘记密码-1(验证码)
     * @param worker
     * @return
     * @throws Exception
     */
    @PostMapping("/forgetone/{yzm}")
    public String forgetone(@RequestBody Worker worker, @PathVariable("yzm") String yzm) {
        // 手机号存在
        if (ws.existph(worker.getPhonenum()) == true) {
            // 验证手机验证码
            if (ys.verification(worker.getPhonenum(), yzm) == true) {
                return "yzmtrue";
            } else {
                return "yzmerror";
            }
        } else {
            return "phnoregist";
        }
    }

    /**
     * 忘记密码-2(身份证号)
     * @param worker
     * @return
     * @throws Exception
     */
    @PostMapping("/forgettwo")
    public String forgettwo(@RequestBody Worker worker) throws Exception {
        // 是否认证
        if (ws.certification(worker.getPhonenum()) == true) {
            // 身份证号是否存在
            if (ws.existIdCardNum(worker.getPhonenum(), worker.getIdcardnum()) == true) {
                return "idtrue";
            } else {
                return "iderror";
            }
        } else {
            return "nocert";
        }
    }

    /**
     * 跳过此步-忘记密码-2(身份证号)
     * @param worker
     * @return
     * @throws Exception
     */
    @PostMapping("/jumpid")
    public String jumpid(@RequestBody Worker worker) throws Exception {
        // 手机号是否已认证
        if (ws.certification(worker.getPhonenum()) == true) {
            return "idexist";
        } else {
            return "idnoexist";
        }
    }

    /**
     * 忘记密码-3(修改密码)
     * @param worker
     * @return
     * @throws Exception
     */
    @PostMapping("/forgetlast")
    public String forgetlast(@RequestBody Worker worker) throws Exception {
        // 修改密码
        if (ws.updatepsd(worker.getPhonenum(), worker.getPassword()) == 1) {
            return "success";
        } else {
            return "failed";
        }
    }

    /**
     * 工作者是否已认证
     * @param
     * @return
     * @throws Exception
     */
    @PostMapping("/iscertification/{phonenum}")
    public String iscertification(@PathVariable("phonenum") String phonenum) throws Exception {
        // 手机号是否已认证
        if (ws.certification(phonenum) == true) {
            return "yes";
        } else {
            // 是否在审核中
            if (ws.woreview(phonenum) == true) {
                return "review";
            } else {
                return "no";
            }
        }
    }

    /**
     * 工作者认证
     * @param worker
     * @return
     * @throws Exception
     */
    @PostMapping("/certification")
    public String certification(@RequestBody Worker worker) throws Exception {
        // 上传认证信息
        if (ws.certificationupload(worker) == 1) {
            return "success";
        } else {
            return "failed";
        }
    }

    /**
     * 工作者修改资料
     * @param worker
     * @return
     * @throws Exception
     */
    @PostMapping("/xgzl")
    public String xgzl(@RequestBody Worker worker) throws Exception {
        if (ws.updatezl(worker) == 1) {
            return "success";
        } else {
            return "failed";
        }
    }

    /**
     * 工作者进入工作状态
     *
     * @param
     * @return
     * @throws Exception
     */
    @PostMapping("/work")
    public String work(@RequestBody WorkerAddress wa) throws Exception {
        // 此工作者是否已认证
        if (ws.certification(wa.getPhonenum()) == true) {
            // 地理位置是否存在
            if (was.existwa(wa.getPhonenum()) == true) {
                // 更改工作者状态为工作状态
                if (was.work(wa.getPhonenum()) == 1) {
                    // 开工成功
                    return "success";
                } else {
                    // 开工失败
                    return "failed";
                }
            } else {
                // 第一次上传地理位置
                if (was.addfirst(wa) == 1) {
                    // 更改工作者状态为工作状态
                    if (was.work(wa.getPhonenum()) == 1) {
                        // 开工成功
                        return "success";
                    } else {
                        // 开工失败
                        return "failed";
                    }
                } else {
                    return "firstfailed";
                }
            }
        } else {
            // 是否在审核中
            if (ws.woreview(wa.getPhonenum()) == true) {
                return "review";
            } else {
                // 未认证
                return "nocertification";
            }
        }
    }

    /**
     * 工作者进入工作状态上传地理位置
     * @param
     * @return
     * @throws Exception
     */
    @PostMapping("/uploadadd")
    public String uploadadd(@RequestBody WorkerAddress wa) throws Exception {
        // 更新工作者地理位置
        if (was.updateadd(wa) == 1) {
            // 上传地理位置成功
            return "uploadaddresssuccess";
        } else {
            // 上传地理位置错误
            return "uploadaddressfailed";
        }
    }

    /**
     * 工作者进入休息状态
     * @param phonenum
     * @return
     * @throws Exception
     */
    @PostMapping("/stop/{phonenum}")
    public String stop(@PathVariable("phonenum") String phonenum) throws Exception {
        // 此工作者是否已认证
        if (ws.certification(phonenum) == true) {
            // 休息
            if (was.stop(phonenum) == 1) {
                return "success";
            } else {
                return "failed";
            }
        } else {
            // 是否在审核中
            if (ws.woreview(phonenum) == true) {
                return "review";
            } else {
                // 未认证
                return "nocertification";
            }
        }
    }

    /**
     * 是否为工作状态
     * @param phonenum
     * @return
     * @throws Exception
     */
    @PostMapping("/iswork/{phonenum}")
    public String iswork(@PathVariable("phonenum") String phonenum) {
        if (was.iswork(phonenum) == true) {
            return "true";
        } else {
            return "false";
        }
    }

    /**
     * 获取工作者姓名
     * @param phonenum
     * @return
     * @throws Exception
     */
    @PostMapping("/getname/{phonenum}")
    public String getname(@PathVariable("phonenum") String phonenum) throws Exception {
        String name = ws.getworkername(phonenum);
        return name;
    }

    /**
     * 工作统计-日单量(今日)
     * @param phonenum
     * @return
     * @throws Exception
     */
    @PostMapping("/gztj/{phonenum}")
    public String gztj(@PathVariable("phonenum") String phonenum) throws Exception {
        if (ws.certification(phonenum) == true) {
            int qqd = wos.gettoday(phonenum);
            int qqd_fi = wos.gettoday_qq_fi(phonenum);
            int zpd = wos.gettoday_zp(phonenum);
            int zpd_fi = wos.gettoday_zp_fi(phonenum);
            JSONObject jsonobj = new JSONObject();
            jsonobj.put("qqd", qqd);
            jsonobj.put("qqd_fi", qqd_fi);
            jsonobj.put("zpd", zpd);
            jsonobj.put("zpd_fi", zpd_fi);
            String qunstr = jsonobj.toString();
            return qunstr;
        } else {
            return "isnocert";
        }
    }

    /**
     * 工作统计-日单量(一周)
     * @param phonenum
     * @return
     * @throws Exception
     */
    @PostMapping("/gztj_week/{phonenum}")
    public String gztj_week(@PathVariable("phonenum") String phonenum) throws Exception {
        if (ws.certification(phonenum) == true) {
            int qqd = wos.getweek(phonenum);
            int qqd_fi = wos.getweek_fi(phonenum);
            int zpd = wos.getweek_zp(phonenum);
            int zpd_fi = wos.gettoday_zp_fi(phonenum);
            JSONObject jsonobj = new JSONObject();
            jsonobj.put("qqd", qqd);
            jsonobj.put("qqd_fi", qqd_fi);
            jsonobj.put("zpd", zpd);
            jsonobj.put("zpd_fi", zpd_fi);
            String qunstr = jsonobj.toString();
            return qunstr;
        } else {
            return "isnocert";
        }
    }

    /**
     * 工作统计-日单量(当月)
     * @param phonenum
     * @return
     * @throws Exception
     */
    @PostMapping("/gztj_month/{phonenum}")
    public String gztj_month(@PathVariable("phonenum") String phonenum) throws Exception {
        if (ws.certification(phonenum) == true) {
            int qqd = wos.getmonth(phonenum);
            int qqd_fi = wos.getmonth_fi(phonenum);
            int zpd = wos.getmonth_zp(phonenum);
            int zpd_fi = wos.getmonth_zp_fi(phonenum);
            JSONObject jsonobj = new JSONObject();
            jsonobj.put("qqd", qqd);
            jsonobj.put("qqd_fi", qqd_fi);
            jsonobj.put("zpd", zpd);
            jsonobj.put("zpd_fi", zpd_fi);
            String qunstr = jsonobj.toString();
            return qunstr;
        } else {
            return "isnocert";
        }
    }

    /**
     * 工作统计-收入(当日)
     * @param phonenum
     * @return
     * @throws Exception
     */
    @PostMapping("/gzsr_today/{phonenum}")
    public String gzsr(@PathVariable("phonenum") String phonenum) {
        if (ws.certification(phonenum) == true) {
            int shouru = wos.shouru_today(phonenum);
            String shourustr = String.valueOf(shouru);
            JSONObject jsonobj = new JSONObject();
            jsonobj.put("shouru", shourustr);
            String sr = jsonobj.toString();
            return sr;
        } else {
            return "isnocert";
        }
    }

    /**
     * 工作统计-收入(本周)
     * @param phonenum
     * @return
     * @throws Exception
     */
    @PostMapping("/gzsr_week/{phonenum}")
    public String gzsr_week(@PathVariable("phonenum") String phonenum) throws Exception {
        if (ws.certification(phonenum) == true) {
            int shouru = wos.shouru_week(phonenum);
            String shourustr = String.valueOf(shouru);
            JSONObject jsonobj = new JSONObject();
            jsonobj.put("shouru", shourustr);
            String sr = jsonobj.toString();
            return sr;
        } else {
            return "isnocert";
        }
    }

    /**
     * 工作统计-收入(当月)
     * @param phonenum
     * @return
     * @throws Exception
     */
    @PostMapping("/gzsr_month/{phonenum}")
    public String gzsr_month(@PathVariable("phonenum") String phonenum) throws Exception {
        if (ws.certification(phonenum) == true) {
            int shouru = wos.shouru_month(phonenum);
            String shourustr = String.valueOf(shouru);
            JSONObject jsonobj = new JSONObject();
            jsonobj.put("shouru", shourustr);
            String sr = jsonobj.toString();
            return sr;
        } else {
            return "isnocert";
        }
    }

    /**
     * 获取等级数据
     * @param phonenum
     * @return
     * @throws Exception
     */
    @PostMapping("/getwo_grade/{phonenum}")
    public String getwo_grade(@PathVariable("phonenum") String phonenum) throws Exception {
        return wgs.getWorkerGrade(phonenum);
    }

    /*PC端*/
    /**
     * 获取所有已认证工作者
     * @return
     * @throws Exception
     */
    @PostMapping("/web_getallcertworker")
    public String web_getallcertworker() throws Exception {
        String jsonallworker = ws.web_getallcertworker();
        JSONArray ja = JSONArray.fromObject(jsonallworker);
        if (ja.size() > 0) {
            return jsonallworker;
        } else {
            return "noworker";
        }
    }

    /**
     * 获取所有未认证工作者
     * @return
     * @throws Exception
     */
    @PostMapping("/web_getallnocertworker")
    public String web_getallnocertworker() throws Exception {
        String jsonallworker = ws.web_getallnocertworker();
        JSONArray ja = JSONArray.fromObject(jsonallworker);
        if (ja.size() > 0) {
            return jsonallworker;
        } else {
            return "noworker";
        }
    }

    /**
     * 获取所有审核中工作者
     * @return
     * @throws Exception
     */
    @PostMapping("/web_getallstopworker")
    public String web_getallworker() throws Exception {
        String jsonallworker = ws.web_getallstopworker();
        JSONArray ja = JSONArray.fromObject(jsonallworker);
        if (ja.size() > 0) {
            return jsonallworker;
        } else {
            return "noworker";
        }
    }

    /**
     * 获取所有已认证工作者
     * @return
     * @throws Exception
     */
    @PostMapping("/web_getallshworker")
    public String web_getallshworker() throws Exception {
        String jsonallworker = ws.web_getallshworker();
        JSONArray ja = JSONArray.fromObject(jsonallworker);
        if (ja.size() > 0) {
            return jsonallworker;
        } else {
            return "noworker";
        }
    }

    /**
     * 停用工作者
     * @return
     * @throws Exception
     */
    @PostMapping("/web_stopworker/{phonenum}")
    public String web_stopworker(@PathVariable("phonenum") String phonenum) throws Exception {
        if (ws.web_stopworker(phonenum) == 1) {
            return "success";
        } else {
            return "failed";
        }
    }

    /**
     * 查询一位工作者信息
     * @return
     * @throws Exception
     */
    @PostMapping("/web_getoneworker/{phonenum}")
    public String web_getoneworker(@PathVariable("phonenum") String phonenum) throws Exception {
        String jsononeworker = ws.web_getoneworker(phonenum);
        return jsononeworker;
    }

    /**
     * 修改一位工作者信息
     * @return
     * @throws Exception
     */
    @PostMapping("/web_editoneworker")
    public String web_editoneworker(@RequestBody Worker wo) throws Exception {
        if (ws.web_editoneworker(wo) == 1) {
            return "success";
        } else {
            return "failed";
        }
    }

    /**
     * 审核通过
     * @return
     * @throws Exception
     */
    @PostMapping("/web_shtgworker/{phonenum}")
    public String web_shtgworker(@PathVariable("phonenum") String phonenum) throws Exception {
        if (ws.web_shtgworker(phonenum) == 1) {
            wgs.addwg(phonenum);
            return "success";
        } else {
            return "failed";
        }
    }

    /**
     * 查询一位工作者
     * @return
     * @throws Exception
     */
    @PostMapping("/web_cxoneworker/{phonenum}")
    public String web_cxoneworker(@PathVariable("phonenum") String phonenum) throws Exception {
        if (ws.existph(phonenum) == true) {
            String jsononeworker = ws.web_getoneworker(phonenum);
            return jsononeworker;
        } else {
            return "nothisworker";
        }
    }

    /**
     * 获取所有在线工作者坐标
     * @return
     * @throws Exception
     */
    @GetMapping("/get_allwa")
    public String get_allwa() throws Exception {
        @SuppressWarnings("rawtypes")
        List<Map> list1 = new ArrayList<Map>();
        List<WorkerAddress> list = was.get_allwa();
        for (int i = 0; i < list.size(); i++) {
            Worker wo = ws.web_getoneworkerjk(list.get(i).getPhonenum());
            @SuppressWarnings("unchecked")
            Map<String, Object> a = (Map<String, Object>) JSON.toJSON(wo);  //对象转为map
            a.put("longitude", list.get(i).getLongitude());
            a.put("latitude", list.get(i).getLatitude());
            list1.add(a);
        }
        Gson gson = new Gson();
        String json = gson.toJson(list1);
        return json;
    }

}