package com.gxuwz.fx.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.gxuwz.fx.pojo.Worker;

/**
 * 配送人员Mapper接口
 * @author fengx
 */
@Mapper
public interface WorkerMapper {

    /*app端*/
    /**
     * 判断手机号是否存在
     * @param phonenum
     * @return
     */
    boolean existByPhonenum(String phonenum);

    /**
     * 添加工作者
     * @param worker
     * @return
     */
    int add(Worker worker);

    /**
     * 登录验证
     * @param phonenum
     * @param password
     * @return
     */
    boolean existByWorker(String phonenum, String password);

    /**
     * 判断工作者是否已认证
     * @param Phonenum
     * @return
     */
    boolean certification(String Phonenum);

    /**
     * 判断是否存在此身份证号
     * @param phonenum
     * @return
     */
    boolean existIdCardNum(String phonenum, String idcardnum);

    /**
     * 修改密码
     * @param phonenum
     * @return
     */
    int updatepsd(String phonenum, String password);

    /**
     * 工作者是否在审核中
     * @param Phonenum
     * @return
     */
    boolean review(String Phonenum);

    /**
     * 工作者上传信息认证
     * @param woker
     * @return
     */
    int certificationupdate(Worker woker);

    /**
     * 获取工作者姓名
     * @param phonenum
     * @return
     */
    String getworkername(String phonenum);

    /**
     * 修改资料
     * @param
     * @return
     */
    int updatezl(Worker worker);

    /**
     * 更新工作者认证状态
     * @param phonenum
     * @return
     */
    int updatestatus(String phonenum);

    /**
     * 获取工作者id
     * @param phonenum
     * @return
     */
    int getworkerid(String phonenum);

    /*PC端*/
    /**
     * 获取所有已认证工作者
     * @return
     */
    List<Worker> web_getallcertworker();

    /**
     * 获取所有未认证工作者
     * @return
     */
    List<Worker> web_getallnocertworker();

    /**
     * 获取所有已停用工作者
     * @return
     */
    List<Worker> web_getallstopworker();

    /**
     * 获取所有正在审核工作者
     * @return
     */
    List<Worker> web_getallshworker();

    /**
     * 停用工作者
     * @return
     */
    int web_stopworker(String phonenum);

    /**
     * 获取一名工作者信息
     * @return
     */
    List<Worker> web_getoneworker(String phonenum);

    /**
     * 获取一名工作者
     * @return
     */
    Worker web_getoneworkerjk(String phonenum);

    /**
     * 修改一名工作者信息
     * @return
     */
    int web_editoneworker(Worker wo);

    /**
     * 审核通过
     * @return
     */
    int web_shtgworker(String phonenum);

}
