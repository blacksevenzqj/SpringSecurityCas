package ass.management.security.modules.business.ass.entity;


/**
 * 站点信息
 */
public class SiteInfo {
    private long id;

    private String name;

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

    public int getDeviceCount() {
        return deviceCount;
    }

    public void setDeviceCount(int deviceCount) {
        this.deviceCount = deviceCount;
    }

}
