package ingeniousthings.sigfox.model;

public class CoverageMargins {

    public final double redundancyLevel0;
    public final double redundancyLevel1;
    public final double redundancyLevel2;

    public CoverageMargins(double redundancyLevel0, double redundancyLevel1, double redundancyLevel2) {
        this.redundancyLevel0 = redundancyLevel0;
        this.redundancyLevel1 = redundancyLevel1;
        this.redundancyLevel2 = redundancyLevel2;
    }

    @Override
    public String toString() {
        return "CoverageMargins{" +
            "redundancyLevel0=" + redundancyLevel0 +
            ", redundancyLevel1=" + redundancyLevel1 +
            ", redundancyLevel2=" + redundancyLevel2 +
            '}';
    }
}
