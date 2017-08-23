package ingeniousthings.sigfox.client;

import ingeniousthings.sigfox.model.CallbackError;
import ingeniousthings.sigfox.model.Page;
import org.junit.Test;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CallbackClientTest extends SigfoxClientTestBase {

    @Test
    public void shouldGetMessagesNotSentThisMonth() throws Exception {
        sigfoxBackend.stubFor(get(urlPathMatching("/api/callbacks/messages/error"))
            .willReturn(ok().withBodyFile("callback/messages-not-sent-this-month.json"))
        );
        Page<CallbackError> page = client.callbacks.getMessagesNotSentThisMonth().execute().body();
        assertThat(page.getSize()).isEqualTo(3);
        assertThat(page.hasNext()).isTrue();

        List<CallbackError> errors = page.getContent();
        assertThat(errors.get(0).callback).isInstanceOf(CallbackError.UrlCallback.class);
        assertThat(errors.get(2).parameters.data).isEqualTo("3b3616201e100000");
    }
}
