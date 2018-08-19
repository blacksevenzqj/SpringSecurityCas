package ass.management.admin.common.concurrent.hashedwheeltimer.bussiness.exception;


import ass.management.admin.common.concurrent.hashedwheeltimer.bussiness.config.BestPayResultEnum;

public class BestPayException extends RuntimeException {

    private Integer code;

    public BestPayException(BestPayResultEnum resultEnum) {
        super(resultEnum.getMsg());
        code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }
}
