package ingeniousthings.sigfox.model;

import com.google.gson.annotations.SerializedName;

public class Message {

    public final String device;
    public final long time;
    public final String data;
    public final double snr;
    public final ComputedLocation computedLocation;
    public final LinkQuality linkQuality;
    public final DownlinkAnswerStatus downlinkAnswerStatus;

    public Message(String device, long time, String data, double snr, ComputedLocation computedLocation, LinkQuality linkQuality, DownlinkAnswerStatus downlinkAnswerStatus) {
        this.device = device;
        this.time = time;
        this.data = data;
        this.snr = snr;
        this.computedLocation = computedLocation;
        this.linkQuality = linkQuality;
        this.downlinkAnswerStatus = downlinkAnswerStatus;
    }

    public enum LinkQuality {
        @SerializedName("LIMIT")
        LIMIT("LIMIT"),
        @SerializedName("AVERAGE")
        AVERAGE("AVERAGE"),
        @SerializedName("GOOD")
        GOOD("GOOD"),
        @SerializedName("EXCELLENT")
        EXCELLENT("EXCELLENT");

        public final String value;

        LinkQuality(String value) {
            this.value = value;
        }
    }


    public class DownlinkAnswerStatus {
        public final String data;

        public DownlinkAnswerStatus(String data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "DownlinkAnswerStatus{" +
                "data='" + data + '\'' +
                '}';
        }
    }

    @Override
    public String toString() {
        return "Message{" +
            "device='" + device + '\'' +
            ", time=" + time +
            ", data='" + data + '\'' +
            ", snr=" + snr +
            ", computedLocation=" + computedLocation +
            ", linkQuality=" + linkQuality +
            ", downlinkAnswerStatus=" + downlinkAnswerStatus +
            '}';
    }
}
