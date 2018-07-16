package com.newframe.controllers;

import com.newframe.enums.CodeStatus;
import lombok.Data;

/**
 * 【接口请求统一JSON格式响应】
 */
@Data
public class JsonResult {

    private String code;

    private String message;

    private Object data;

    public JsonResult(CodeStatus codeStatus) {

        this.code = codeStatus.getCode();
        this.message = codeStatus.getMessage();
    }


    //正确的用这个
    public JsonResult(CodeStatus codeStatus, Object data) {
        this.code = codeStatus.getCode();
        this.message = codeStatus.getMessage();
        this.data = data;
    }

    //错误的用这个，不需要返回data
    public JsonResult(String code, String message) {
        this.code = code ;
        this.message =message ;
    }

}
