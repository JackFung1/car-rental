package com.es.challenge.exception;

import com.es.challenge.entity.MsgResponse;
import com.fasterxml.jackson.core.JsonParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author fjt
 * @date 2023-02-16
 */
@ControllerAdvice
public class ExceptionAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler(AppRuntimeException.class)
    @ResponseBody
    public MsgResponse<?> handleExchangeRuntimeException(AppRuntimeException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        logger.error("runtime exception caught", exception);
        return new MsgResponse<>(errorCode.getErrorCode(), exception.getMessage());

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public MsgResponse<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.error("HttpMessageNotReadable exception caught", e);
        return new MsgResponse<>(ErrorCode.PARAM_TYPE_ERROR.getErrorCode(), ErrorCode.PARAM_TYPE_ERROR.getErrorMsg());

    }

    @ExceptionHandler(JsonParseException.class)
    @ResponseBody
    public MsgResponse<?> handleJsonParseException(JsonParseException e) {
        logger.error("JsonParseException exception caught", e);
        return new MsgResponse<>(ErrorCode.PARAM_TYPE_ERROR.getErrorCode(), ErrorCode.PARAM_TYPE_ERROR.getErrorMsg());

    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public MsgResponse<?> handleException(@SuppressWarnings("unused") Exception e) {
        logger.error("Unexpected exception caught", e);
        return new MsgResponse<>(ErrorCode.FAIL.getErrorCode(), ErrorCode.FAIL.getErrorMsg());
    }

}
