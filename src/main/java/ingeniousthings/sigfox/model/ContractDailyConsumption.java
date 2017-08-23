package ingeniousthings.sigfox.model;

import java.util.Map;

public class ContractDailyConsumption {

    public final int deviceCount;
    public final int frameCount;
    public final int activationCount;
    public final int tokenRevocations;
    public final int revokedActivations;
    public final int downlinkFrameCount;
    public final int roamingDeviceCount;
    public final int roamingFrameCount;
    public final int roamingDownlinkFrameCount;
    public final Map<String, RoamingDetailedFrameCount> roamingDetailedFrameCount;
    public final int framesByRoamingDevicesCount;
    public final int downlinkFramesByRoamingDevicesCount;

    public ContractDailyConsumption(int deviceCount, int frameCount, int activationCount, int tokenRevocations, int revokedActivations, int downlinkFrameCount, int roamingDeviceCount, int roamingFrameCount, int roamingDownlinkFrameCount, Map<String, RoamingDetailedFrameCount> roamingDetailedFrameCount, int framesByRoamingDevicesCount, int downlinkFramesByRoamingDevicesCount) {
        this.deviceCount = deviceCount;
        this.frameCount = frameCount;
        this.activationCount = activationCount;
        this.tokenRevocations = tokenRevocations;
        this.revokedActivations = revokedActivations;
        this.downlinkFrameCount = downlinkFrameCount;
        this.roamingDeviceCount = roamingDeviceCount;
        this.roamingFrameCount = roamingFrameCount;
        this.roamingDownlinkFrameCount = roamingDownlinkFrameCount;
        this.roamingDetailedFrameCount = roamingDetailedFrameCount;
        this.framesByRoamingDevicesCount = framesByRoamingDevicesCount;
        this.downlinkFramesByRoamingDevicesCount = downlinkFramesByRoamingDevicesCount;
    }

    @Override
    public String toString() {
        return "ContractDailyConsumption{" +
            "deviceCount=" + deviceCount +
            ", frameCount=" + frameCount +
            ", activationCount=" + activationCount +
            ", tokenRevocations=" + tokenRevocations +
            ", revokedActivations=" + revokedActivations +
            ", downlinkFrameCount=" + downlinkFrameCount +
            ", roamingDeviceCount=" + roamingDeviceCount +
            ", roamingFrameCount=" + roamingFrameCount +
            ", roamingDownlinkFrameCount=" + roamingDownlinkFrameCount +
            ", roamingDetailedFrameCount=" + roamingDetailedFrameCount +
            ", framesByRoamingDevicesCount=" + framesByRoamingDevicesCount +
            ", downlinkFramesByRoamingDevicesCount=" + downlinkFramesByRoamingDevicesCount +
            '}';
    }

    public static class RoamingDetailedFrameCount {

        public final int roamingFrameCount;
        public final int roamingDownlinkFrameCount;

        public RoamingDetailedFrameCount(int roamingFrameCount, int roamingDownlinkFrameCount) {
            this.roamingFrameCount = roamingFrameCount;
            this.roamingDownlinkFrameCount = roamingDownlinkFrameCount;
        }

        @Override
        public String toString() {
            return "RoamingDetailedFrameCount{" +
                "roamingFrameCount=" + roamingFrameCount +
                ", roamingDownlinkFrameCount=" + roamingDownlinkFrameCount +
                '}';
        }
    }
}
