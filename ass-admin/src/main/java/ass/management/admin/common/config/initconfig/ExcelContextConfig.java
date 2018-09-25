package ass.management.admin.common.config.initconfig;

import ass.management.admin.common.excel.ExcelContext;
import ass.management.elasticsearch.config.ESClientDecorator;
import ass.management.elasticsearch.config.ElasticsProperties;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ExcelContextConfig {

    @Value("${excel.context.path}")
    private String contextPath;

    /**
     * 初始化
     */
    @Bean("excelContext")
    public ExcelContext getExcelContext() {
        return new ExcelContext(contextPath);
    }

}
