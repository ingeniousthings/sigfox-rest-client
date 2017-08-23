package ingeniousthings.sigfox.client;

import ingeniousthings.sigfox.model.Group;
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
    Call<Group> create(@Body GroupRequest groupRequest);

    @POST("api/groups/edit")
    Call<Group> edit(@Body GroupRequest groupRequest);

    @POST("api/groups/delete")
    Call<Group> edit(@Body GroupRequest groupRequest);
}
