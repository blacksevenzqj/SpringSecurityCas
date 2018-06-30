package ass.management.db.datasource.tabswitch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;

import javax.sql.DataSource;

/**
 * 动态数据源事务管理器
 */
public class DynamicSwitchDataSourceTransactionManager extends DataSourceTransactionManager {

    private static final Logger logger = LoggerFactory.getLogger(DynamicSwitchDataSourceTransactionManager.class);

    public DynamicSwitchDataSourceTransactionManager(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected void doBegin(Object transaction, TransactionDefinition definition) {
        //设置数据源
        boolean readOnly = definition.isReadOnly();
        //只读事务到读库，读写事务到写库
        if (readOnly) {
            logger.info("DynamicSwitchDataSourceTransactionManager is readOnly");
        } else {
            logger.info("DynamicSwitchDataSourceTransactionManager is writer");
        }
        super.doBegin(transaction, definition);
    }

    @Override
    protected void doCleanupAfterCompletion(Object transaction) {
        super.doCleanupAfterCompletion(transaction);
        //清理本地线程的数据源
        logger.info("DynamicSwitchDataSourceTransactionManager is clearDataSource");
    }
}