package ingeniousthings.sigfox.client;

import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import org.junit.ClassRule;
import org.junit.Rule;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class SigfoxClientTestBase {

    public static WireMockConfiguration DEBUG_CONFIG = wireMockConfig().notifier(new ConsoleNotifier(true)).port(8888);

    public static WireMockConfiguration STD_CONFIG = wireMockConfig().port(8888);

    @ClassRule
    public static WireMockClassRule WIRE_MOCK_RULE = new WireMockClassRule(STD_CONFIG);

    @Rule
    public WireMockClassRule sigfoxBackend = WIRE_MOCK_RULE;

    protected SigfoxClient client = SigfoxClient.create("http://localhost:8888", "foo", "bar");

    protected String sigfoxBackendHttpUrl = "http://" + sigfoxBackend.getOptions().bindAddress() + ":" + sigfoxBackend.getOptions().portNumber();

}
