package ass.management.security.modules.business.ass.entity;


/**
 * 用户
 */
public class UserWater {
    private long id;

    private long userId;

    private String card;

    private String meterId;

    private String waterNum;

    private long dataTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getMeterId() {
        return meterId;
    }

    public void setMeterId(String meterId) {
        this.meterId = meterId;
    }

    public String getWaterNum() {
        return waterNum;
    }

    public void setWaterNum(String waterNum) {
        this.waterNum = waterNum;
    }

    public long getDataTime() {
        return dataTime;
    }

    public void setDataTime(long dataTime) {
        this.dataTime = dataTime;
    }
}
