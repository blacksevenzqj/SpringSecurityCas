package ass.management.admin.common.concurrent.hashedwheeltimer.bussiness.entry;

import ass.management.db.pojo.IncrementDataEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class DefaultPayOrder extends IncrementDataEntity {

    @Override
    public boolean isNewRecord() {
        return orderNum == null;
    }

    @JsonIgnore
    private String orderNum; // 订单编号
    @JsonIgnore
    private Double orderAmount;  // 订单金额
    @JsonIgnore
    private String orderName; // 订单名称

    // 订单状态：1:未支付，2:支付成功，3:支付失败，4:过期未支付，5:退款成功，6:退款失败
    @JsonIgnore
    private String orderStatus = "1";

    /**
     * 交易类型：
     * 一、微信：1:WXPAY_APP，2:WXPAY_JSAPI，3:WXPAY_MWEB，4:WXPAY_NATIVE
     * 二、支付宝：
     */
    @JsonIgnore
    private String tradeType;

    @JsonIgnore
    private Date uniformPaymentTime; // 统一支付时间

    @JsonIgnore
    private String uniformPaymentResult; // 统一支付结果

    @JsonIgnore
    private String paymentTransactionId; // 第三方生产的支付订单号

    @JsonIgnore
    private Date notifyPaymentTime; // 回调支付通知时间

    @JsonIgnore
    private String notifyPaymentResult; // 回调支付通知结果

}
