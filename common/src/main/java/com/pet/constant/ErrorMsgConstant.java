package com.pet.constant;

public class ErrorMsgConstant {
    private ErrorMsgConstant(){
    }

    /**
     * 提交成功
     */
    public static final String SUCCESS = "提交成功";
    /**
     * 系统异常
     */
    public static final String SYSTEM_ERROR = "系统异常";
    /**
     * 用户名称已被占用
     */
    public static final String USER_ALREADY_EXIST = "用户名称已被占用";
    /**
     * 用户不能为空
     */
    public static final String USER_NAME_NOT_NULL  = "用户不能为空";
    /**
     * 密码不能为空
     */
    public static final String USER_PWD_NOT_NULL  = "密码不能为空";
    /**
     * 校验密码不能为空
     */
    public static final String USER_VALID_PWD_NOT_NULL  = "校验密码不能为空";
    /**
     * 密码不符合规范
     */
    public static final String USER_PWD_NOT_MATCH  = "密码不符合规范";
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
}
