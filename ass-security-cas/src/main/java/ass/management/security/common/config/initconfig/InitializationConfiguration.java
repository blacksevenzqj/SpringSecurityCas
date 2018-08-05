package ass.management.security.common.config.initconfig;

import ass.management.common.config.ConfigParameter;
import ass.management.common.utils.SystemPropertyUtil;
import ass.management.security.common.config.ParameterConfiguration;
import ass.management.security.common.executor.DefaultExecutors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class InitializationConfiguration {

    @Autowired
    ParameterConfiguration parameterConfiguration;

    @PostConstruct
    public void init(){
        setSystemProperty();
        DefaultExecutors.getDefaultExecutors();
    }

    @PreDestroy
    public void dostory(){
        DefaultExecutors.getDefaultExecutors().shutdown();
    }


    private void setSystemProperty(){
        SystemPropertyUtil.setProperty(ConfigParameter.Executor.DEFAULT_FACTORY_NAME, parameterConfiguration.getDefaultName());
    }


}
