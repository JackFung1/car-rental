package com.es.challenge.exception;

/**
 * @author fjt
 * @date 2023-02-16
 */
public class ErrorCode {

  public static final ErrorCode SUCCESS = new ErrorCode("SUCCESS", "成功");   //成功
  public static final ErrorCode FAIL = new ErrorCode("FAIL", "系统异常");   //成功
  public static final ErrorCode PARAM_ERROR = new ErrorCode("PARAM_ERROR", "参数错误");  //参数错误
  public static final ErrorCode PARAM_TYPE_ERROR = new ErrorCode("PARAM_TYPE_ERROR", "参数类型错误");  //参数错误
  public static final ErrorCode SIGN_ERROR = new ErrorCode("SIGN_ERROR", "签名错误");
  public static final ErrorCode INTERFACE_NO_AUTH = new ErrorCode("INTERFACE_NO_AUTH", "没有调用api权限");
  public static final ErrorCode DUPLICATE_DATA = new ErrorCode("DUPLICATE_DATA", "数据重复");
  public static final ErrorCode EMPTY_RECORD = new ErrorCode("EMPTY_RECORD", "记录为空");
  public static final ErrorCode TOO_MANY_RECORDS = new ErrorCode("TOO_MANY_RECORDS", "记录过多");
  public static final ErrorCode REPEAT_MESSAGE = new ErrorCode("REPEAT_MESSAGE", "重复的消息");  //重复的消息
  public static final ErrorCode USER_NOT_EXIST = new ErrorCode("USER_NOT_EXIST", "用户信息不存在");
  public static final ErrorCode USER_NOT_MATCH = new ErrorCode("USER_NOT_MATCH", "用户信息不匹配");
  public static final ErrorCode BROKER_ASSET_NOT_EXIST = new ErrorCode("BROKER_ASSET_NOT_EXIST", "券商没有分配该币种");
  public static final ErrorCode BROKER_ASSET_EXIST = new ErrorCode("BROKER_ASSET_EXIST", "券商已经分配该币种");
  public static final ErrorCode SYMBOL_FORMAT_ERROR = new ErrorCode("SYMBOL_FORMAT_ERROR", "币对格式错误");
  public static final ErrorCode INVALID_SYMBOL = new ErrorCode("INVALID_SYMBOL", "交易对未生效");
  public static final ErrorCode ORDER_CREATE_ERROR = new ErrorCode("ORDER_CREATE_ERROR", "订单创建失败");
  public static final ErrorCode ORDER_NOT_EXIST = new ErrorCode("ORDER_NOT_EXIST", "订单信息不存在");
  public static final ErrorCode ORDER_HAD_EXIST = new ErrorCode("ORDER_HAD_EXIST", "订单信息已存在");
  public static final ErrorCode ORDER_STATE_ERROR = new ErrorCode("ORDER_STATE_ERROR", "订单状态错误");
  public static final ErrorCode ORDER_HAD_CANCELLED = new ErrorCode("ORDER_HAD_CANCELLED", "订单已撤销");
  public static final ErrorCode ORDER_HAD_PROCESSING = new ErrorCode("ORDER_HAD_PROCESSING", "订单处理中");
  public static final ErrorCode ORDER_HAD_TRADED = new ErrorCode("ORDER_HAD_TRADED", "订单已撮合");
  public static final ErrorCode NO_PERMISSION = new ErrorCode("NO_PERMISSION", "没有权限");  //没有权限
  public static final ErrorCode BROKER_NOT_EXIST = new ErrorCode("BROKER_NOT_EXIST", "券商未入驻");
  public static final ErrorCode ADDRESS_ASSIGN_ERROR = new ErrorCode("ADDRESS_ASSIGN_ERROR", "生成地址出错");

  //安全的操作
  public static final ErrorCode NO_LOGIN = new ErrorCode("NO_LOGIN", "用户未登陆");
  public static final ErrorCode TOKE_HAS_INVALID = new ErrorCode("TOKE_HAS_INVALID", "登陆会话超时");

  //谷歌验证码
  public static final ErrorCode MANAGER_CAN_NOT_RESET_GOOGLE_CODE = new ErrorCode("MANAGER_CAN_NOT_RESET_GOOGLE_CODE", "管理员不能重置谷歌验证码");
  public static final ErrorCode MANAGER_GOOGLE_CODE_HAS_SET = new ErrorCode("MANAGER_GOOGLE_CODE_HAS_SET", "谷歌验证码已经重置");
  public static final ErrorCode GOOGLE_CODE_ERROR = new ErrorCode("GOOGLE_CODE_ERROR", "谷歌验证码错误");
  //mq消息
  public static final ErrorCode MESSAGE_TO_MQ_FAIL = new ErrorCode("MESSAGE_TO_MQ_FAIL", "发送消息到mq失败");

  //邮件短信
  public static final ErrorCode NOT_CONFIG_EMAIL = new ErrorCode("NOT_CONFIG_EMAIL", "邮件未配置");

  //用户信息之类
  public static final ErrorCode NO_REGISTER = new ErrorCode("NO_REGISTER", "未注册");
  public static final ErrorCode LOGIN_PASSWORD_ERROR = new ErrorCode("LOGIN_PASSWORD_ERROR", "登录密码错误");
  public static final ErrorCode ROLE_EXIST = new ErrorCode("ROLE_EXIST", "角色已存在");
  public static final ErrorCode ROLE_CAN_NOT_DELETE = new ErrorCode("ROLE_CAN_NOT_DELETE", "角色不能删除");

  //资产相关
  public static final ErrorCode ADDRESS_STATUS_ERROR = new ErrorCode("ADDRESS_STATUS_ERROR", "地址状态错误");
  private final String errorCode;
  private final String errorMsg;

  private ErrorCode(String errorCode, String errorMsg) {
    this.errorCode = errorCode;
    this.errorMsg = errorMsg;
  }

  public String getErrorCode() {
    return errorCode;
  }

  public String getErrorMsg() {
    return errorMsg;
  }
}
