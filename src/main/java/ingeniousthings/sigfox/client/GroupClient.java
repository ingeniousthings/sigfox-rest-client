package ingeniousthings.sigfox.client;

import ingeniousthings.sigfox.model.Group;
import ingeniousthings.sigfox.model.GroupRequest;
import ingeniousthings.sigfox.model.Page;
import retrofit2.Call;
import retrofit2.http.*;

public interface GroupClient {

    @GET("api/groups")
    Call<Page<Group>> getGroups(@Query("limit") int limit, @Query("offset") int offset, @Query("parentId") String parentId);

    @GET("api/groups")
    Call<Page<Group>> getGroups(@Query("parentId") String parentId);

    @GET("api/groups")
    Call<Page<Group>> getGroups();

    @GET("api/groups/{groupId}")
    Call<Group> getGroup(@Path("groupId") String groupId);

    @POST("api/groups/create")
    Call<String> create(@Body GroupRequest groupRequest);

    @POST("api/groups/edit")
    Call<Void> edit(@Body GroupRequest groupRequest);

    @POST("api/groups/delete")
    Call<Void> delete(@Body GroupRequest groupRequest);

    default Call<Void> delete(String groupId) {
        return delete(new GroupRequest.Builder().id(groupId).build());
    }

}
