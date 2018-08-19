package ass.management.admin.common.concurrent.hashedwheeltimer.watch.impl;

import ass.management.admin.common.concurrent.hashedwheeltimer.bussiness.service.VisaOrderService;
import ass.management.admin.common.concurrent.hashedwheeltimer.watch.DefaultDelayMessageWatch;
import ass.management.admin.common.config.initparam.VisaDelayMessageConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component(value = "visaDelayMessageWatch")
public class VisaDelayMessageWatch extends DefaultDelayMessageWatch<VisaOrderService, VisaDelayMessageConfiguration> {
}
