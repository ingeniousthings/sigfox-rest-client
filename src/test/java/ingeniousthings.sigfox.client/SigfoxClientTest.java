package ingeniousthings.sigfox.client;

import com.github.tomakehurst.wiremock.client.BasicCredentials;
import org.junit.Before;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SigfoxClientTest extends SigfoxClientTestBase {

    @Before
    public void setup() {
        sigfoxBackend.stubFor(get(urlPathMatching("/api/.*"))
            .willReturn(unauthorized())
        );
    }

    @Test
    public void shouldSendBasicAuthorizationHeader() throws Exception {
        sigfoxBackend.stubFor(get(urlPathMatching("/api/.*"))
            .withBasicAuth("the-username", "the-password")
            .willReturn(ok().withBody("{}"))
        );
        SigfoxClient trutedClient = SigfoxClient.create(sigfoxBackendHttpUrl, "the-username", "the-password");
        assertThat(trutedClient.groups.getGroups().execute().code()).isEqualTo(200);
    }

    @Test
    public void shouldRetryAuthenticationOnlyOnce() throws Exception {
        sigfoxBackend.stubFor(get(urlMatching("/api/.*"))
            .withBasicAuth("foo", "bar")
            .willReturn(ok().withBody("{}"))
        );
        SigfoxClient notTrusted = SigfoxClient.create(sigfoxBackendHttpUrl, "hack", "hack");
        assertThat(notTrusted.groups.getGroups().execute().code()).isEqualTo(401);
        // 2 request to the endpoint
        verify(2, getRequestedFor(urlPathMatching("/api/[a-z]+")));
        // with one using credentials
        verify(1, getRequestedFor(urlPathMatching("/api/[a-z]+"))
            .withBasicAuth(new BasicCredentials("hack", "hack")));
    }


//    @Test
//    public void should() throws Exception {
//
//
//        SigfoxClient sigfox = SigfoxClient.create("56cb6b109336f4c73cc1f799", "a778891163ea92b76b6eb50124a5ca6d");
//        List<DeviceType> deviceTypes = sigfox.deviceTypes.getDeviceTypes().execute().body();
//
//        for (DeviceType deviceType : deviceTypes) {
//            System.out.println("> " + deviceType);
//            List<Callback> callbacks = sigfox.deviceTypes.getCallbacksByDeviceType(deviceType.id).execute().body();
//
//            for(Callback callback: callbacks) {
//                System.out.println(" | " + callback);
//            }
//
//        }
//
////        System.out.println(client.deviceTypes.getDeviceType("5334049693361f4a462d3162").execute().code());
////        DeviceType deviceType = client.deviceTypes.getDeviceType("5334049693361f4a462d3162").execute().body();
////        System.out.println(deviceType);
//
////        Page<Message> messages = client.deviceTypes.getMessageByDeviceType("57f8e4449058c25f87cb2cc0").execute().body();
////        System.out.println(messages);
//    }
}
