package ass.management.security.modules.business.ass.entity;


/**
 * 设备信息
 * Created by henery on 2017/11/25.
 */
public class DeviceInfo {
    private long id;

    private String name;

    private String desc;

    private String type;

    private String vendor;

    private float voltage;

    private float current;

    private float head;

    private float power;

    private float maxOutputPressure;

    private float suction;

    private float capacity;

    private float efficiency;

    private float outletSize;

    private float inletSize;

    private float speed;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public float getVoltage() {
        return voltage;
    }

    public void setVoltage(float voltage) {
        this.voltage = voltage;
    }

    public float getCurrent() {
        return current;
    }

    public void setCurrent(float current) {
        this.current = current;
    }

    public float getHead() {
        return head;
    }

    public void setHead(float head) {
        this.head = head;
    }

    public float getPower() {
        return power;
    }

    public void setPower(float power) {
        this.power = power;
    }

    public float getMaxOutputPressure() {
        return maxOutputPressure;
    }

    public void setMaxOutputPressure(float maxOutputPressure) {
        this.maxOutputPressure = maxOutputPressure;
    }

    public float getSuction() {
        return suction;
    }

    public void setSuction(float suction) {
        this.suction = suction;
    }

    public float getCapacity() {
        return capacity;
    }

    public void setCapacity(float capacity) {
        this.capacity = capacity;
    }

    public float getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(float efficiency) {
        this.efficiency = efficiency;
    }

    public float getOutletSize() {
        return outletSize;
    }

    public void setOutletSize(float outletSize) {
        this.outletSize = outletSize;
    }

    public float getInletSize() {
        return inletSize;
    }

    public void setInletSize(float inletSize) {
        this.inletSize = inletSize;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
