package ass.management.admin.common.concurrent.hashedwheeltimer.bussiness.config;


import ass.management.admin.common.concurrent.hashedwheeltimer.bussiness.exception.BestPayException;

public enum BaseNotifyEnum {

    XML_PARSING_FAILED(0, "XML解析异常"),
    SIGNATURE_VERIFICATION_FAILED(1, "签名验证失败"),
    RETURNCODE_FAIL(2, "通信标识失败"),
    RESULTCODE_FAIL(3, "交易结果失败"),
    NOAUTH(4, "商户未开通此接口权限"),
    NOTENOUGH(5, "用户帐号余额不足"),
    ORDERPAID(6, "商户订单已支付，无需重复操作"),
    ORDERCLOSED(7, "当前订单已关闭，无法支付"),
    SYSTEMERROR(8, "系统超时")
    ;

    private Integer code;

    private String msg;

    BaseNotifyEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public BaseNotifyEnum getBaseNotifyEnum(Integer code){
        for(BaseNotifyEnum baseNotifyEnum : BaseNotifyEnum.values()){
            if(baseNotifyEnum.code == code){
                return baseNotifyEnum;
            }
        }
        throw new BestPayException(BestPayResultEnum.NOTIFY_STATUS_ERROR);
    }

}
