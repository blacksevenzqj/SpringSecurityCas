package ass.management.security.modules.business.ass.entity;


public class DeviceStatusData {
    private long id;

    private long deviceId;

    private String deviceName;

    private String waterDosage;

    private String pressure;

    private String totalPower;

    private String currentA;

    private String currentB;

    private String currentC;

    private String status;

    private long dataTime;

    private Alarm alarm;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDataTime() {
        return dataTime;
    }

    public void setDataTime(long dataTime) {
        this.dataTime = dataTime;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getWaterDosage() {
        return waterDosage;
    }

    public void setWaterDosage(String waterDosage) {
        this.waterDosage = waterDosage;
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

    public Alarm getAlarm() {
        return alarm;
    }

    public void setAlarm(Alarm alarm) {
        this.alarm = alarm;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
