package ass.management.business.visa.entity.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import ass.management.db.pojo.IncrementDataEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@Data
@Entity
@DynamicUpdate
@Table(name="dd_visa_order_master")
public class VisaOrderMaster extends IncrementDataEntity {

    @Override
    public boolean isNewRecord() {
        return id == null;
    }

    @Id
    @GeneratedValue
    @Column(name="id",nullable=true)
    private Integer id;

    @Column(name="order_num",nullable=true)
    private String orderNum;

    private Integer userId;
    private Integer visaId;
    private Integer comboId;
    private String contactUserName;
    private String contactUserPhone;
    private String contactUserEmail;
    private Integer buyNum; // 购买份数

    @Column(name="order_payment_price",nullable=true)
    private Double orderAmount;  // 订单金额，支付价格

    @Column(name="order_status",nullable=true)
    private String orderStatus = "1"; // 订单状态：PaymentStatus

    private String orderCancelRemark; // 退款原因

    @JsonIgnore
    private BigDecimal backPrice; // 退换的钱

    private String visaType; // 签证收件类型
    private String visaAddress; // 签证收件地址
    private String userRemark; // 用户留言
    private Date sendVisaTime; // 邮寄签证时间
    private String sendVisaInfo; // 邮寄签证信息
    private Date createTime = new Date(); // 创建时间;

//    @JsonIgnore
//    @Column(name="del_flag",nullable=true)
//    private String delFlag;



    // 用户支付 公共属性
    @Column(name="order_name",nullable=true)
    private String orderName = "签证订单"; // 订单名称

    /**
     * 交易类型：
     * 一、微信：1:WXPAY_APP，2:WXPAY_JSAPI，3:WXPAY_MWEB，4:WXPAY_NATIVE
     * 二、支付宝：
     */
    @JsonIgnore
    @Column(name="trade_type",nullable=true)
    private String tradeType;

    @JsonIgnore
    @Column(name="uniform_payment_time",nullable=true)
    private Date uniformPaymentTime; // 统一支付时间

    @JsonIgnore
    @Column(name="uniform_payment_result",nullable=true)
    private String uniformPaymentResult; // 统一支付结果

    @JsonIgnore
    @Column(name="notify_payment_time",nullable=true)
    private Date notifyPaymentTime; // 回调支付通知时间

    @JsonIgnore
    @Column(name="notify_payment_result",nullable=true)
    private String notifyPaymentResult; // 回调支付通知结果

    @JsonIgnore
    @Column(name="payment_transaction_id",nullable=true)
    private String paymentTransactionId; // 第三方生产的支付订单号

   
}