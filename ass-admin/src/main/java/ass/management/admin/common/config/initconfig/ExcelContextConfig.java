package ass.management.admin.common.config.initconfig;

import ass.management.admin.common.excel.ExcelContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
