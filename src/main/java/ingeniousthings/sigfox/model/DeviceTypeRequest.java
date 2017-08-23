package ingeniousthings.sigfox.model;

import com.google.gson.annotations.SerializedName;

public class DeviceTypeRequest {

    public final String id;
    public final String name;
    public final String description;
    public final int keepAlive;
    public final String alertEmail;
    public final DeviceType.PayloadType payloadType;
    public final String group;
    public final DownlinkMode downlinkMode;
    public final String downlinkDataString;
    public final String contractId;

    public DeviceTypeRequest(String id, String name, String description, int keepAlive, String alertEmail, DeviceType.PayloadType payloadType, String group, DownlinkMode downlinkMode, String downlinkDataString, String contractId) {
        if (downlinkMode == DownlinkMode.DIRECT_DOWNLINK) {
            require8HexadecimalBytesString(downlinkDataString);
        }
        this.id = id;
        this.name = name;
        this.description = description;
        this.keepAlive = keepAlive;
        this.alertEmail = alertEmail;
        this.payloadType = payloadType;
        this.group = group;
        this.downlinkMode = downlinkMode;
        this.downlinkDataString = downlinkDataString;
        this.contractId = contractId;
    }

    public DeviceTypeRequest(String name, String description, int keepAlive, String alertEmail, DeviceType.PayloadType payloadType, String group, DownlinkMode downlinkMode, String downlinkDataString, String contractId) {
        this(
            null,
            name,
            description,
            keepAlive,
            alertEmail,
            payloadType,
            group,
            downlinkMode,
            downlinkDataString,
            contractId
        );
    }

    private static String require8HexadecimalBytesString(String text) {
        if (!(text.length() == 16 && text.matches("[0-9a-fA-F]+"))) {
            throw new IllegalArgumentException("Since downlinkMode is a direct downlink (0), downlinkDataString must be an 8 hexadecimal bytes string");
        }
        return text;
    }

    public enum DownlinkMode {
        @SerializedName("0")
        DIRECT_DOWNLINK(0),
        @SerializedName("1")
        CALLBACK_DOWNLINK(1);

        public final int value;

        DownlinkMode(int value) {
            this.value = value;
        }
    }

    public static class Builder {

        private String id;
        private String name;
        private String description;
        private int keepAlive;
        private String alertEmail;
        private DeviceType.PayloadType payloadType;
        private String group;
        private DownlinkMode downlinkMode;
        private String downlinkDataString;
        private String contractId;

        public DeviceTypeRequest build() {
            return new DeviceTypeRequest(
                id,
                name,
                description,
                keepAlive,
                alertEmail,
                payloadType,
                group,
                downlinkMode,
                downlinkDataString,
                contractId
            );
        }

        public Builder from(DeviceType deviceType) {
            this.id = deviceType.id;
            this.name = deviceType.name;
            this.description = deviceType.description;
            this.keepAlive = deviceType.keepAlive;
            this.payloadType = deviceType.payloadType;
            this.contractId = deviceType.contract;
            return this;
        }

        public String id() {
            return id;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public String name() {
            return name;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public String description() {
            return description;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public int keepAlive() {
            return keepAlive;
        }

        public Builder keepAlive(int keepAlive) {
            this.keepAlive = keepAlive;
            return this;
        }

        public String alertEmail() {
            return alertEmail;
        }

        public Builder alertEmail(String alertEmail) {
            this.alertEmail = alertEmail;
            return this;
        }

        public DeviceType.PayloadType payloadType() {
            return payloadType;
        }

        public Builder payloadType(DeviceType.PayloadType payloadType) {
            this.payloadType = payloadType;
            return this;
        }

        public String group() {
            return group;
        }

        public Builder group(String group) {
            this.group = group;
            return this;
        }

        public DownlinkMode downlinkMode() {
            return downlinkMode;
        }

        public Builder downlinkMode(DownlinkMode downlinkMode) {
            this.downlinkMode = downlinkMode;
            return this;
        }

        public String downlinkDataString() {
            return downlinkDataString;
        }

        public Builder downlinkDataString(String downlinkDataString) {
            this.downlinkDataString = downlinkDataString;
            return this;
        }

        public String contractId() {
            return contractId;
        }

        public Builder contractId(String contractId) {
            this.contractId = contractId;
            return this;
        }
    }


}
