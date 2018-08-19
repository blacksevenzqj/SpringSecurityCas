package ass.management.admin.common.concurrent.hashedwheeltimer.bussiness.service;


import ass.management.admin.common.concurrent.hashedwheeltimer.bussiness.entry.DefaultPayOrder;

public interface DefaultBusinessService {

    void savePaidOrderById(DefaultPayOrder defaultPayOrder);

    DefaultPayOrder findPaidOrderByNo(String outTradeNo);

}
