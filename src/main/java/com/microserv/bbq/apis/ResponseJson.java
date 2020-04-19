package com.microserv.bbq.apis;

import com.microserv.bbq.infrastructure.general.constant.ErrorCodeEnum;

import java.util.HashMap;

/**
 * Json返回体格式封装
 * [成功]有数据：code/msg/data ; 无数据：code/msg
 * [失败]：code/msg
 *
 * @author jockeys
 * @since 2020/4/6
 */
public final class ResponseJson extends HashMap<String, Object> {
    /**
     * 默认错误码
     */
    private static final int DEFAULT_ERROR_CODE = -1;

    /**
     * 默认构造函数私有化
     */
    private ResponseJson() {
        put("code", 0);
        put("msg", "success");
    }

    /**
     * 重新设置提示信息
     *
     * @param msg 提示信息
     * @return this object
     */
    public ResponseJson setMsg(String msg) {
        this.put("msg", msg);
        return this;
    }

    /**
     * 错误-消息返回体设置
     *
     * @param code 错误状态码
     * @param msg  错误提示信息
     * @return this object
     */
    public static ResponseJson error(int code, String msg) {
        ResponseJson ans = new ResponseJson();
        ans.put("code", code);
        ans.put("msg", msg);
        return ans;
    }

    /**
     * 错误-消息返回体设置（默认错误状态码为 -1）
     *
     * @param msg 错误提示信息
     * @return this object
     */
    public static ResponseJson error(String msg) {
        return error(DEFAULT_ERROR_CODE, msg);
    }

    /**
     * 错误-信息返回体设置，默认错误码和错误提示
     *
     * @return this object
     */
    public static ResponseJson error() {
        return error(ErrorCodeEnum.INTERNAL_SERVER_ERROR.getCode(), ErrorCodeEnum.INTERNAL_SERVER_ERROR.getMessage());
    }

    /**
     * 错误-信息返回体设置，返回信息状态定义
     *
     * @return this object
     */
    public static ResponseJson error(ErrorCodeEnum responseEnum) {
        return error(responseEnum.getCode(), responseEnum.getMessage());
    }

    /**
     * 成功-信息返回体设置
     *
     * @param data 响应数据
     * @return the object with response data
     */

    public static ResponseJson ok(Object data) {
        ResponseJson ans = new ResponseJson();
        ans.put("data", data);
        return ans;
    }

    /**
     * 成功-信息返回体设置 （无响应数据）
     *
     * @return this object without response data
     */
    public static ResponseJson ok() {
        return new ResponseJson();
    }
}
