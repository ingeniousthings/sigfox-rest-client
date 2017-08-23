package ingeniousthings.sigfox.client;

import ingeniousthings.sigfox.model.*;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ContractClientTest extends SigfoxClientTestBase {

    public static final String DEFAULT_CONTRACT_ID = "AAA";
    public static final String DEFAULT_DEVICE_TYPE_ID = "BBB";

    @Test
    public void shouldGetBSSOrdersByDeviceType() throws Exception {
        sigfoxBackend.stubFor(get(urlPathMatching("/api/contracts")).
            withQueryParam("deviceTypeId", matching("[a-zA-Z0-9]+"))
            .willReturn(ok().withBodyFile("contract/bss-orders-list.json"))
        );
        List<Contract> contracts = client.contracts.getContractsByDeviceType(DEFAULT_DEVICE_TYPE_ID).execute().body();
        assertThat(contracts.get(0).name).isEqualTo("BSS Order sample");
    }

    @Test
    public void shouldGetAllBSSOrders() throws Exception {
        sigfoxBackend.stubFor(get(urlPathMatching("/api/contracts"))
            .willReturn(ok().withBodyFile("contract/bss-orders-list.json"))
        );
        List<Contract> contracts = client.contracts.getContracts().execute().body();
        assertThat(contracts.get(0).name).isEqualTo("BSS Order sample");
    }

    @Test
    public void shouldGetBSSOrderInformation() throws Exception {
        sigfoxBackend.stubFor(get(urlPathMatching("/api/contracts/[a-zA-Z0-9]+"))
            .willReturn(ok().withBodyFile("contract/bss-order-information.json"))
        );
        Contract contract = client.contracts.getContract(DEFAULT_CONTRACT_ID).execute().body();
        assertThat(contract.name).isEqualTo("BSS Order sample");
    }

    @Test
    public void shouldGetRatingInformationByDateString() throws Exception {
        sigfoxBackend.stubFor(get(urlPathMatching("/api/contracts/[a-zA-Z0-9]+/rating"))
            .withQueryParam("date", matching("[0-9]{4}-[0-9]{2}-[0-9]{2}"))
            .willReturn(ok().withBodyFile("contract/rating-information.json"))
        );
        Rating rating = client.contracts.getRatingByContract(DEFAULT_CONTRACT_ID, "2014-01-20").execute().body();
        assertThat(rating.consumptionData.get(0).quarterDate).isEqualTo(1396303200000L);
    }

    @Test
    public void shouldGetRatingInformationByDate() throws Exception {
        sigfoxBackend.stubFor(get(urlPathMatching("/api/contracts/[a-zA-Z0-9]+/rating"))
            .withQueryParam("date", matching("[0-9]{4}-[0-9]{2}-[0-9]{2}"))
            .willReturn(ok().withBodyFile("contract/rating-information.json"))
        );
        Rating rating = client.contracts.getRatingByContract(DEFAULT_CONTRACT_ID, new Date()).execute().body();
        assertThat(rating.consumptionData.get(0).quarterDate).isEqualTo(1396303200000L);
    }

    @Test
    public void shouldGetDetailedRatingInformation() throws Exception {
        sigfoxBackend.stubFor(get(urlPathMatching("/api/contracts/[a-zA-Z0-9]+/rating/detailed"))
            .withQueryParam("date", matching("[0-9]{4}-[0-9]{2}-[0-9]{2}"))
            .willReturn(ok().withBodyFile("contract/detailed-rating-information.json"))
        );
        DetailedRating rating = client.contracts.getDetailedRatingByContract(DEFAULT_CONTRACT_ID, "2014-01-03").execute().body();
        assertThat(rating.general.billingMode).isEqualTo(DetailedRating.BillingMode.MORE_THAN_50000_UNITS);
        assertThat(rating.details.get(0).date).isEqualTo(1388530800000L);
    }

    @Test
    public void shouldGetBssOrderConsumptions() throws Exception {
        sigfoxBackend.stubFor(get(urlPathMatching("/api/contracts/[a-zA-Z0-9]+/consumptions/[0-9]{4}"))
            .willReturn(ok().withBodyFile("contract/bss-orders-consumptions.json"))
        );
        ContractYearlyConsumption consumption = client.contracts.getConsumptionsByContract(DEFAULT_CONTRACT_ID, 2017).execute().body();
        assertThat(consumption.id).isEqualTo("53b5300093365d24f52ff393_2015");
        assertThat(consumption.consumptions.get(0).deviceCount).isEqualTo(4368);
        assertThat(consumption.consumptions.get(0).roamingDetailedFrameCount.get("SNO_FRANCE").roamingFrameCount).isEqualTo(151);
    }

}
