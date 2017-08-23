package ingeniousthings.sigfox.client;

import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import ingeniousthings.sigfox.model.*;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

public class DeviceTypeClientTest extends SigfoxClientTestBase{

    private static final String DEFAULT_DEVICE_TYPE_ID = "AAA";

    @Test
    public void shouldGetDeviceTypeList() throws Exception {
        sigfoxBackend.stubFor(get(urlPathMatching("/api/devicetypes")).
            willReturn(ok().withBodyFile("device-type/device-types-list.json"))
        );

        List<DeviceType> deviceTypes = client.deviceTypes.getDeviceTypes().execute().body();
        assertThat(deviceTypes.size()).isEqualTo(2);
        assertThat(deviceTypes.get(0).id).isEqualTo("4d3091a05ee16b3cc86699ab");
    }

    @Test
    public void shouldGetDeviceTypeInformation() throws Exception {
        sigfoxBackend.stubFor(get(urlPathMatching("/api/devicetypes/[a-zA-Z0-9]+")).
            willReturn(ok().withBodyFile("device-type/device-type-information.json"))
        );
        DeviceType deviceType = client.deviceTypes.getDeviceType(DEFAULT_DEVICE_TYPE_ID).execute().body();
        assertThat(deviceType.name).isEqualTo("Sigfox test device");
    }

    @Test
    public void shouldGetCallbackList() throws Exception {
        sigfoxBackend.stubFor(get(urlPathMatching("/api/devicetypes/[a-zA-Z0-9]+/callbacks")).
            willReturn(ok().withBodyFile("device-type/device-type-callback-list.json"))
        );

        List<Callback> callbacks = client.deviceTypes.getCallbacksByDeviceType(DEFAULT_DEVICE_TYPE_ID).execute().body();
        assertThat(callbacks.size()).isEqualTo(2);
        assertThat(callbacks.get(0)).isInstanceOf(UrlCallback.class);
        assertThat(callbacks.get(1)).isInstanceOf(BatchUrlCallback.class);
    }

    @Test
    public void shouldGetErrorStatusEventList() throws Exception {
        sigfoxBackend.stubFor(get(urlPathMatching("/api/devicetypes/[a-zA-Z0-9]+/status/error")).
            willReturn(ok().withBodyFile("device-type/device-type-error-status-list.json"))
        );
        Page<Status.Error> page = client.deviceTypes.getErrorsByDeviceType(DEFAULT_DEVICE_TYPE_ID).execute().body();
        assertThat(page.getSize()).isEqualTo(2);

        List<Status.Error> statuses = page.getContent();
        assertThat(statuses.get(0).deviceId).isEqualTo("0235");
        assertThat(statuses.get(0).time).isEqualTo(1381410600026L);

        assertThat(statuses.get(1).callbacks.get(1)).isInstanceOf(Status.StatusEmailCallback.class);
        assertThat(statuses.get(1).callbacks.get(1).status).isEqualTo(200);
        Status.StatusEmailCallback status = (Status.StatusEmailCallback) statuses.get(1).callbacks.get(1);
        assertThat(status.message).isEqualTo("some messages");
    }

    @Test
    public void shouldGetWarnStatusEventList() throws Exception {
        sigfoxBackend.stubFor(get(urlPathMatching("/api/devicetypes/[a-zA-Z0-9]+/status/warn")).
            willReturn(ok().withBodyFile("device-type/device-type-warn-status-list.json"))
        );
        Page<Status.Warning> page = client.deviceTypes.getWarningsByDeviceType(DEFAULT_DEVICE_TYPE_ID).execute().body();
        assertThat(page.getSize()).isEqualTo(1);
        assertThat(page.hasNext()).isTrue();

        List<Status.Warning> statuses = page.getContent();
        assertThat(statuses.get(0).deviceIds).containsExactlyInAnyOrder("0235", "023A", "4830");
        assertThat(statuses.get(0).time).isEqualTo(1381410600026L);
        assertThat(statuses.get(0).callbacks.get(0).status).isEqualTo(600);
    }

    @Test
    public void shouldGetMessagePages() throws Exception {
        sigfoxBackend.stubFor(get(urlPathMatching("/api/devicetypes/[a-zA-Z0-9]+/messages")).
            willReturn(ok().withBodyFile("device-type/device-type-messages.json"))
        );
        Page<Message> page = client.deviceTypes.getMessagesByDeviceType(DEFAULT_DEVICE_TYPE_ID).execute().body();
        assertThat(page.getSize()).isEqualTo(1);
        assertThat(page.hasNext()).isTrue();
        List<Message> messages = page.getContent();
        assertThat(messages.get(0).data).isEqualTo("3235353843fc");
        assertThat(messages.get(0).computedLocation.radius).isEqualTo(500);
    }

    @Test
    public void shouldGetDevices() throws Exception {
        sigfoxBackend.stubFor(get(urlPathMatching("/api/devicetypes/[a-zA-Z0-9]+/devices"))
            .withQueryParam("snr", equalTo("1"))
            .willReturn(ok().withBodyFile("device-type/device-type-devices.json"))
        );
        Page<Device> page = client.deviceTypes.getDevicesByDeviceType(DEFAULT_DEVICE_TYPE_ID, 1).execute().body();
        assertThat(page.getSize()).isEqualTo(2);
        assertThat(page.hasNext()).isTrue();
        List<Device> devices = page.getContent();
        assertThat(devices.get(0).id).isEqualTo("0B59");
        assertThat(devices.get(0).computedLocation.radius).isEqualTo(500);
    }


    @Test
    public void shouldExtractDataIntoList() throws Exception {
        sigfoxBackend.stubFor(get(urlPathMatching("/api/devicetypes"))
            .willReturn(ok().withBodyFile("device-type/device-types-list.json"))
        );

        List<DeviceType> deviceTypes = client.deviceTypes.getDeviceTypes().execute().body();
        assertThat(deviceTypes.size()).isEqualTo(2);
    }
}
