package ingeniousthings.sigfox.client;

import ingeniousthings.sigfox.model.*;
import ingeniousthings.sigfox.model.Status.Severity;
import org.junit.Test;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

public class DeviceClientTest extends SigfoxClientTestBase {

    private static final String DEFAULT_DEVICE_ID = "AAA";

    @Test
    public void shouldGetDeviceInformation() throws Exception {
        sigfoxBackend.stubFor(get(urlPathMatching("/api/devices/[a-zA-Z0-9]+")).
            willReturn(ok().withBodyFile("device/device-information.json"))
        );
        Device device = client.devices.getDevice(DEFAULT_DEVICE_ID).execute().body();
        assertThat(device.name).isEqualTo("Labege 4");
        assertThat(device.state).isEqualTo(Device.State.OK);
    }

    @Test
    public void shouldGetTokenState() throws Exception {
        sigfoxBackend.stubFor(get(urlPathMatching("/api/devices/[a-zA-Z0-9]+/token-state")).
            willReturn(ok().withBodyFile("device/device-token-state.json"))
        );
        TokenState tokenState = client.devices.getTokenStateByDevice(DEFAULT_DEVICE_ID).execute().body();
        assertThat(tokenState.code).isEqualTo(TokenState.Code.KO);
        assertThat(tokenState.detailMessage).isEqualTo(TokenState.DetailMessage.OFF_CONTRACT);
        assertThat(tokenState.tokenType).isEqualTo(Device.TokenType.CONTRACT);
    }

    @Test
    public void shouldGetMessagePages() throws Exception {
        sigfoxBackend.stubFor(get(urlPathMatching("/api/devices/[a-zA-Z0-9]+/messages")).
            willReturn(ok().withBodyFile("device/device-messages.json"))
        );
        Page<Message> page = client.devices.getMessagesByDevice(DEFAULT_DEVICE_ID).execute().body();
        assertThat(page.getSize()).isEqualTo(1);
        assertThat(page.hasNext()).isTrue();
        List<Message> messages = page.getContent();
        assertThat(messages.get(0).device).isEqualTo("002C");
        assertThat(messages.get(0).computedLocation.radius).isEqualTo(500);
        assertThat(messages.get(0).downlinkAnswerStatus.data).isEqualTo("1511000a00007894");
    }

    @Test
    public void shouldGetMessagesLocationPages() throws Exception {
        sigfoxBackend.stubFor(get(urlPathMatching("/api/devices/[a-zA-Z0-9]+/locations")).
            willReturn(ok().withBodyFile("device/device-messages-location.json"))
        );
        Page<MessageLocation> page = client.devices.getMessagesLocationByDevice(DEFAULT_DEVICE_ID).execute().body();
        assertThat(page.getSize()).isEqualTo(1);
        assertThat(page.hasNext()).isTrue();
        List<MessageLocation> messageLocations = page.getContent();
        assertThat(messageLocations.get(0).valid).isTrue();
        assertThat(messageLocations.get(0).lat).isEqualTo(42.4631156);
    }

    @Test
    public void shouldGetErrorStatusEventList() throws Exception {
        sigfoxBackend.stubFor(get(urlPathMatching("/api/devices/[a-zA-Z0-9]+/status/error")).
            willReturn(ok().withBodyFile("device/device-error-status-list.json"))
        );
        Page<Status.Error> page = client.devices.getErrorsByDevice(DEFAULT_DEVICE_ID).execute().body();
        assertThat(page.getSize()).isEqualTo(1);

        List<Status.Error> statuses = page.getContent();
        assertThat(statuses.get(0).deviceId).isEqualTo("4830");

        assertThat(statuses.get(0).callbacks.get(1)).isInstanceOf(Status.StatusEmailCallback.class);
        assertThat(statuses.get(0).callbacks.get(1).status).isEqualTo(200);
        Status.StatusEmailCallback status = (Status.StatusEmailCallback) statuses.get(0).callbacks.get(1);
        assertThat(status.message).isEqualTo("some messages");
    }

    @Test
    public void shouldGetWarnStatusEventList() throws Exception {
        sigfoxBackend.stubFor(get(urlPathMatching("/api/devices/[a-zA-Z0-9]+/status/warn")).
            willReturn(ok().withBodyFile("device/device-warn-status-list.json"))
        );
        Page<Status.Warning> page = client.devices.getWarningsByDevice(DEFAULT_DEVICE_ID).execute().body();
        assertThat(page.getSize()).isEqualTo(1);

        List<Status.Warning> statuses = page.getContent();
        assertThat(statuses.get(0).deviceIds).containsExactlyInAnyOrder("0235", "023A", "4830");
        assertThat(statuses.get(0).severity).isEqualTo(Severity.WARN);

        assertThat(statuses.get(0).callbacks.get(1)).isInstanceOf(Status.StatusEmailCallback.class);
        assertThat(statuses.get(0).callbacks.get(1).status).isEqualTo(200);
        Status.StatusEmailCallback status = (Status.StatusEmailCallback) statuses.get(0).callbacks.get(1);
        assertThat(status.message).isEqualTo("some messages");
    }

    @Test
    public void shouldGetNetworkStatus() throws Exception {
        sigfoxBackend.stubFor(get(urlPathMatching("/api/devices/[a-zA-Z0-9]+/networkstate")).
            willReturn(ok().withBodyFile("device/device-network-status.json"))
        );
        NetworkStatus networkStatus = client.devices.getNetworkStatusByDevice(DEFAULT_DEVICE_ID).execute().body();
        assertThat(networkStatus).isEqualTo(NetworkStatus.NOK);
    }

    @Test
    public void shouldGetMessageMetrics() throws Exception {
        sigfoxBackend.stubFor(get(urlPathMatching("/api/devices/[a-zA-Z0-9]+/messages/metric")).
            willReturn(ok().withBodyFile("device/device-message-metrics.json"))
        );
        MessageMetric messageMetric = client.devices.getMessageMetricsByDevice(DEFAULT_DEVICE_ID).execute().body();
        assertThat(messageMetric.lastDay).isEqualTo(47);
        assertThat(messageMetric.lastWeek).isEqualTo(276);
        assertThat(messageMetric.lastMonth).isEqualTo(784);
    }

    @Test
    public void shouldGetConsumptions() throws Exception {
        sigfoxBackend.stubFor(get(urlPathMatching("/api/devices/[a-zA-Z0-9]+/consumptions/[0-9]{4}")).
            willReturn(ok().withBodyFile("device/device-consumptions.json"))
        );
        DeviceYearlyConsumption deviceYearlyConsumption = client.devices.getConsumptionsByDevice(DEFAULT_DEVICE_ID, 2017).execute().body();
        assertThat(deviceYearlyConsumption.id).isEqualTo("110160_2015");
        assertThat(deviceYearlyConsumption.consumptions.get(0).frameCount).isEqualTo(12);
    }

}
