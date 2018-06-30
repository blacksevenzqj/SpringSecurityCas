package ass.management.admin.common.config.druid.dynamic.tabswitch;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import ass.management.admin.common.exception.base.SystemException;
import ass.management.db.datasource.tabswitch.DynamicSwitchDataSource;
import ass.management.db.datasource.tabswitch.DynamicSwitchDataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 配置多数据源
 */
@Configuration
public class DynamicDataSourceConfig {

    @Primary
    @Bean(name = "managementDataSource")
    @ConfigurationProperties("spring.datasource.management")
    public DataSource  managementDataSource(){
        return new DruidDataSource();
    }

    @Bean(name = "businessDataSource")
    @ConfigurationProperties("spring.datasource.business")
    public DataSource  businessDataSource(){
        return DruidDataSourceBuilder.create().build();  // 一样的
    }

    @Bean(name = "dynamicSwitchDataSource")
    public DataSource dynamicSwitchDataSource() {
        DynamicSwitchDataSource dynamicSwitchDataSource = new DynamicSwitchDataSource();
        dynamicSwitchDataSource.setManagementDataSource(managementDataSource());
        dynamicSwitchDataSource.setBusinessDataSource(businessDataSource());
        return dynamicSwitchDataSource;
    }

    /**
     * 用于：事务---“动静分离”。只是切换数据源的话，不用也可以。
     * Dynamic transaction manager data source transaction manager.
     * @param dynamicSwitchDataSource the dynamic data source
     * @return the data source transaction manager
     */
    @Bean(name = "dynamicTransactionManager")
    public DataSourceTransactionManager dynamicTransactionManager(@Qualifier("dynamicSwitchDataSource") DataSource dynamicSwitchDataSource) {
        return new DynamicSwitchDataSourceTransactionManager(dynamicSwitchDataSource);
    }


    // MyBatis的数据源：
    /**
     * Dynamic sql session factory sql session factory.
     * @param dynamicSwitchDataSource the dynamic data source
     * @param properties        the properties
     * @return the sql session factory
     */
    @Bean
    @ConfigurationProperties(prefix = MybatisProperties.MYBATIS_PREFIX)
    public SqlSessionFactory dynamicSqlSessionFactory(@Qualifier("dynamicSwitchDataSource") DataSource dynamicSwitchDataSource, MybatisProperties properties) {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dynamicSwitchDataSource);
        sessionFactory.setConfigLocation(new DefaultResourceLoader().getResource(properties.getConfigLocation()));
        sessionFactory.setMapperLocations(properties.resolveMapperLocations());
        try {
            return sessionFactory.getObject();
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }


    // JPA数据源：在“只读事务”上存在问题，暂时不用。
//    /**
//     * 我们通过LocalContainerEntityManagerFactoryBean来获取EntityManagerFactory实例
//     * 注意：LocalContainerEntityManagerFactoryBean和userEntityManagerFactory方法其中一个注解@Primary即可，不然启动会报错。
//     */
//    @Bean(name = "localContainerEntityManagerFactoryBean")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
//        return builder
//                .dataSource(dynamicSwitchDataSource())
//                .packages("school.management.business") //设置实体类所在位置
//                .persistenceUnit("schoolPersistenceUnit")
//                .build(); //不要在这里直接获取EntityManagerFactory
//    }
//    /**
//     * EntityManagerFactory类似于Hibernate的SessionFactory,mybatis的SqlSessionFactory
//     * 总之,在执行操作之前,我们总要获取一个EntityManager,这就类似于Hibernate的Session，mybatis的sqlSession.
//     */
//    @Bean(name = "entityManagerFactory")
//    @Primary
//    public EntityManagerFactory entityManagerFactory(EntityManagerFactoryBuilder builder) {
//        return this.entityManagerFactoryBean(builder).getObject();
//    }
//    /**
//     * 配置事物管理器
//     */
//    @Bean(name = "transactionManager")
//    @Primary
//    public PlatformTransactionManager writeTransactionManager(EntityManagerFactoryBuilder builder) {
//        return new JpaTransactionManager(this.entityManagerFactory(builder));
//    }


}
