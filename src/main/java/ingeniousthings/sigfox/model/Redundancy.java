package ingeniousthings.sigfox.model;

import com.google.gson.annotations.SerializedName;

public class Redundancy {

    public final BaseStationRedundancy baseStationRedundancy;

    public Redundancy(BaseStationRedundancy baseStationRedundancy) {
        this.baseStationRedundancy = baseStationRedundancy;
    }

    @Override
    public String toString() {
        return "Redundancy{" +
            "baseStationRedundancy=" + baseStationRedundancy +
            '}';
    }

    public enum BaseStationRedundancy {
        @SerializedName("0")
        NONE(0),
        @SerializedName("1")
        ONE_BASE_STATION(1),
        @SerializedName("2")
        TWO_BASE_STATIONS(2),
        @SerializedName("3")
        THREE_OR_MORE_BASE_STATIONS(3);

        public final int value;

        BaseStationRedundancy(int value) {
            this.value = value;
        }

        public static BaseStationRedundancy valueOf(int value) {
            for (BaseStationRedundancy baseStationRedundancy : BaseStationRedundancy.values()) {
                if (baseStationRedundancy.value == value) {
                    return baseStationRedundancy;
                }
            }
            return null;
        }
    }

    public enum Mode {
        @SerializedName("INDOOR")
        INDOOR("INDOOR"),
        @SerializedName("OUTDOOR")
        OUTDOOR("OUTDOOR"),
        @SerializedName("UNDERGROUND")
        UNDERGROUND("UNDERGROUND");

        public final String value;

        Mode(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

}
