package ingeniousthings.sigfox.model;

public class MessageMetric {

    public final long lastDay;
    public final long lastWeek;
    public final long lastMonth;

    public MessageMetric(long lastDay, long lastWeek, long lastMonth) {
        this.lastDay = lastDay;
        this.lastWeek = lastWeek;
        this.lastMonth = lastMonth;
    }

    @Override
    public String toString() {
        return "MessageMetric{" +
            "lastDay=" + lastDay +
            ", lastWeek=" + lastWeek +
            ", lastMonth=" + lastMonth +
            '}';
    }
}
