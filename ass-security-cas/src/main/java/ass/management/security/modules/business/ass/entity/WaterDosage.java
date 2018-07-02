package ass.management.security.modules.business.ass.entity;


/**
 * 用水量
 */
public class WaterDosage {
    private long id;

    private String data;

    private String otherData;

    private long dataTime;

    private long otherDataTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getOtherData() {
        return otherData;
    }

    public void setOtherData(String otherData) {
        this.otherData = otherData;
    }

    public long getDataTime() {
        return dataTime;
    }

    public void setDataTime(long dataTime) {
        this.dataTime = dataTime;
    }

    public long getOtherDataTime() {
        return otherDataTime;
    }

    public void setOtherDataTime(long otherDataTime) {
        this.otherDataTime = otherDataTime;
    }
}
