package ass.management.admin.common.concurrent.hashedwheeltimer;


public interface DefaultBusinessService {

    String findPaidOrderById(int orderId);

    void savePaidOrderById(int orderId);

    String findPaidOrderByNo(String outTradeNo);

}
