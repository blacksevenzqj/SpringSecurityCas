package ass.management.admin.common.config.initparam;

import ass.management.common.config.DefaultDelayMessageConfiguration;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "delaymessage.visa")
@Data
@Component
public class VisaDelayMessageConfiguration extends DefaultDelayMessageConfiguration {
}
