package ass.management.business.airticket.entity.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ass.management.db.pojo.IncrementDataEntity;
import java.util.Date;

/**
 * 单乘机人、单行程信息
 */
@Data
public class AirTicketOrderDetailRouting extends IncrementDataEntity {

    @Override
    public boolean isNewRecord() {
        return id == null;
    }

    private Integer id;  // 乘机人行程表ID

    private Integer orderDetailId;  // 订单明细（乘机人表主键ID）

    private Integer routingId;  // 行程表主键ID

    private String orderNum;  // 订单编号

    private Integer userpId; // 登陆用户Id：下单用户

    private String ticketRoutingType; // 1:去程，2:返程

    private String insuranceIds; // 订购的保险ids

    private Double airTicketPrice; // 机票价格

    private Double insurancePrice; // 该乘机人保险价格

    private Double airportFuelPrice; // 机建费 + 燃油费

    @JsonIgnore
    private Double taxesPrice; // 税费

    @JsonIgnore
    private Double refundAmount; // 退款金额

    @JsonIgnore
    private String refundAmountMark; // 订单标记（订单退款记录）

    @JsonIgnore
    private Double ticketChangePrice; // 改签机票价格

    @JsonIgnore
    private String ticketChangeInfo; // 改签机票信息

    private String ticketNo; // 机票出票后的票号：由 根据旅客信息创建PNR及订单[CO1] notifiedUrl 回调的属性 type=1 出票成功时：ticketNos参数 "," 号分割得到。

    /**
     * 订单状态：以用户 已支付 第三方支付（微信）成功后，向 51Book 支付的状态： --该用户该行程订单状态
     * 1:未支付，2:订单取消，3:支付成功/出票中，4:支付成功/出票成功，5:支付失败。
     * 6:申请改签/改签中，7:申请改签/改签成功，8:申请改签/改签失败。
     * 9:申请退款、退票，10:申请退款、退票/不通过，11:申请退款、退票/通过/退款中，12:申请退款、退票/通过/退款成功。
     */
    private String orderStatus = "1"; // 订单状态

    @JsonIgnore
    private Date createDate;

    @JsonIgnore
    private Date updateDate;

    @JsonIgnore
    private String delFlag;


    // 根据旅客信息创建PNR及订单[CO1] 返回属性
    @JsonIgnore
    private Integer policyId;  // 政策ID：一行程多人一号

    @JsonIgnore
    private String liantuoOrderNo; // 51Book订单号：一行程多人一号

    @JsonIgnore
    private String pnrNo; // PNR订单号：一行程多人一号

    @JsonIgnore
    private Double paidFobookPrice;  // 支付给51Book的价格（机票结算价+基建+燃油）单行程

}
