package ass.management.security.modules.business.ass.entity;


/**
 * 设备数据
 */
public class DeviceDatas {
    private long id;

    private long areaId;

    private String areaName;

    private long districtId;

    private String districtName;

    private long siteId;

    private String siteName;

    private String waterDosage;

    private String minPressure;
    private String maxPressure;

    private String totalPower;

    private String minCurrentA;
    private String maxCurrentA;

    private String minCurrentB;
    private String maxCurrentB;

    private String minCurrentC;
    private String maxCurrentC;

    private long dataTime;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public long getDataTime() {
        return dataTime;
    }

    public void setDataTime(long dataTime) {
        this.dataTime = dataTime;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getMinPressure() {
        return minPressure;
    }

    public void setMinPressure(String minPressure) {
        this.minPressure = minPressure;
    }

    public String getMaxPressure() {
        return maxPressure;
    }

    public void setMaxPressure(String maxPressure) {
        this.maxPressure = maxPressure;
    }

    public String getTotalPower() {
        return totalPower;
    }

    public void setTotalPower(String totalPower) {
        this.totalPower = totalPower;
    }

    public String getMinCurrentA() {
        return minCurrentA;
    }

    public void setMinCurrentA(String minCurrentA) {
        this.minCurrentA = minCurrentA;
    }

    public String getMaxCurrentA() {
        return maxCurrentA;
    }

    public void setMaxCurrentA(String maxCurrentA) {
        this.maxCurrentA = maxCurrentA;
    }

    public String getMinCurrentB() {
        return minCurrentB;
    }

    public void setMinCurrentB(String minCurrentB) {
        this.minCurrentB = minCurrentB;
    }

    public String getMaxCurrentB() {
        return maxCurrentB;
    }

    public void setMaxCurrentB(String maxCurrentB) {
        this.maxCurrentB = maxCurrentB;
    }

    public String getMinCurrentC() {
        return minCurrentC;
    }

    public void setMinCurrentC(String minCurrentC) {
        this.minCurrentC = minCurrentC;
    }

    public String getMaxCurrentC() {
        return maxCurrentC;
    }

    public void setMaxCurrentC(String maxCurrentC) {
        this.maxCurrentC = maxCurrentC;
    }

    public String getWaterDosage() {
        return waterDosage;
    }

    public void setWaterDosage(String waterDosage) {
        this.waterDosage = waterDosage;
    }
}
