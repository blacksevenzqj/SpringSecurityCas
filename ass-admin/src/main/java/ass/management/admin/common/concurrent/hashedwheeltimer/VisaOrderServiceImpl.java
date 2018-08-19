package ass.management.admin.common.concurrent.hashedwheeltimer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service(value = "visaOrderService")
public class VisaOrderServiceImpl implements DefaultBusinessService {

    @Override
    public String findPaidOrderById(int orderId) {
        return null;
    }

    @Override
    public void savePaidOrderById(int orderId) {

    }

    @Override
    public String findPaidOrderByNo(String outTradeNo) {
        return null;
    }

}
