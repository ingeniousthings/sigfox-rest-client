package ingeniousthings.sigfox.client;

import ingeniousthings.sigfox.model.Page;
import ingeniousthings.sigfox.model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserClient {

    @GET("api/users")
    Call<Page<User>> getUsersByGroup(@Query("groupId") String groupId);
}
