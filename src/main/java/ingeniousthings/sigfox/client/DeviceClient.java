package ingeniousthings.sigfox.client;

import ingeniousthings.sigfox.model.*;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DeviceClient {

    @GET("api/devices/{deviceId}")
    Call<Device> getDevice(@Path("deviceId") String deviceId);

    @GET("api/devices/{deviceId}/token-state")
    Call<TokenState> getTokenStateByDevice(@Path("deviceId") String deviceId);

    @GET("api/devices/{deviceId}/messages")
    Call<Page<Message>> getMessagesByDevice(@Path("deviceId") String deviceId, @Query("limit") int limit, @Query("before") long before, @Query("since") long since, @Query("offset") int offset);

    @GET("api/devices/{deviceId}/messages")
    Call<Page<Message>> getMessagesByDevice(@Path("deviceId") String deviceId);

    @GET("api/devices/{deviceId}/locations")
    Call<Page<MessageLocation>> getMessagesLocationByDevice(@Path("deviceId") String deviceId, @Query("limit") int limit, @Query("before") long before, @Query("since") long since, @Query("offset") int offset);

    @GET("api/devices/{deviceId}/locations")
    Call<Page<MessageLocation>> getMessagesLocationByDevice(@Path("deviceId") String deviceId);

    @GET("api/devices/{deviceId}/status/error")
    Call<Page<Status.Error>> getErrorsByDevice(@Path("deviceId") String deviceId, @Query("limit") int limit, @Query("before") long before, @Query("since") long since);

    @GET("api/devices/{deviceId}/status/error")
    Call<Page<Status.Error>> getErrorsByDevice(@Path("deviceId") String deviceId);

    @GET("api/devices/{deviceId}/status/warn")
    Call<Page<Status.Warning>> getWarningsByDevice(@Path("deviceId") String deviceId, @Query("limit") int limit, @Query("before") long before, @Query("since") long since);

    @GET("api/devices/{deviceId}/status/warn")
    Call<Page<Status.Warning>> getWarningsByDevice(@Path("deviceId") String deviceId);

    @GET("api/devices/{deviceId}/networkstate")
    Call<NetworkStatus> getNetworkStatusByDevice(@Path("deviceId") String deviceId);

    @GET("api/devices/{deviceId}/messages/metric")
    Call<MessageMetric> getMessageMetricsByDevice(@Path("deviceId") String deviceId);

    @GET("api/devices/{deviceId}/consumptions/{year}")
    Call<DeviceYearlyConsumption> getConsumptionsByDevice(@Path("deviceId") String deviceId, @Path("year") int year);
}
