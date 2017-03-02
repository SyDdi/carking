package com.car.mp.domain.vo;

import com.car.mp.domain.PageEntity;
import com.car.mp.util.Constant;

import java.io.Serializable;

/**
 * 结果输出VO类
 *
 * @author wangjh7
 * @date 2016-05-15
 */
public class ResponseVO implements Serializable {

    /**
     * 状态 0 成功 >0 失败
     */
    private int status = 0;

    /**
     * 文字提示
     */
    private String msg = "";
    /**
     * 分页
     */
    private PageEntity page = null;

    /**
     * 通用对象，需要拆箱装箱
     */
    private Object object = null;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public PageEntity getPage() {
        return page;
    }

    public void setPage(PageEntity page) {
        this.page = page;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * 是否成功
     *
     * @return
     */
    public boolean isSuccess() {
        return Constant.ErrorCode.SUCCESS.getCode() == status;
    }

    /**
     * 成功
     *
     * @return
     */
    public static ResponseVO success() {
        return success(null);
    }

    /**
     * 成功-带返回值
     *
     * @return
     */
    public static ResponseVO success(Object object) {
        return success(object, null);
    }

    /**
     * 成功-带返回值 + 分页
     *
     * @return
     */
    public static ResponseVO success(Object object, PageEntity page) {
        ResponseVO vo = new ResponseVO();
        vo.setStatus(Constant.ErrorCode.SUCCESS.getCode());
        vo.setMsg(Constant.ErrorCode.SUCCESS.getMessage());
        if (null != object) {
            vo.setObject(object);
        }
        if (null != page) {
            vo.setPage(page);
        }
        return vo;
    }

    /**
     * 失败
     *
     * @return
     */
    public static ResponseVO error() {
        return error(Constant.ErrorCode.ERROR);
    }

    /**
     * 失败-枚举值
     *
     * @return
     */
    public static ResponseVO error(Constant.ErrorCode errorCode) {
        ResponseVO vo = new ResponseVO();
        vo.setStatus(errorCode.getCode());
        vo.setMsg(errorCode.getMessage());
        return vo;
    }

    /**
     * 失败-自定义值
     *
     * @return
     */
    public static ResponseVO error(int code, String msg) {
        ResponseVO vo = new ResponseVO();
        vo.setStatus(code);
        vo.setMsg(msg);
        return vo;
    }


    @Override
    public String toString() {
        return "ResponseVO{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", page=" + page +
                ", object=" + object +
                '}';
    }
}
