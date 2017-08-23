package ingeniousthings.sigfox.client;

import ingeniousthings.sigfox.model.Group;
import ingeniousthings.sigfox.model.GroupRequest;
import ingeniousthings.sigfox.model.Page;
import org.junit.Test;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class GroupClientTest extends SigfoxClientTestBase {

    private static final String DEFAULT_GROUP_ID = "AAA";

    @Test
    public void shouldGetGroupList() throws Exception {
        sigfoxBackend.stubFor(get(urlPathMatching("/api/groups"))
            .willReturn(ok().withBodyFile("group/group-list.json"))
        );
        Page<Group> page = client.groups.getGroups().execute().body();
        assertThat(page.hasContent()).isTrue();
        assertThat(page.getSize()).isEqualTo(2);

        List<Group> groups = page.getContent();
        assertThat(groups.get(0).id).isEqualTo("510b848ee4b0ca47869752b5");
    }

    @Test
    public void shouldGetGroupInformation() throws Exception {
        sigfoxBackend.stubFor(get(urlPathMatching("/api/groups/[a-zA-Z0-9]+"))
            .willReturn(ok().withBodyFile("group/group-information.json"))
        );
        Group group = client.groups.getGroup(DEFAULT_GROUP_ID).execute().body();
        assertThat(group.name).isEqualTo("Group 1");
    }


    @Test
    public void shouldPaginate() throws Exception {
        sigfoxBackend.stubFor(get(urlPathMatching("/api/groups"))
            .willReturn(ok().withBodyFile("group/group-list.json"))
        );
        Page<Group> page = client.groups.getGroups().execute().body();
        assertThat(page.hasNext()).isTrue();

        sigfoxBackend.stubFor(get(urlPathMatching("/api/groups")).
            willReturn(ok().withBodyFile("group/groups-without-paging.json"))
        );
        page = client.groups.getGroups().execute().body();
        assertThat(page.hasNext()).isFalse();
    }

    @Test
    public void shouldFailWhenBillableTrueAndClientDetailsEmpty() {
        try {
            new GroupRequest.Builder()
                .name("groupName")
                .parent("50f13484b846618c7bae77b7")
                .description("the description")
                .bssId("bss-89566516")
                .billable(true)
                .build();
            fail();
        } catch (NullPointerException e) {
            assertThat(e.getMessage()).contains("Expected group's clientName not to be null since billable is true");
        }
    }

    @Test
    public void shouldCreateGroup() throws Exception {
        sigfoxBackend.stubFor(post(urlMatching("/api/groups/create"))
            .withRequestBody(notMatching(".*id.*")) //should'nt have an id
            .willReturn(ok().withBodyFile("group/group-creation.json"))
        );
        GroupRequest groupRequest = new GroupRequest.Builder()
            .name("groupName")
            .description("the description")
            .parent("50f13484b846618c7bae77b7")
            .billable(true)
            .clientName("The client name")
            .clientEmail("client@mail.com")
            .clientAddress("1 client street")
            .bssId("bss-89566516")
            .build();

        String groupId = client.groups.create(groupRequest).execute().body();
        assertThat(groupId).isEqualTo("49a13484b865d18c7ba716c3");
    }

    @Test
    public void shouldEditGroup() throws Exception {
        sigfoxBackend.stubFor(post(urlMatching("/api/groups/edit"))
            .withRequestBody(containing("id")) //should have an id
            .willReturn(ok())
        );
        GroupRequest groupRequest = new GroupRequest.Builder()
            .id("50f13484b846618c7bae77b7")
            .name("groupName")
            .description("the description")
            .parent("50f13484b846618c7bae77b7")
            .billable(true)
            .clientName("The client name")
            .clientEmail("client@mail.com")
            .clientAddress("1 client street")
            .bssId("bss-89566516")
            .build();

        int status = client.groups.edit(groupRequest).execute().code();
        assertThat(status).isEqualTo(200);
    }

    @Test
    public void shouldDeleteGroup() throws Exception {
        sigfoxBackend.stubFor(post(urlMatching("/api/groups/delete"))
            .withRequestBody(containing("id")) //should have an id
            .willReturn(ok())
        );
        GroupRequest groupRequest = new GroupRequest.Builder()
            .id("50f13484b846618c7bae77b7").build();

        assertThat(client.groups.delete(groupRequest).execute().isSuccessful()).isTrue();
        assertThat(client.groups.delete("50f13484b846618c7bae77b7").execute().isSuccessful()).isTrue();
    }

}
