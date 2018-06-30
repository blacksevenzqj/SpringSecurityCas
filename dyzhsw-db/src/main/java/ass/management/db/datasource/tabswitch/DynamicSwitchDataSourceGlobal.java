package ass.management.db.datasource.tabswitch;

public enum DynamicSwitchDataSourceGlobal {
    /**
     * 后台数据源
     */
    MANAGEMENT("1", "后台数据源"),
    /**
     * 业务数据源
     */
    BUSINESS("2", "业务数据源");

    private String code;

    private String desc;

    DynamicSwitchDataSourceGlobal(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }
    public String getDesc() {
        return desc;
    }

    public static DynamicSwitchDataSourceGlobal getByName(String name) {
        for (DynamicSwitchDataSourceGlobal dynamicEnum : DynamicSwitchDataSourceGlobal.values()) {
            if (dynamicEnum.name().equals(name)) {  // dynamicEnum.name(): MANAGEMENT 或 BUSINESS
                return dynamicEnum;
            }
        }
        return BUSINESS;
    }

}