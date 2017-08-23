package ingeniousthings.sigfox.model;

public class MessageLocation {

    public final long time;
    public final boolean valid;
    public final double lat;
    public final double lng;
    public final int radius;

    public MessageLocation(long time, boolean valid, double lat, double lng, int radius) {
        this.time = time;
        this.valid = valid;
        this.lat = lat;
        this.lng = lng;
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "MessageLocation{" +
            "time=" + time +
            ", valid=" + valid +
            ", lat=" + lat +
            ", lng=" + lng +
            ", radius=" + radius +
            '}';
    }
}
