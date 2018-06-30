package ass.management.db.datasource.tabswitch;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.HashMap;
import java.util.Map;

/**
 * 动态数据源
 */
public class DynamicSwitchDataSource extends AbstractRoutingDataSource {

    /**
     * 后台数据源
     */
    private Object managementDataSource;
    /**
     * 业务数据源
     */
    private Object businessDataSource;


    @Override
    public void afterPropertiesSet() {
        if (this.managementDataSource == null) {
            throw new IllegalArgumentException("Property 'managementDataSource' is required");
        }
        setDefaultTargetDataSource(managementDataSource);
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DynamicSwitchDataSourceGlobal.MANAGEMENT.name(), managementDataSource);
        if (businessDataSource != null) {
            targetDataSources.put(DynamicSwitchDataSourceGlobal.BUSINESS.name(), businessDataSource);
        }
        setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        DynamicSwitchDataSourceGlobal dynamicSwitchDataSourceGlobal = DynamicSwitchDataSourceHolder.getDataSource();
        if (dynamicSwitchDataSourceGlobal == null
                || dynamicSwitchDataSourceGlobal == DynamicSwitchDataSourceGlobal.MANAGEMENT) {
            return dynamicSwitchDataSourceGlobal.MANAGEMENT.name();
        }
        return dynamicSwitchDataSourceGlobal.BUSINESS.name();
    }



    public Object getManagementDataSource() {
        return managementDataSource;
    }
    public void setManagementDataSource(Object managementDataSource) {
        this.managementDataSource = managementDataSource;
    }

    public Object getBusinessDataSource() {
        return businessDataSource;
    }
    public void setBusinessDataSource(Object businessDataSource) {
        this.businessDataSource = businessDataSource;
    }
}
