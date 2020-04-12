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
    private static final Integer DEFAULT_ERROR_CODE = -1;
    private static final Integer DEFAULT_SUCCESS_CODE = 0;
    private static final String DEFAULT_SUCCESS_MSG = "success";

    /**
     * 默认构造函数私有化
     */
    private ResponseJson() {
        init(DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS_MSG);
    }

    private ResponseJson init(Integer code, String msg) {
        return this.fluentPut("code", code).fluentPut("msg", msg);
    }

    private ResponseJson setData(Object data) {
        return this.fluentPut("data", data);
    }

    private ResponseJson fluentPut(String key, Object value) {
        put(key, value);
        return this;
    }


    /**
     * 错误-消息返回体设置
     *
     * @param code 错误状态码
     * @param msg  错误提示信息
     * @return this object
     */
    public static ResponseJson error(Integer code, String msg) {
        return new ResponseJson().init(code, msg);
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
        return error(ErrorCodeEnum.INTERNAL_SERVER_ERROR.getMessage());
    }

    /**
     * 错误-信息返回体设置，返回信息状态定义
     *
     * @return this object
     */
    public static ResponseJson error(ErrorCodeEnum errorCodeEnum) {
        return error(errorCodeEnum.getCode(), errorCodeEnum.getMessage());
    }

    /**
     * 成功-信息返回体设置
     *
     * @param data 响应数据
     * @return the object with response data
     */

    public static ResponseJson success(Object data) {
        return new ResponseJson().setData(data);
    }

    /**
     * 成功-信息返回体设置 （无响应数据）
     *
     * @return this object without response data
     */
    public static ResponseJson success() {
        return new ResponseJson();
    }
}
