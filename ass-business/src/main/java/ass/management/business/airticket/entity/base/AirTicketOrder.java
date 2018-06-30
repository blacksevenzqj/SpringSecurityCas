package ass.management.business.airticket.entity.base;

import ass.management.db.pojo.IncrementDataEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class AirTicketOrder extends IncrementDataEntity {

    @Override
    public boolean isNewRecord() {
        return id == null;
    }

    private Integer id;  // 乘机人行程表ID

    private String orderNum;

    private String ticketOrderType = "1"; // 机票订单类型：1:单程，2:往返

    private String orderChangingType = "1";  // 订单类型：1:默认新单，2:改签订单

    @JsonIgnore
    private String oldOrderNum; // 原始订单号（只针对改签订单）

    private Integer userpId;

    @JsonIgnore
    private Integer adultNum; // 成人数

    @JsonIgnore
    private Integer childrenNum; // 儿童数

    private Integer orderPeopleNum; // 订单总人数

    private Double ticketTotalPrice; // 订单机票总价

    private Double airportFuelTotalPrice; // 机建费 + 燃油费 总价

    @JsonIgnore
    private Double taxesTotalPrice; // 税费总价（国际机票)

    private Double insuranceTotalPrice; // 保险总费用=用户订购保险价格*乘机人数量

    private Double reimbursementVoucherPrice; // 报销凭证 配送 价格

    @JsonIgnore
    private String useCashVolumeFlag; // 使用标识：1:未使用，2:使用

    @JsonIgnore
    private Double useCashVolume; // 使用现金卷金额

    @JsonIgnore
    private Double orderOriginalPrice; // 订单原始总价格

    @JsonIgnore
    private Double orderReductionPrice; // 订单减免总价格

    private Double orderAmount; // 订单支付总价格：为了统一类名称改为orderAmount。原为orderPaymentPrice

    @JsonIgnore
    private Double ticketChangeTotalPrice; // 订单机票改签费用

    @JsonIgnore
    private Double orderRefundTotalAmount; // 订单退款总金额

    private Date paymentTime; // 支付时间

    @JsonIgnore
    private String orderCancelRecord; // 订单取消记录

    @JsonIgnore
    private Date orderCancelTime; // 订单取消时间

    /**
     * 订单状态：以用户 第三方支付（微信）状态：--总订单状态（多用户多行程多款项）保险款项暂时只存在于主订单中。
     */
    private String orderStatus = "1"; // 主订单表 订单状态：PaymentStatus

    private String contactUserName; // 联系人姓名

    private String contactUserPhone;

    private String reimbursementVoucher = "0"; // 报销凭证：0:不需要，需要:1

    private String reimbursementVoucherUserName; // 报销凭证 查收用户名

    private String reimbursementVoucherUserPhone; // 报销凭证 查收用户电话

    private String reimbursementVoucherUserAddress; // 报销凭证 的收货地址。

    private Date createDate;

    private Date updateDate;

    private String delFlag;

    // 支付给51Book的价格（机票结算价+基建+燃油）所有乘机人往返
    @JsonIgnore
    private Double paidFobookTotalPrice;



    // 用户支付 公共属性
    private String orderName = "机票订单"; // 订单名称

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
    private Date notifyPaymentTime; // 回调支付通知时间

    @JsonIgnore
    private String notifyPaymentResult; // 回调支付通知结果

    @JsonIgnore
    private String paymentTransactionId; // 第三方生产的支付订单号


}
