package ingeniousthings.sigfox.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Rating {

    public final String id;
    public final String contractId;
    public final long fromTime;
    public final long toTime;
    public final int tokensInUse;
    public final int maxTokens;
    public final List<QuarterlyConsumption> consumptionData;

    public Rating(String id, String contractId, long fromTime, long toTime, int tokensInUse, int maxTokens, List<QuarterlyConsumption> consumptionData) {
        this.id = id;
        this.contractId = contractId;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.tokensInUse = tokensInUse;
        this.maxTokens = maxTokens;
        this.consumptionData = consumptionData;
    }

    public enum State {
        @SerializedName("0")
        IN_PROGRESS(0),
        @SerializedName("1")
        FINAL(1),
        @SerializedName("2")
        BOTH(2);

        public int value;

        State(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }
}
