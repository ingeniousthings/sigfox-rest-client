package ingeniousthings.sigfox.model;

import java.util.List;

public class DeviceYearlyConsumption {

    public final String id;
    public final List<DeviceDailyConsumption> consumptions;

    public DeviceYearlyConsumption(String id, List<DeviceDailyConsumption> consumptions) {
        this.id = id;
        this.consumptions = consumptions;
    }

    @Override
    public String toString() {
        return "DeviceYearlyConsumption{" +
            "id='" + id + '\'' +
            ", consumptions=" + consumptions +
            '}';
    }
}
