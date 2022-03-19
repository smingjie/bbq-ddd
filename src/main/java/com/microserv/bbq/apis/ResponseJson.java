package com.microserv.bbq.apis;

import com.microserv.bbq.infrastructure.general.constant.ErrorCodeEnum;

import java.io.Serializable;

/**
 * Json返回体格式封装
 * [成功]有数据：code/msg/data ; 无数据：code/msg/data=null
 * [失败]无数据：code/msg/data=null
 *
 * @author jockeys
 * @since 2020/4/6
 */
public final class ResponseJson<T> implements Serializable {
    /** 状态码 */
    private String code;
    /** 提示信息 */
    private String msg;
    /** 返回数据（if has） */
    private T data;

    /** 默认错误状态码 */
    private static final String DEFAULT_ERROR_CODE = "-1";
    /** 默认成功状态码 */
    private static final String DEFAULT_SUCCESS_CODE = "0";
    /** 默认成功提示信息 */
    private static final String DEFAULT_SUCCESS_MSG = "success";

    private ResponseJson() {
    }

    private ResponseJson(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private ResponseJson(String code, String msg) {
        this(code, msg, null);
    }

    /**
     * 重新设置提示信息
     *
     * @param msg 提示信息
     * @return this object
     */
    public ResponseJson<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    /**
     * 错误-消息返回体设置
     *
     * @param code 错误状态码
     * @param msg  错误提示信息
     * @return this object
     */
    public static <T> ResponseJson<T> error(String code, String msg) {
        return new ResponseJson<T>(code, msg);
    }

    /**
     * 错误-消息返回体设置（默认错误状态码为 -1）
     *
     * @param msg 错误提示信息
     * @return this object
     */
    public static <T> ResponseJson<T> error(String msg) {
        return error(DEFAULT_ERROR_CODE, msg);
    }

    /**
     * 错误-信息返回体设置，默认错误码和错误提示
     *
     * @return this object
     */
    public static <T> ResponseJson<T> error() {
        return error(ErrorCodeEnum.INTERNAL_SERVER_ERROR.getCode(), ErrorCodeEnum.INTERNAL_SERVER_ERROR.getMessage());
    }

    /**
     * 错误-信息返回体设置，返回信息状态定义
     *
     * @return this object
     */
    public static <T> ResponseJson<T> error(ErrorCodeEnum responseEnum) {
        return error(responseEnum.getCode(), responseEnum.getMessage());
    }

    /**
     * 成功-信息返回体设置
     *
     * @param data 响应数据
     * @return the object with response data
     */

    public static <T> ResponseJson<T> success(T data) {
        return new ResponseJson<T>(DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS_MSG, data);
    }

    /**
     * 成功-信息返回体设置 （无响应数据）
     *
     * @return this object without response data
     */
    public static <T> ResponseJson<T> success() {
        return success(null);
    }
}
