package com.pet.constant;

public class ErrorMsgConstant {
    private ErrorMsgConstant(){
    }

    /**
     * 提交成功
     */
    public static final String SUCCESS = "处理成功";
    /**
     * 系统异常
     */
    public static final String SYSTEM_ERROR = "系统异常";
    /**
     * WEBSOCKET服务异常
     */
    public static final String WEBSOCKET_ERROR = "WEBSOCKET服务异常";
    /**
     * MAINTAIN服务异常
     */
    public static final String MAINTAIN_ERROR = "MAINTAIN服务异常";
    /**
     * 手机号应为11位数字
     */
    public static final String PHONE_ERROR = "手机号应为11位数字";
    /**
     * 邮箱过长
     */
    public static final String EMAIL_TOO_LONG = "邮箱过长";
    /**
     * 用户不能为空
     */
    public static final String USER_NAME_NOT_NULL  = "用户名不能为空";
    /**
     * 用户名重复
     */
    public static final String USER_NAME_ALREADY_EXIST  = "用户名重复";
    /**
     * 用户名过长
     */
    public static final String USER_NAME_TOO_LONG  = "用户名过长";
    /**
     * 密码不能为空
     */
    public static final String USER_PWD_NOT_NULL  = "密码不能为空";
    /**
     * 密码过长
     */
    public static final String USER_PWD_TOO_LONG  = "密码过长";
    /**
     * pageIndex不能为空
     */
    public static final String PAGE_INDEX_NOT_NULL  = "pageIndex不能为空";
    /**
     * pageSize不能为空
     */
    public static final String PAGE_SIZE_NOT_NULL  = "pageSize不能为空";
    /**
     * 校验密码不能为空
     */
    public static final String USER_VALID_PWD_NOT_NULL  = "校验密码不能为空";
    /**
     * 密码不符合规范
     */
    public static final String USER_PWD_NOT_MATCH  = "密码不符合规范";
    /**
     * 邮箱不符合规范
     */
    public static final String USER_EMAIL_NOT_MATCH  = "邮箱不符合规范";
    /**
     * 第二次输入密码不符合规范
     */
    public static final String USER_VALID_PWD_NOT_MATCH  = "第二次输入密码不符合规范";
    /**
     * 两次输入密码不一致
     */
    public static final String USER_VALID_PWD_USER_PWD_NOT_EQUALS  = "两次输入密码不一致";
    /**
     * 角色不能为空
     */
    public static final String ROLE_NAME_NOT_NULL  = "角色名称不能为空";
    /**
     * 权限名称不能为空
     */
    public static final String RIGHTS_NAME_NOT_NULL  = "权限名称不能为空";
    /**
     * 角色名称已被占用
     */
    public static final String ROLE_ALREADY_EXIST = "角色名称已被占用";
    /**
     * 角色名称修改前后一致
     */
    public static final String ROLE_NAME_MODIFY_CONSISTENT = "角色名称修改前后一致";
    /**
     * 用户不存在
     */
    public static final String USER_NOT_EXIST_ERROR = "用户不存在";
    /**
     * 用户权限不足
     */
    public static final String USER_RIGHT_ERROR = "用户权限不足";
    /**
     * 用户名或密码错误
     */
    public static final String USER_NAME_OR_PWD_ERROR = "用户名或密码错误";
    /**
     * 权限的父类id不能为空
     */
    public static final String RIGHT_PARENT_ID_NOT_NULL  = "权限父ID不能为空";
}
