package ingeniousthings.sigfox.client;

import ingeniousthings.sigfox.model.Page;
import ingeniousthings.sigfox.model.User;
import ingeniousthings.sigfox.model.UserRole;
import org.junit.Test;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

public class UserClientTest extends SigfoxClientTestBase {

    public static final String DEFAULT_GROUP_ID = "AAA";

    @Test
    public void shouldGetUsersByGroup() throws Exception {
        sigfoxBackend.stubFor(get(urlPathMatching("/api/users")).
            withQueryParam("groupId", matching("[a-zA-Z0-9]+"))
            .willReturn(ok().withBodyFile("user/user-list.json"))
        );
        Page<User> page = client.users.getUsersByGroup(DEFAULT_GROUP_ID).execute().body();
        assertThat(page.getSize()).isEqualTo(2);
        assertThat(page.hasNext()).isTrue();

        List<User> users = page.getContent();
        assertThat(users.get(0).firstName).isEqualTo("Michel");
        assertThat(users.get(0).userRoles.size()).isEqualTo(1);

        UserRole role = users.get(1).userRoles.get(1);
        assertThat(role.group.name).isEqualTo("SIGFOX_France");
        assertThat(role.group.path).containsExactlyInAnyOrder("babecafebabecafebabecafe");
        assertThat(role.customRole.name).isEqualTo("OPT_DOWNLINK");
    }
}
