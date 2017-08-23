package ingeniousthings.sigfox.model;

import com.google.gson.annotations.SerializedName;

public abstract class Callback {

    public final String id;
    public final Channel channel;
    public final CallbackType callbackType;
    public final CallbackSubtype callbackSubtype;
    public final String payloadConfig;
    public final boolean enabled;
    public final boolean sendDuplicate;
    public final boolean dead;

    public Callback(String id, Channel channel, CallbackType callbackType, CallbackSubtype callbackSubtype, String payloadConfig, boolean enabled, boolean sendDuplicate, boolean dead) {
        this.id = id;
        this.channel = channel;
        this.callbackType = callbackType;
        this.callbackSubtype = callbackSubtype;
        this.payloadConfig = payloadConfig;
        this.enabled = enabled;
        this.sendDuplicate = sendDuplicate;
        this.dead = dead;
    }

    public enum Channel {
        @SerializedName("URL")
        URL("URL"),
        @SerializedName("BATCH_URL")
        BATCH_URL("BATCH_URL"),
        @SerializedName("EMAIL")
        EMAIL("EMAIL");

        public final String value;

        Channel(String value) {
            this.value = value;
        }

    }

    public enum CallbackType {
        @SerializedName("0")
        DATA(0),
        @SerializedName("1")
        SERVICE(1),
        @SerializedName("2")
        ERROR(2);

        public final int value;

        CallbackType(int value) {
            this.value = value;
        }
    }

    public enum CallbackSubtype {
        @SerializedName("0")
        STATUS(0),
        @SerializedName("2")
        UPLINK(2),
        @SerializedName("3")
        BIDIR(3),
        @SerializedName("4")
        ACKNOWLEDGE(4);

        public final int value;

        CallbackSubtype(int value) {
            this.value = value;
        }
    }

}
