package ass.management.admin.common.config.initconfig;

import ass.management.admin.common.config.initparam.ExecutorsConfiguration;
import ass.management.admin.common.concurrent.executor.DefaultExecutors;
import ass.management.common.config.ConfigParameter;
import ass.management.common.utils.SystemPropertyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class InitializationConfiguration {

    @Autowired
    ExecutorsConfiguration parameterConfiguration;

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
