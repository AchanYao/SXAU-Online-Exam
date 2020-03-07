package com.achan.exam.common.vo;

import lombok.Getter;

/**
 * <p>响应结果集</p>
 * <p>25开头错误表示服务器错误</p>
 * <p>21开头表示请求错误，如参数缺失，数据插入错误</p>
 *
 * @author Achan
 * @since 2020/1/14
 */
@Getter
public enum ResultCodeEnum {
    /**
     * 成功
     */
    SUCCESS(true, 20000, "成功"),
    /**
     * 未知错误
     */
    UNKNOWN_REASON(false, 20001, "未知错误"),
    /**
     * sql语法错误
     */
    BAD_SQL_GRAMMAR(false, 25001, "sql语法错误"),
    /**
     * json解析错误
     */
    JSON_PARSE_ERROR(false, 21002, "json解析异常"),
    /**
     * 参数不正确
     */
    PARAM_ERROR(false, 21003, "参数不正确"),
    /**
     * 文件上传错误
     */
    FILE_UPLOAD_ERROR(false, 21004, "文件上传错误"),
    /**
     * 数据导入错误
     */
    DATA_IMPORT_ERROR(false, 21005, "数据导入错误"),
    /**
     * 新增用户失败
     */
    INSERT_USER_ERROR(false, 21006, "新增用户失败"),
    /**
     * 关联关系错误
     */
    CONNECTION_RELATION_ERROR(false, 21007, "关联关系错误"),
    /**
     * 数据保存错误
     */
    DATA_SAVE_ERROR(false, 21008, "数据保存错误"),
    /**
     * 访问的数据不存在
     */
    DATA_NOT_EXISTS(false, 21009, "访问的资源不存在")
    ;

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态信息
     */
    private String message;

    ResultCodeEnum(Boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}
