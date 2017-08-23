package ingeniousthings.sigfox.client;

import ingeniousthings.sigfox.model.DetailedRating;
import ingeniousthings.sigfox.model.Page;
import ingeniousthings.sigfox.model.Rating;
import ingeniousthings.sigfox.model.Rating.State;
import org.junit.Test;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class RatingClientTest extends SigfoxClientTestBase {

    @Test
    public void shouldGetRatingsInformation() throws Exception {
        sigfoxBackend.stubFor(get(urlPathMatching("/api/ratings")).
            willReturn(ok().withBodyFile("rating/ratings-information.json"))
        );
        Page<Rating> page = client.ratings.getRatings().execute().body();
        assertThat(page.getSize()).isEqualTo(2);

        List<Rating> ratings = page.getContent();
        assertThat(ratings.get(0).id).isEqualTo("52a403dae4b026a9f3dbfde3");
        assertThat(ratings.get(0).consumptionData.get(0).quarterDate).isEqualTo(1396303200000L);

    }

    @Test
    public void shouldGetRatingsInformationWithCriteria() throws Exception {
        sigfoxBackend.stubFor(get(urlPathMatching("/api/ratings"))
            .withQueryParam("groupId", matching("AAA,BBB"))
            .withQueryParam("fromTime", matching("2017-01-01"))
            .withQueryParam("toTime", matching("2018-01-01"))
            .withQueryParam("state", matching("2"))
            .willReturn(ok().withBodyFile("rating/ratings-information.json"))
        );
        Page<Rating> page = client.ratings.getRatings(
            null,
            null,
            String.join(",", "AAA", "BBB"),
            null,
            "2017-01-01",
            "2018-01-01",
            State.BOTH,
            null
        ).execute().body();
        assertThat(page.getSize()).isEqualTo(2);

        List<Rating> ratings = page.getContent();
        assertThat(ratings.get(0).id).isEqualTo("52a403dae4b026a9f3dbfde3");
        assertThat(ratings.get(0).consumptionData.get(0).quarterDate).isEqualTo(1396303200000L);

    }

    @Test
    public void shouldGetDetailedRatingList() throws Exception {
        sigfoxBackend.stubFor(get(urlPathMatching("/api/ratings/detailed")).
            willReturn(ok().withBodyFile("rating/detailed-rating-information.json"))
        );
        Page<DetailedRating> page = client.ratings.getDetailedRatings().execute().body();
        assertThat(page.getSize()).isEqualTo(2);

        List<DetailedRating> detailedRatings = page.getContent();
        assertThat(detailedRatings.get(0).general.billingMode).isEqualTo(DetailedRating.BillingMode.MORE_THAN_50000_UNITS);
        assertThat(detailedRatings.get(0).details.get(0).date).isEqualTo(1388530800000L);

    }


}
