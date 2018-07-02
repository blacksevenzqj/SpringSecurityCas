package ass.management.security.modules.business.ass.entity;


/**
 * 能耗设备数据
 */
public class EnergyEquipmentData {
    private long id;

    private long areaId;

    private long districtId;

    private long siteId;

    private long deviceId;

    private String code;

    private String totalPower;

    private String currentA;

    private String currentB;

    private String currentC;

    private long dataTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getAreaId() {
        return areaId;
    }

    public void setAreaId(long areaId) {
        this.areaId = areaId;
    }

    public long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(long districtId) {
        this.districtId = districtId;
    }

    public long getSiteId() {
        return siteId;
    }

    public void setSiteId(long siteId) {
        this.siteId = siteId;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public String getTotalPower() {
        return totalPower;
    }

    public void setTotalPower(String totalPower) {
        this.totalPower = totalPower;
    }

    public String getCurrentA() {
        return currentA;
    }

    public void setCurrentA(String currentA) {
        this.currentA = currentA;
    }

    public String getCurrentB() {
        return currentB;
    }

    public void setCurrentB(String currentB) {
        this.currentB = currentB;
    }

    public String getCurrentC() {
        return currentC;
    }

    public void setCurrentC(String currentC) {
        this.currentC = currentC;
    }

    public long getDataTime() {
        return dataTime;
    }

    public void setDataTime(long dataTime) {
        this.dataTime = dataTime;
    }
}
