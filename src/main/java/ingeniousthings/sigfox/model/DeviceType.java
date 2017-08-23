package ingeniousthings.sigfox.model;

import com.google.gson.annotations.SerializedName;

public class DeviceType {

    public final String id;
    public final String name;
    public final String group;
    public final String description;
    public final int keepAlive;
    public final PayloadType payloadType;
    public final String contract;

    public DeviceType(String id, String name, String group, String description, int keepAlive, PayloadType payloadType, String contract) {
        this.id = id;
        this.name = name;
        this.group = group;
        this.description = description;
        this.keepAlive = keepAlive;
        this.payloadType = payloadType;
        this.contract = contract;
    }

    public enum PayloadType {
        @SerializedName("None")
        NONE("None"),
        @SerializedName("String")
        STRING("String"),
        @SerializedName("CustomRole")
        CUSTOM("CustomRole"),
        @SerializedName("Geolocation")
        GEOLOCATION("Geolocation");

        public final String value;

        PayloadType(String value) {
            this.value = value;
        }
    }

    @Override
    public String toString() {
        return "DeviceType{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", group='" + group + '\'' +
            ", description='" + description + '\'' +
            ", keepAlive=" + keepAlive +
            ", payloadType=" + payloadType +
            ", contract='" + contract + '\'' +
            '}';
    }
}
