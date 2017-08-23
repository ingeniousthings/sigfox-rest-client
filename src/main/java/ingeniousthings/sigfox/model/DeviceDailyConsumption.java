package ingeniousthings.sigfox.model;

public class DeviceDailyConsumption {

    public final int frameCount;
    public final int downlinkFrameCount;
    public final int roamingFrameCount;
    public final int roamingDownlinkFrameCount;

    public DeviceDailyConsumption(int frameCount, int downlinkFrameCount, int roamingFrameCount, int roamingDownlinkFrameCount) {
        this.frameCount = frameCount;
        this.downlinkFrameCount = downlinkFrameCount;
        this.roamingFrameCount = roamingFrameCount;
        this.roamingDownlinkFrameCount = roamingDownlinkFrameCount;
    }

    @Override
    public String toString() {
        return "DeviceDailyConsumption{" +
            "frameCount=" + frameCount +
            ", downlinkFrameCount=" + downlinkFrameCount +
            ", roamingFrameCount=" + roamingFrameCount +
            ", roamingDownlinkFrameCount=" + roamingDownlinkFrameCount +
            '}';
    }
}
