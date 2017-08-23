package ingeniousthings.sigfox.model;

import java.util.List;

public class ContractYearlyConsumption {

    public final String id;
    public final List<ContractDailyConsumption> consumptions;

    public ContractYearlyConsumption(String id, List<ContractDailyConsumption> consumptions) {
        this.id = id;
        this.consumptions = consumptions;
    }

    @Override
    public String toString() {
        return "ContractYearlyConsumption{" +
            "id='" + id + '\'' +
            ", consumptions=" + consumptions +
            '}';
    }
}
