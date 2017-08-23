package ingeniousthings.sigfox.client;

import ingeniousthings.sigfox.model.*;
import ingeniousthings.sigfox.model.Redundancy.BaseStationRedundancy;
import org.junit.Test;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class CoverageClientTest extends SigfoxClientTestBase {

    @Test
    public void shouldGetRedundancy() throws Exception {
        sigfoxBackend.stubFor(get(urlPathMatching("/api/coverages/redundancy"))
            .withQueryParam("lat", equalTo("1.0"))
            .withQueryParam("lng", equalTo("2.0"))
            .withQueryParam("mode", equalTo("INDOOR"))
            .willReturn(ok().withBodyFile("coverage/redundancy.json"))
        );
        Redundancy redundancy = client.coverages.getRedundancy(1.0, 2.0, Redundancy.Mode.INDOOR).execute().body();
        assertThat(redundancy.baseStationRedundancy).isEqualTo(BaseStationRedundancy.THREE_OR_MORE_BASE_STATIONS);
    }

    @Test
    public void shouldGetMargins() throws Exception {
        sigfoxBackend.stubFor(get(urlPathMatching("/api/coverages/global/predictions"))
            .withQueryParam("lat", equalTo("1.0"))
            .withQueryParam("lng", equalTo("2.0"))
            .willReturn(ok().withBodyFile("coverage/global-coverage-single-point.json"))
        );
        CoverageMargins margins = client.coverages.getGlobalCoverage(1.0, 2.0).execute().body();
        assertThat(margins.redundancyLevel0).isEqualTo(42.12);
        assertThat(margins.redundancyLevel1).isEqualTo(27.49);
        assertThat(margins.redundancyLevel2).isEqualTo(5.87);
    }

    @Test
    public void shouldGetMarginsWithCoverageRequest() throws Exception {
        sigfoxBackend.stubFor(post(urlPathMatching("/api/coverages/global/predictions"))
            .withRequestBody(matchingJsonPath("$.locations[0][?(@.lat == 43.5)]"))
            .withRequestBody(matchingJsonPath("$.locations[1][?(@.lng == 1.5)]"))
            .withRequestBody(matching("(?!radius).*")) //radius should be absent since it's null
            .willReturn(ok().withBodyFile("coverage/global-coverage-multiple-point.json"))
        );
        CoverageRequest request = new CoverageRequest(
            asList(new Location(43.5, 1.5), new Location(43.4, 1.5)),
            null);
        List<CoverageMarginsByLocation> margins = client.coverages.getGlobalCoverageByLocations(request).execute().body();
        assertThat(margins.size()).isEqualTo(2);
        assertThat(margins.get(0).margins.redundancyLevel0).isEqualTo(42);
        assertThat(margins.get(1).lat).isEqualTo(43.4);
    }
}
