package ingeniousthings.sigfox.model;

public class CoverageMarginsByLocation {

    public final double lat;
    public final double lng;
    public final CoverageMargins margins;

    public CoverageMarginsByLocation(double lat, double lng, CoverageMargins margins) {
        this.lat = lat;
        this.lng = lng;
        this.margins = margins;
    }

    @Override
    public String toString() {
        return "CoverageMarginsByLocation{" +
            "lat=" + lat +
            ", lng=" + lng +
            ", margins=" + margins +
            '}';
    }
}
