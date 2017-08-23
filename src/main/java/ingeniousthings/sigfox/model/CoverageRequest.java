package ingeniousthings.sigfox.model;

import java.util.Collections;
import java.util.List;

public class CoverageRequest {

    public final List<Location> locations;

    public final Integer radius;

    public CoverageRequest(List<Location> locations, Integer radius) {
        this.locations = Collections.unmodifiableList(locations);
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "CoverageRequest{" +
            "locations=" + locations +
            ", radius=" + radius +
            '}';
    }
}
