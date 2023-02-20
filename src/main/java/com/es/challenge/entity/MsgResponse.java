package com.es.challenge.entity;

import com.es.challenge.exception.ErrorCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Lxa on 2018/6/6.
 *
 * @author lixianan
 */
@Data
public class MsgResponse<T> {

    @ApiModelProperty(value = "响应成功", required = true)
    private boolean ret;
    @ApiModelProperty(value = "响应码", required = true)
    private String code;
    @ApiModelProperty(value = "响应信息", required = true)
    private String msg;
    private T data;

    private MsgResponse(boolean ret, String code, String msg, T data) {
        this.ret = ret;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public MsgResponse(T data) {
        this(true, ErrorCode.SUCCESS.getErrorCode(), StringUtils.EMPTY, data);
    }

    public MsgResponse(String errorCode, String errorMsg) {
        this(false, errorCode, errorMsg, null);
    }

}
