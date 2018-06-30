package ass.management.db.datasource.tabswitch;


/**
 * 动态数据源持有者
 */
public final class DynamicSwitchDataSourceHolder {

    /**
     * 动态数据源存储
     */
    private static final ThreadLocal<DynamicSwitchDataSourceGlobal> DYNAMIC_SWITCH_DATA_SOURCE_GLOBAL_THREAD_LOCAL = new ThreadLocal<>();

    private DynamicSwitchDataSourceHolder() {
        //
    }

    /**
     * 存放数据源
     *
     * @param dataSource 数据源
     */
    public static void putDataSource(DynamicSwitchDataSourceGlobal dataSource) {
        DYNAMIC_SWITCH_DATA_SOURCE_GLOBAL_THREAD_LOCAL.set(dataSource);
    }

    /**
     * 获取数据源
     *
     * @return the data source
     */
    public static DynamicSwitchDataSourceGlobal getDataSource() {
        return DYNAMIC_SWITCH_DATA_SOURCE_GLOBAL_THREAD_LOCAL.get();
    }

    /**
     * 清除数据源
     */
    public static void clearDataSource() {
        DYNAMIC_SWITCH_DATA_SOURCE_GLOBAL_THREAD_LOCAL.remove();
    }

}