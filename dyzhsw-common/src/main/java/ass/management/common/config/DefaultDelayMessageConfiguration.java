package ass.management.common.config;

import lombok.Data;

@Data
public abstract class DefaultDelayMessageConfiguration {

    private long tickDurationSecond;

    private int ticksPerWheelSecond;

    private double timeOutSecond;

    private double timeOutHour;

}
