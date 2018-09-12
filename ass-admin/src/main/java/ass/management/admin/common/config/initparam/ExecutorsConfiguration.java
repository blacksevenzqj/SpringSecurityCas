package ass.management.admin.common.config.initparam;

import ass.management.common.config.ConfigParameter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = ConfigParameter.Executor.EXECUTOR_FACTORY_NAME)
public class ExecutorsConfiguration {

    private String defaultName;
    private String secondName;


    public String getDefaultName() {
        return defaultName;
    }
    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }

    public String getSecondName() {
        return secondName;
    }
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
}
