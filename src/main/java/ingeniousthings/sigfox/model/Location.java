package ingeniousthings.sigfox.model;

public class Location {

    public final Double lat;
    public final Double lng;

    public Location(Double lat, Double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "Location{" +
            "lat=" + lat +
            ", lng=" + lng +
            '}';
    }
}
