package ass.management.admin.common.concurrent.hashedwheeltimer;

import ass.management.admin.common.config.initparam.VisaDelayMessageConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component(value = "visaDelayMessageWatch")
public class VisaDelayMessageWatch extends DefaultDelayMessageWatch<VisaOrderServiceImpl, VisaDelayMessageConfiguration> {
}
