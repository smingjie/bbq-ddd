package com.microserv.bbq.infrastructure.general.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;

/**
 * 错误码定义
 *
 * @author jockeys
 * @since 2020/4/6
 */
@Getter
@RequiredArgsConstructor
public enum ErrorCodeEnum {
    /**
     * 400
     */
    BAD_REQUEST(HttpServletResponse.SC_BAD_REQUEST, "请求参数有误"),
    /**
     * JSON格式错误
     */
    JSON_FORMAT_ERROR(HttpServletResponse.SC_BAD_REQUEST, "JSON格式错误"),
    /**
     * 401
     */
    UNAUTHORIZED(HttpServletResponse.SC_UNAUTHORIZED, "请先进行认证"),
    /**
     * 403
     */
    FORBIDDEN(HttpServletResponse.SC_FORBIDDEN, "暂无权操作，如需授权，请联系管理员"),
    /**
     * 404
     */
    NOT_FOUND(HttpServletResponse.SC_NOT_FOUND, "未找到"),
    /**
     * 405
     */
    METHOD_NOT_ALLOWED(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "请求方式不支持"),
    /**
     * 406
     */
    NOT_ACCEPTABLE(HttpServletResponse.SC_NOT_ACCEPTABLE, "不可接受该请求"),
    /**
     * 411
     */
    LENGTH_REQUIRED(HttpServletResponse.SC_LENGTH_REQUIRED, "请求长度受限制"),
    /**
     * 415
     */
    UNSUPPORTED_MEDIA_TYPE(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "该请求不支持的媒体类型"),
    /**
     * 416
     */
    REQUESTED_RANGE_NOT_SATISFIABLE(HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE, "该请求不在请求满足的范围"),
    /**
     * 500
     */
    INTERNAL_SERVER_ERROR(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "服务器出了点问题，抱歉"),
    /**
     * 503
     */
    SERVICE_UNAVAILABLE(HttpServletResponse.SC_SERVICE_UNAVAILABLE, "请求超时"),
    /**
     * 消息异常
     */
    MSG_EXCEPTION(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "默认消息异常"),
    //----------------------------------------------------业务异常----------------------------------------------------
    /**
     * Dept
     */
    DEPT_EXISTING_LOWER_LEVEL_DEPT(HttpServletResponse.SC_BAD_REQUEST, "当前部门存在下属部门，不允许删除"),
    DEPT_EXISTING_USER(HttpServletResponse.SC_BAD_REQUEST, "当前部门还存在用户，不允许删除"),
    DEPT_NAME_EXIST(HttpServletResponse.SC_BAD_REQUEST, "部门名称已经存在"),
    DEPT_PARENT_DEPT_CANNOT_MYSELF(HttpServletResponse.SC_BAD_REQUEST, "上级部门不能是当前部门"),
    /**
     * User
     */
    USER_OLD_PASSWORD_ERROR(HttpServletResponse.SC_BAD_REQUEST, "修改密码失败，旧密码错误"),
    USER_AVATAR_NOT_EMPTY(HttpServletResponse.SC_BAD_REQUEST, "用户头像不能为空"),
    USER_AVATAR_UPLOAD_FAIL(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "用户头像上传失败"),
    USER_CANNOT_UPDATE_SUPER_ADMIN(HttpServletResponse.SC_BAD_REQUEST, "不可以修改超级管理员"),
    USER_ACCOUNT_EXIST(HttpServletResponse.SC_BAD_REQUEST, "账号已存在"),
    USER_PHONE_EXIST(HttpServletResponse.SC_BAD_REQUEST, "手机号已存在"),
    USER_EMAIL_EXIST(HttpServletResponse.SC_BAD_REQUEST, "Email已存在"),
    USER_NOT_ONLINE(HttpServletResponse.SC_BAD_REQUEST, "用户已下线"),
    USER_CANNOT_RETREAT_CURRENT_ACCOUNT(HttpServletResponse.SC_BAD_REQUEST, "当前登陆用户无法强退"),
    USER_ELSEWHERE_LOGIN(HttpServletResponse.SC_UNAUTHORIZED, "您已在别处登录，请您修改密码或重新登录"),
    USER_USERNAME_OR_PASSWORD_IS_WRONG(HttpServletResponse.SC_BAD_REQUEST, "用户名密码错误"),

    /**
     * Menu
     */
    MENU_EXISTING_LOWER_LEVEL_MENU(HttpServletResponse.SC_BAD_REQUEST, "当前菜单存在子菜单，不允许删除"),
    MENU_EXISTING_USING(HttpServletResponse.SC_BAD_REQUEST, "菜单已被使用，不允许删除"),
    MENU_NAME_EXIST(HttpServletResponse.SC_BAD_REQUEST, "菜单名称已存在"),
    /**
     * File
     */
    FILE_UPLOAD_FAIL(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "文件上传失败"),
    FILE_DOWNLOAD_FAIL(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "文件下载失败"),
    FILE_ILLEGAL_FILENAME(HttpServletResponse.SC_BAD_REQUEST, "文件名称非法，不允许下载"),
    /**
     * Job
     */
    JOB_NOT_FOUND(HttpServletResponse.SC_BAD_REQUEST, "未找到该定时任务"),
    /**
     * Config
     */
    CONFIG_KEY_EXIST(HttpServletResponse.SC_BAD_REQUEST, "参数键名已存在"),
    /**
     * Dict
     */
    DICT_TYPE_EXIST(HttpServletResponse.SC_BAD_REQUEST, "字典类型已存在"),
    /**
     * Post
     */
    POST_NAME_EXIST(HttpServletResponse.SC_BAD_REQUEST, "岗位名称已存在"),
    POST_CODE_EXIST(HttpServletResponse.SC_BAD_REQUEST, "岗位编码已存在"),
    /**
     * Role
     */
    ROLE_NAME_EXIST(HttpServletResponse.SC_BAD_REQUEST, "角色名称已存在"),
    ROLE_KEY_EXIST(HttpServletResponse.SC_BAD_REQUEST, "角色权限已存在"),

    ;

    //----------------------------------------------------分割线----------------------------------------------------
    /**
     * http状态码
     */
    private final int code;
    /**
     * 错误信息提示
     */
    private final String message;

    /**
     * for example: JSON_FORMAT_ERROR
     *
     * @return json-format-error
     */
    public String getName() {
        return this.name().toLowerCase().replace('_', '-');
    }

    public static ErrorCodeEnum get(String errorCode) {
        for (ErrorCodeEnum errorCodeEnum : ErrorCodeEnum.values()) {
            if (errorCodeEnum.name().equalsIgnoreCase(errorCode)) {
                return errorCodeEnum;
            }
        }
        return null;
    }

    public String toString() {
        return MessageFormat.format("{0}(code={1},message={2})", name().toLowerCase(), getCode(), getMessage());

    }
}
