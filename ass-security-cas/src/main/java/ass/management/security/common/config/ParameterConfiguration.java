package ass.management.security.common.config;

import ass.management.common.config.ConfigParameter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = ConfigParameter.Executor.EXECUTOR_FACTORY_NAME)
public class ParameterConfiguration {

    private String defaultName;


    public String getDefaultName() {
        return defaultName;
    }
    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }
}
