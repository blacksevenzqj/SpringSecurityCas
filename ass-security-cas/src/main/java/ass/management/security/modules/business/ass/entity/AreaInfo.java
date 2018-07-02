package ass.management.security.modules.business.ass.entity;


/**
 * 区域
 * Created by henery on 2017/11/26.
 */
public class AreaInfo {
    private long id;

    private String name;

    private int districtCount;

    private int siteCount;

    private int deviceCount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDistrictCount() {
        return districtCount;
    }

    public void setDistrictCount(int districtCount) {
        this.districtCount = districtCount;
    }

    public int getSiteCount() {
        return siteCount;
    }

    public void setSiteCount(int siteCount) {
        this.siteCount = siteCount;
    }

    public int getDeviceCount() {
        return deviceCount;
    }

    public void setDeviceCount(int deviceCount) {
        this.deviceCount = deviceCount;
    }
}
