package ingeniousthings.sigfox.model;

public class QuarterlyConsumption {

    public final long quarterDate;
    public final int activationDays;
    public final int sentMessages;
    public final int sentDownlinkMessages;

    public QuarterlyConsumption(long quarterDate, int activationDays, int sentMessages, int sentDownlinkMessages) {
        this.quarterDate = quarterDate;
        this.activationDays = activationDays;
        this.sentMessages = sentMessages;
        this.sentDownlinkMessages = sentDownlinkMessages;
    }

    @Override
    public String toString() {
        return "QuarterlyConsumption{" +
            "quarterDate=" + quarterDate +
            ", activationDays=" + activationDays +
            ", sentMessages=" + sentMessages +
            ", sentDownlinkMessages=" + sentDownlinkMessages +
            '}';
    }
}
