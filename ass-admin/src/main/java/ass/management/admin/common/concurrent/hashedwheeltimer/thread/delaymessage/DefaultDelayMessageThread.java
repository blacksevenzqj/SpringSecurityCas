package ass.management.admin.common.concurrent.hashedwheeltimer.thread.delaymessage;

import ass.management.admin.common.concurrent.hashedwheeltimer.bussiness.entry.DefaultPayOrder;
import ass.management.admin.common.concurrent.hashedwheeltimer.bussiness.config.PaymentEnum;
import ass.management.admin.common.concurrent.hashedwheeltimer.bussiness.service.DefaultBusinessService;
import io.netty.util.Timeout;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class DefaultDelayMessageThread implements Runnable{

    private DefaultBusinessService defaultBusinessService;
    private Timeout timeout;
    private Map<Timeout, String> timeoutMap;
    private Map<String, Timeout> timeoutOrderMap;

    public DefaultDelayMessageThread(DefaultBusinessService defaultBusinessService, Timeout timeout, Map<Timeout, String> timeoutMap, Map<String, Timeout> timeoutOrderMap) {
        this.defaultBusinessService = defaultBusinessService;
        this.timeout = timeout;
        this.timeoutMap = timeoutMap;
        this.timeoutOrderMap = timeoutOrderMap;
    }

    // 还没做全局数据锁。
    @Override
    public void run() {
        // TODO ... 全局数据锁
        String orderNo = timeoutMap.get(timeout);
        if(StringUtils.isNotBlank(orderNo)) {
            DefaultPayOrder defaultPayOrder = defaultBusinessService.findPaidOrderByNo(orderNo);
            if (defaultPayOrder.getOrderStatus().equals(PaymentEnum.UNPAID.getCode())) {
                defaultPayOrder.setOrderStatus(PaymentEnum.EXPIRED.getCode());
                defaultBusinessService.savePaidOrderById(defaultPayOrder);
            }
            timeoutMap.remove(timeout);
            timeoutOrderMap.remove(orderNo);
        }
    }
}
