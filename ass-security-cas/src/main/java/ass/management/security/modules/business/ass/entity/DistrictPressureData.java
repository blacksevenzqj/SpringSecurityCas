package ass.management.security.modules.business.ass.entity;


/**
 * 灌区压力数据
 */
public class DistrictPressureData {
    private long id;

    private long districtId;

    private String districtName;

    private String minPressure;

    private String maxPressure;

    private long dataTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(long districtId) {
        this.districtId = districtId;
    }

    public long getDataTime() {
        return dataTime;
    }

    public void setDataTime(long dataTime) {
        this.dataTime = dataTime;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
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
}
