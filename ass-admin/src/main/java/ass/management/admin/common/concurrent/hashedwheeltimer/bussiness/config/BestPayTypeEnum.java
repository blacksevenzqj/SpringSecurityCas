package ass.management.admin.common.concurrent.hashedwheeltimer.bussiness.config;


import ass.management.admin.common.concurrent.hashedwheeltimer.bussiness.exception.BestPayException;

/**
 * 支付方式
 */
public enum BestPayTypeEnum {

    WXPAY_APP("APP", "微信APP支付"),

    WXPAY_JSAPI("JSAPI", "微信公众账号支付"),

    WXPAY_MWEB("MWEB", "微信H5支付"),

    WXPAY_NATIVE("NATIVE", "微信原生扫描支付"),


    ALIPAY_APP("alipay_app", "支付宝app"),

    ALIPAY_PC("alipay_pc", "支付宝pc"),

    ALIPAY_WAP("alipay_wap", "支付宝wap")
    ;

    private String code;

    private String name;

    BestPayTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static BestPayTypeEnum getByCode(String code) {
        for (BestPayTypeEnum bestPayTypeEnum : values()) {
            if (bestPayTypeEnum.getCode().equals(code)) {
                return bestPayTypeEnum;
            }
        }
        throw new BestPayException(BestPayResultEnum.PAY_TYPE_ERROR);
    }

    public static BestPayTypeEnum getByEnumName(String enumName) {
        for (BestPayTypeEnum bestPayTypeEnum : values()) {
            if (bestPayTypeEnum.toString().equals(enumName)) {
                return bestPayTypeEnum;
            }
        }
        throw new BestPayException(BestPayResultEnum.PAY_TYPE_ERROR);
    }

}
