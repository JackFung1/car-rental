package com.es.challenge.exception;


/**
 * @author fjt
 * @date 2023-02-16
 */
public class ParamInvalidException extends AppRuntimeException {

    public ParamInvalidException(Throwable cause, Reason reason) {
        super(reason.getCode(), cause);
    }

    public ParamInvalidException(Reason reason) {
        super(reason.getCode());
    }

    public ParamInvalidException() {
        super(Reason.PARAM_ERROR.getCode());
    }

    public ParamInvalidException(String errorMessage) {
        super(Reason.PARAM_ERROR.getCode(), errorMessage);
    }

    public enum Reason {

        PARAM_ERROR(ErrorCode.PARAM_ERROR),
        INVALID_SYMBOL(ErrorCode.INVALID_SYMBOL),
        SIGN_ERROR(ErrorCode.SIGN_ERROR),
        NO_PERMISSION(ErrorCode.NO_PERMISSION),
        ;

        private final ErrorCode code;

        public ErrorCode getCode() {
            return code;
        }

        Reason(ErrorCode code) {
            this.code = code;
        }
    }
}
