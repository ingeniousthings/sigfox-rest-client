package ingeniousthings.sigfox.client;

import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import ingeniousthings.sigfox.model.Group;
import ingeniousthings.sigfox.model.Page;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.*;

public class GroupClientTest extends SigfoxClientTestBase{

    private static final String DEFAULT_GROUP_ID = "AAA";

    @Test
    public void shouldGetGroupList() throws Exception {
        sigfoxBackend.stubFor(get(urlPathMatching("/api/groups")).
            willReturn(ok().withBodyFile("group/group-list.json"))
        );
        Page<Group> page = client.groups.getGroups().execute().body();
        assertThat(page.hasContent()).isTrue();
        assertThat(page.getSize()).isEqualTo(2);

        List<Group> groups = page.getContent();
        assertThat(groups.get(0).id).isEqualTo("510b848ee4b0ca47869752b5");
    }

    @Test
    public void shouldGetGroupInformation() throws Exception {
        sigfoxBackend.stubFor(get(urlPathMatching("/api/groups/[a-zA-Z0-9]+")).
            willReturn(ok().withBodyFile("group/group-information.json"))
        );
        Group group = client.groups.getGroup(DEFAULT_GROUP_ID).execute().body();
        assertThat(group.name).isEqualTo("Group 1");
    }


    @Test
    public void shouldPaginate() throws Exception {
        sigfoxBackend.stubFor(get(urlPathMatching("/api/groups")).
            willReturn(ok().withBodyFile("group/group-list.json"))
        );
        Page<Group> page = client.groups.getGroups().execute().body();
        assertThat(page.hasNext()).isTrue();

        sigfoxBackend.stubFor(get(urlPathMatching("/api/groups")).
            willReturn(ok().withBodyFile("group/groups-without-paging.json"))
        );
        page = client.groups.getGroups().execute().body();
        assertThat(page.hasNext()).isFalse();
    }

}
