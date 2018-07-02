package com.gws.newframe.dto;

import com.gws.newframe.enums.CodeStatus;
import com.gws.newframe.enums.SystemCode;
import lombok.NoArgsConstructor;

/**
 * 【操作结果的枚举】
 */
@NoArgsConstructor
public class OperationResult<T> {

    private Boolean succ = true;

    private CodeStatus errorCode;

    private T entity;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    private String code;

    private String message;
    public OperationResult(T entity) {
        this.entity = entity;
    }

    public OperationResult(CodeStatus errorCode) {
        this.succ = false;
        this.errorCode = errorCode;
        this.code=errorCode.getCode();
        this.message=errorCode.getMessage();
    }

    public OperationResult(String errorCode, String errorMessage) {
        this.succ = false;
        this.code = errorCode;
        this.message = errorMessage;
    }

    public OperationResult(CodeStatus errorCode, T entity) {
        if(SystemCode.SUCCESS.getCode().equals(errorCode.getCode())){
            this.succ = true;
        }else{
            this.succ = false;
        }
        this.errorCode = errorCode;
        this.code=errorCode.getCode();
        this.message=errorCode.getMessage();
        this.entity = entity;
    }
    public Boolean getSucc() {
        return succ;
    }


    public CodeStatus getErrorCode() {
        return errorCode;
    }

    public T getEntity() {
        return entity;
    }
}
