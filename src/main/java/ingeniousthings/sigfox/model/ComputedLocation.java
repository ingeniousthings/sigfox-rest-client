package ingeniousthings.sigfox.model;

public class ComputedLocation {
    public final double lat;
    public final double lng;
    public final int radius;

    public ComputedLocation(double lat, double lng, int radius) {
        this.lat = lat;
        this.lng = lng;
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "ComputedLocation{" +
            "lat=" + lat +
            ", lng=" + lng +
            ", radius=" + radius +
            '}';
    }
}
