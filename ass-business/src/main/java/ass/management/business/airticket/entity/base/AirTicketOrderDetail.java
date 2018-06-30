package ass.management.business.airticket.entity.base;

import lombok.Data;
import ass.management.db.pojo.IncrementDataEntity;

import java.util.Date;

/**
 * 乘机人信息：单个乘机人信息（往返）
 */
@Data
public class AirTicketOrderDetail extends IncrementDataEntity {

    @Override
    public boolean isNewRecord() {
        return id == null;
    }

    protected Integer id;  // 乘机人行程表ID

    protected Integer orderId;  // 订单主表ID

    private String orderNum;

    private Integer userpId;

    private Double airTicketRoundPrice; // 该乘机人机票价格（往返）

    private Double insuranceRoundPrice; // 该乘机人保险价格总和（往返）

    private Double airportFuelRoundPrice;  // 该乘机人机建费 + 燃油费（往返）

    private Double taxesRoundPrice;  // 税费（往返）

    private Double refundRoundAmount;  // 退款金额（往返）

    private Double ticketRoundChangePrice; // 改签费用（往返）

    private String passengerName; // 乘机人姓名

    private String passengerEnglishName; // 乘机人英文名称

    private Integer passengerAge; // 乘机人年龄

    private String passengerAgeLevel; // 乘机人年龄层次：0:成人，1:儿童

    private String passengerGender; // 乘机人性别：1:男，2:女

    private String passengerPhone; // 乘机人电话

    private String passengerBirthDay; // 乘机人生日

    private String passengerCardType; // 乘机人证件类别：1:身份证，2:护照

    private String passengerCardNum; // 乘机人证件号码

    private String passengerCardIssuingCountry; // 乘机人证件发行国家：国家二字码

    private String passengerCardValid;  // 乘机人证件有效期

    private String passengerCitizenShip; // 乘机人国籍：汉字

    /**
     * 注意：暂时不使用 本属性
     * 订单状态：以用户 已支付 第三方支付（微信）成功后，向 51Book 支付的状态： --该用户往返两个行程合并订单状态
     * 以下都是以向 51Book 支付 往返（两个单行程合并） 订单状态：
     * 1:未支付，2:订单取消，3:支付成功/出票中，4:支付成功/出票成功，5:支付失败。
     * 6:申请改签/改签中，7:申请改签/改签成功，8:申请改签/改签失败。
     * 9:申请退款、退票，10:申请退款、退票/不通过，11:申请退款、退票/通过/退款中，12:申请退款、退票/通过/退款成功。
     */
    private String orderStatus = "1"; // 乘机人订单状态：往返（两个单行程合并） 订单状态

    private Date createDate;

    private Date updateDate;

    private String delFlag;

    private Double paidFobookRoundPrice; // 该乘机人支付给51Book的价格（机票结算价+基建+燃油）往返行程

}
