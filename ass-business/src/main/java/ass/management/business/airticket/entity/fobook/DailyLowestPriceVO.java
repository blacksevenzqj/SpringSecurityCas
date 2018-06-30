package ass.management.business.airticket.entity.fobook;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ass.management.db.pojo.IncrementDataEntity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 单行程表
 */
@Data
public class DailyLowestPriceVO extends IncrementDataEntity {

    @Override
    public boolean isNewRecord() {
        return id == null;
    }

    @JsonIgnore
    protected Integer id;

    @JsonIgnore
    protected Integer orderId; // 订单主表ID

    @JsonIgnore
    private Integer userpId;

    @JsonIgnore
    protected String orderNum; // 订单号

    protected String ticketOrderType; // 机票订单类型：1:单程，2:往返

    protected String ticketRoutingType; // 1:去程，2:返程

    protected String orgCityName; // 出发城市

    protected String orgCitySzm; // 出发城市三字码

    protected String dstCityName; // 抵达城市

    protected String dstCitySzm; // 抵达城市三字码

    protected Double distance; // 航程公里数

    protected String airlineCompany; // 航空公司名称

    protected String flightNo; // 航班号

    @JsonIgnore
    protected Double basePrice; // 	Y舱价格

    protected String orgAirportName; // 出发机场

    protected String orgAirportSzm; // 出发机场三字码：注意接口中的字段名为 orgCity，是错误的

    @JsonIgnore
    protected String orgJetquay; // 始发航站楼

    protected String arrAirportName; // 抵达机场

    protected String arrAirportSzm; // 抵达机场三字码：注意接口中的字段名为 dstCity，是错误的

    @JsonIgnore
    protected String dstJetquay; // 到达航站楼

    protected String date; // 出发日期

    protected String depTime; // 出发时间（小时分钟）

    @JsonIgnore
    protected String depModifyTime; // 起飞修正时间

    protected String depDateTime; // 出发时间（自己拼接）

    protected String arriTime; // 	到达时间（小时分钟）

    @JsonIgnore
    protected String arriModifyTime; // 降落修正时间

    protected String arriDateTime; // 	到达时间（自己拼接）

    protected String meal; // 餐食标识

    protected String planeType; // 机型：除CR2、CRJ、EMB、ERJ、MA6 5种小机型没有机场建设费，其他机型收取机场建设费

    protected Integer stopnum; // 经停次数

    @JsonIgnore
    protected Boolean isElectronicTicket; // 是否电子客票

    protected Double airportTax = 0.00; // 成人的机建费

    protected Double fuelTax = 0.00; // 成人的燃油费

    protected String seatCode; // 舱位码

    protected Integer seatType; // 是否特价舱位

    protected String seatStatus; // 舱位状态

    protected String seatMsg; // 舱位说明

    protected Integer parPrice; // 票面价

    protected Double discount; // 折扣

    protected Double settlePrice; // 成人的结算价：单行程 单乘客 价格

    protected Integer policyId; // 	政策ID：一行程多人一号

    @JsonIgnore
    protected Boolean codeShare; // 是否共享航班

    @JsonIgnore
    protected String shareNum; // 共享航班号

    @JsonIgnore
    protected String link; // 联接协议级别

    @JsonIgnore
    public Date createDate;

    @JsonIgnore
    public Date updateDate;

    @JsonIgnore
    public String delFlag;

    public void setDepDateTime() {
        this.depDateTime = date + " " + depTime.substring(0, 2) + ":" + depTime.substring(2, 4);
    }
    public void setArriDateTime() {
        if(Long.valueOf(arriTime).compareTo(Long.valueOf(depTime)) < 0){
            DateTimeFormatter ymd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate local = LocalDate.parse(date, ymd);
            this.arriDateTime = local.plusDays(1) + " " + arriTime.substring(0, 2) + ":" + arriTime.substring(2, 4);
        }else{
            this.arriDateTime = date + " " + arriTime.substring(0, 2) + ":" + arriTime.substring(2, 4);
        }
    }


    /**
     * 订单状态：以用户 已支付 第三方支付（微信）成功后，向 51Book 支付的状态： --该行程 多用户 订单状态
     * 51Book支付方式为：单行程 多用户 为一个支付订单。
     * 1:未支付，2:订单取消/成功，3:订单取消/失败，4:支付成功/出票中，5:支付成功/出票成功，6:支付失败/自动支付PA1失败，7:支付失败/供应商拒单，8:支付失败/出票失败，
     * 9:申请改签/改签中，10:申请改签/改签成功，11:申请改签/改签失败。
     * 12:申请退款、退票，13:申请退款、退票/不通过，14:申请退款、退票/通过/退款中，15:申请退款、退票/通过/退款成功。
     */
    @JsonIgnore
    private String orderStatus = "1"; // 该行程 多乘机人 订单状态：FoPaymentStatus


    // 根据旅客信息创建PNR及订单[CO1] 返回属性
    @JsonIgnore
    private String liantuoOrderNo; // 51Book订单号：一行程多人一号

    @JsonIgnore
    private String pnrNo; // PNR订单号：一行程多人一号

    @JsonIgnore
    protected Double settleTotalPrice; // 成人的结算价：单行程 多乘客 价格


    // 订单自动支付[PA1] 返回结果属性
    @JsonIgnore
    protected String foAutoPayReturnCode; // 成功标识：S-成功 / F-失败

    @JsonIgnore
    protected String foAutoPayReturnMsg; // 失败时的错误信息

    @JsonIgnore
    protected String foAutoPayOrderNo; // 订单号

    @JsonIgnore
    protected String foAutoPayOrderStatus; // 订单状态

    @JsonIgnore
    protected Double foAutoPayTotalTicketPrice;  // 票面总价	Double

    @JsonIgnore
    protected Double foAutoPayTotalFuelTax;  // 燃油总价

    @JsonIgnore
    protected Double foAutoPayTotalAirportTax; // 机建总价

    @JsonIgnore
    protected Double foAutoPayTotalPay; // 总结算价

    @JsonIgnore
    protected String foAutoPayTradeNo; // 支付宝交易号：订单支付后产生的支付宝交易号

    @JsonIgnore
    protected String foAutoPayPayerAccount; // 支付帐号

    @JsonIgnore
    protected String foAutoPayTime;  // 支付成功的时间



    // 根据旅客信息创建PNR及订单[CO1]  notifiedUrl 回调的属性
    /**
     * 供应商拒单返回：
     */
    @JsonIgnore
    private String reason; // 拒单原因

    /**
     * 出票失败通知：
     */
    @JsonIgnore
    private String refundNo; // 退款号

    @JsonIgnore
    private String orderNo; // 关联的订单号

    @JsonIgnore
    private String refundTime; // 退款时间

    @JsonIgnore
    private String refundPrice; // 退款金额

}
