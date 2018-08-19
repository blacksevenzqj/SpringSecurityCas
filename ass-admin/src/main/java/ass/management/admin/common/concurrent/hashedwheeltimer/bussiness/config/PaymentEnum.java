package ass.management.admin.common.concurrent.hashedwheeltimer.bussiness.config;


import ass.management.admin.common.concurrent.hashedwheeltimer.bussiness.exception.BestPayException;

public enum PaymentEnum {

    UNPAID("1", "未支付"),

    PAYMENT_CANCEL_SUCCESS("2", "订单取消/成功"),

    PAYMENT_CANCEL_FAIL("3", "订单取消/失败"),

    PAYMENT_SUCCESSFUL("4", "支付成功"),

    PAYMENT_FAILED("5", "支付失败"),

    EXPIRED("6", "过期未支付"),

    REFUND_SUCCESSFUL("7", "退款成功"),

    REFUND_FAILED("8", "退款失败")
    ;

    private String code;

    private String name;

    PaymentEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static PaymentEnum getByCode(String code) {
        for (PaymentEnum paymentStatusEnum : PaymentEnum.values()) {
            if (paymentStatusEnum.getCode().equals(code)) {
                return paymentStatusEnum;
            }
        }
        throw new BestPayException(BestPayResultEnum.PAY_STATUS_ERROR);
    }

}
