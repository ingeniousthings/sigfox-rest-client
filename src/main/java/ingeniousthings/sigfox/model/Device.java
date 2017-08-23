package ingeniousthings.sigfox.model;

import com.google.gson.annotations.SerializedName;

public class Device {

    public final String id;
    public final String name;
    public final String type;
    public final long last;
    public final double averageRssi;
    public final double averageSnr;
    public final State state;
    public final double lat;
    public final double lng;
    public final ComputedLocation computedLocation;
    public final long activationTime;
    public final String pac;
    public final TokenType tokenType;
    public final String contractId;
    public final long freeMessage;
    public final long tokenEnd;
    public final boolean preventRenewal;

    public Device(String id, String name, String type, long last, double averageRssi, double averageSnr, State state, double lat, double lng, ComputedLocation computedLocation, long activationTime, String pac, TokenType tokenType, String contractId, long freeMessage, long tokenEnd, boolean preventRenewal) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.last = last;
        this.averageRssi = averageRssi;
        this.averageSnr = averageSnr;
        this.state = state;
        this.lat = lat;
        this.lng = lng;
        this.computedLocation = computedLocation;
        this.activationTime = activationTime;
        this.pac = pac;
        this.tokenType = tokenType;
        this.contractId = contractId;
        this.freeMessage = freeMessage;
        this.tokenEnd = tokenEnd;
        this.preventRenewal = preventRenewal;
    }

    public enum State {
        @SerializedName("0")
        OK(0, ""),
        @SerializedName("1")
        DEAD(1, "Communication timeout"),
        @SerializedName("2")
        OFF_CONTRACT(2, "Communication forbidden"),
        @SerializedName("3")
        DISABLED(3, "The device is about to be transfered"),
        @SerializedName("4")
        WARN(4, "Network issues"),
        @SerializedName("5")
        DELETED(5, "The device is about to be deleted");

        public final int value;
        public final String message;

        State(int value, String message) {
            this.value = value;
            this.message = message;
        }
    }


    public enum TokenType {
        @SerializedName("CONTRACT")
        CONTRACT("CONTRACT"),
        @SerializedName("FREE")
        FREE("FREE");

        public final String value;

        TokenType(String value) {
            this.value = value;
        }
    }

    @Override
    public String toString() {
        return "Device{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", type='" + type + '\'' +
            ", last=" + last +
            ", averageRssi=" + averageRssi +
            ", averageSnr=" + averageSnr +
            ", state=" + state +
            ", lat=" + lat +
            ", lng=" + lng +
            ", computedLocation=" + computedLocation +
            ", activationTime=" + activationTime +
            ", pac='" + pac + '\'' +
            ", tokenType=" + tokenType +
            ", contractId='" + contractId + '\'' +
            ", freeMessage=" + freeMessage +
            ", tokenEnd=" + tokenEnd +
            ", preventRenewal=" + preventRenewal +
            '}';
    }
}
