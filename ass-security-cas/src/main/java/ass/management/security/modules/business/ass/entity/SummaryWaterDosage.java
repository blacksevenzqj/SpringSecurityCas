package ass.management.security.modules.business.ass.entity;


/**
 * 汇总用水量
 */
public class SummaryWaterDosage {
    private long id;

    private String data;

    private String expectData;

    private long dataTime;

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

    public long getDataTime() {
        return dataTime;
    }

    public void setDataTime(long dataTime) {
        this.dataTime = dataTime;
    }

    public String getExpectData() {
        return expectData;
    }

    public void setExpectData(String expectData) {
        this.expectData = expectData;
    }
}
