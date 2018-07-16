
package com.newframe.enums;

/**
 *【错误码枚举】
 *
 * @author
 */
public enum SystemCode implements CodeStatus {
    SUCC("000", "成功"),
    SUCCESS("200", "成功"),
    NEED_LOGIN("201", "未登录"),
    BAD_REQUEST("202", "参数非法"),
    NOT_IN_WHITELIST("203", "不在白名单内"),
    ILLEGAL_ACTION("204", "不合法的接口"),
    HANDLE_EXCEPTION("205", "系统处理异常，请稍后再试"),








    ;





    private String code;

    private String message;

    private SystemCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
