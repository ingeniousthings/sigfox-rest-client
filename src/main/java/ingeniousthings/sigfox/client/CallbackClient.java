package ingeniousthings.sigfox.client;

import ingeniousthings.sigfox.model.CallbackError;
import ingeniousthings.sigfox.model.Page;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CallbackClient {

    @GET("api/callbacks/messages/error")
    Call<Page<CallbackError>> getMessagesNotSentThisMonth(
        @Query("limit") Integer limit, @Query("offset") Integer offset, @Query("since") Long since, @Query("before") Long before,
        @Query("hexId") String deviceId, @Query("deviceTypeId") String deviceTypeId, @Query("groupId") String groupId);

    @GET("api/callbacks/messages/error")
    Call<Page<CallbackError>> getMessagesNotSentThisMonth();
}
