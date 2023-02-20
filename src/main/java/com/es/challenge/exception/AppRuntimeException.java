package com.es.challenge.exception;


/**
 * @author fjt
 * @date 2023-02-16
 */
public class AppRuntimeException extends RuntimeException {

    private ErrorCode errorCode;

    public AppRuntimeException(ErrorCode errorCode) {
        super(errorCode.getErrorMsg());
        this.errorCode = errorCode;
    }

    public AppRuntimeException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getErrorMsg(), cause);
        this.errorCode = errorCode;
    }

    public AppRuntimeException(ErrorCode errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}
