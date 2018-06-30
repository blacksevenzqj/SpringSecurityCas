//package school.admin.common.config.druid.single;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.AutoConfigureBefore;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//import java.sql.SQLException;
//
//@Slf4j
//@Configuration
//@EnableConfigurationProperties(DruidProperties.class)
//@ConditionalOnClass(DruidDataSource.class)
//@ConditionalOnProperty(prefix = "spring.datasource", name = "url")
//@AutoConfigureBefore(DataSourceAutoConfiguration.class)
//public class DruidAutoConfiguration {
//
//    @Autowired
//    private DruidProperties properties;
//
//    @Bean
//    public DataSource dataSource() {
//        log.info("自定义 DruidDataSource 配置生效");
//        DruidDataSource datasource = new DruidDataSource();
//        datasource.setUrl(properties.getUrl());
//        datasource.setUsername(properties.getUsername());
//        datasource.setPassword(properties.getPassword());
//        datasource.setDriverClassName(properties.getDriverClassName());
//
//        //configuration
//        datasource.setInitialSize(properties.getInitialSize());
//        datasource.setMinIdle(properties.getMinIdle());
//        datasource.setMaxActive(properties.getMaxActive());
//        datasource.setMaxWait(properties.getMaxWait());
//        datasource.setTimeBetweenEvictionRunsMillis(properties.getTimeBetweenEvictionRunsMillis());
//        datasource.setMinEvictableIdleTimeMillis(properties.getMinEvictableIdleTimeMillis());
//        datasource.setValidationQuery(properties.getValidationQuery());
//        datasource.setTestWhileIdle(properties.isTestWhileIdle());
//        datasource.setTestOnBorrow(properties.isTestOnBorrow());
//        datasource.setTestOnReturn(properties.isTestOnReturn());
//        datasource.setPoolPreparedStatements(properties.isPoolPreparedStatements());
//        datasource.setMaxPoolPreparedStatementPerConnectionSize(properties.getMaxPoolPreparedStatementPerConnectionSize());
//        try {
//            datasource.setFilters(properties.getFilters());
//        } catch (SQLException e) {
//            log.error("druid configuration initialization filter: ", e);
//        }
//        datasource.setConnectionProperties(properties.getConnectionProperties());
//        return datasource;
//    }
//}
