package ass.management.admin.common.concurrent.hashedwheeltimer.bussiness.service;

import ass.management.admin.common.concurrent.hashedwheeltimer.bussiness.entry.DefaultPayOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service(value = "visaOrderService")
public class VisaOrderServiceImpl implements VisaOrderService {

    @Override
    public void savePaidOrderById(DefaultPayOrder defaultPayOrder) {
        // TODO ... savePaidOrderById没完成
        log.info("savePaidOrderById");
    }

    @Override
    public DefaultPayOrder findPaidOrderByNo(String outTradeNo) {
        // TODO ... findPaidOrderByNo没完成
        DefaultPayOrder defaultPayOrder = new DefaultPayOrder();
        defaultPayOrder.setOrderNum(outTradeNo);
        defaultPayOrder.setOrderName("飞飞飞");
        defaultPayOrder.setOrderStatus("1");
        return new DefaultPayOrder();
    }

}
