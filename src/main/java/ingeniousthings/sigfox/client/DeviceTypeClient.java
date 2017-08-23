package ingeniousthings.sigfox.client;

import ingeniousthings.sigfox.model.*;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface DeviceTypeClient {

    @GET("api/devicetypes")
    Call<List<DeviceType>> getDeviceTypes(@Query("includeSubGroups") boolean includeSubGroups, @Query("contractInfoId") String contractInfoId);

    @GET("api/devicetypes")
    Call<List<DeviceType>> getDeviceTypes(@Query("includeSubGroups") boolean includeSubGroups);

    @GET("api/devicetypes")
    Call<List<DeviceType>> getDeviceTypes(@Query("contractInfoId") String contractInfoId);

    @GET("api/devicetypes")
    Call<List<DeviceType>> getDeviceTypes();

    @GET("api/devicetypes/{deviceTypeId}")
    Call<DeviceType> getDeviceType(@Path("deviceTypeId") String deviceTypeId);

    @GET("api/devicetypes/{deviceTypeId}/callbacks")
    Call<List<Callback>> getCallbacksByDeviceType(@Path("deviceTypeId") String deviceTypeId);

    @GET("api/devicetypes/{deviceTypeId}/status/error")
    Call<Page<Status.Error>> getErrorsByDeviceType(@Path("deviceTypeId") String deviceTypeId, @Query("limit") int limit, @Query("before") long before, @Query("since") long since);

    @GET("api/devicetypes/{deviceTypeId}/status/error")
    Call<Page<Status.Error>> getErrorsByDeviceType(@Path("deviceTypeId") String deviceTypeId);

    @GET("api/devicetypes/{deviceTypeId}/status/warn")
    Call<Page<Status.Warning>> getWarningsByDeviceType(@Path("deviceTypeId") String deviceTypeId, @Query("limit") int limit, @Query("before") long before, @Query("since") long since);

    @GET("api/devicetypes/{deviceTypeId}/status/warn")
    Call<Page<Status.Warning>> getWarningsByDeviceType(@Path("deviceTypeId") String deviceTypeId);

    @GET("api/devicetypes/{deviceTypeId}/messages")
    Call<Page<Message>> getMessagesByDeviceType(@Path("deviceTypeId") String deviceTypeId, @Query("limit") int limit, @Query("before") long before, @Query("since") long since, @Query("offset") int offset);

    @GET("api/devicetypes/{deviceTypeId}/messages")
    Call<Page<Message>> getMessagesByDeviceType(@Path("deviceTypeId") String deviceTypeId);

    @GET("api/devicetypes/{deviceTypeId}/devices")
    Call<Page<Device>> getDevicesByDeviceType(@Path("deviceTypeId") String deviceTypeId, @Query("snr") int snr, @Query("limit") int limit, @Query("offset") int offset);

    @GET("api/devicetypes/{deviceTypeId}/devices")
    Call<Page<Device>> getDevicesByDeviceType(@Path("deviceTypeId") String deviceTypeId, @Query("snr") int snr);

    @GET("api/devicetypes/{deviceTypeId}/devices")
    Call<Page<Device>> getDevicesByDeviceType(@Path("deviceTypeId") String deviceTypeId);

}
