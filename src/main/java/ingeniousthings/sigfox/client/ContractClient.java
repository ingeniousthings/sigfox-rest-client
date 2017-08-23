package ingeniousthings.sigfox.client;

import ingeniousthings.sigfox.model.Contract;
import ingeniousthings.sigfox.model.ContractYearlyConsumption;
import ingeniousthings.sigfox.model.DetailedRating;
import ingeniousthings.sigfox.model.Rating;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public interface ContractClient {

    @GET("api/contracts")
    Call<List<Contract>> getContractsByDeviceType(@Query("deviceTypeId") String deviceTypeId);

    @GET("api/contracts")
    Call<List<Contract>> getContracts();

    @GET("api/contracts/{contractId}")
    Call<Contract> getContract(@Path("contractId") String contractId);

    @GET("api/contracts/{contractId}/rating")
    default Call<Rating> getRatingByContract(@Path("contractId") String contractId, @Query("date") Date date) {
        return getRatingByContract(contractId, new SimpleDateFormat("yyyy-MM-dd").format(date));
    }

    @GET("api/contracts/{contractId}/rating")
    Call<Rating> getRatingByContract(@Path("contractId") String contractId, @Query("date") String date);

    @GET("api/contracts/{contractId}/rating/detailed")
    Call<DetailedRating> getDetailedRatingByContract(@Path("contractId") String contractId, @Query("date") Date date);

    @GET("api/contracts/{contractId}/rating/detailed")
    Call<DetailedRating> getDetailedRatingByContract(@Path("contractId") String contractId, @Query("date") String date);

    @GET("api/contracts/{contractId}/consumptions/{year}")
    Call<ContractYearlyConsumption> getConsumptionsByContract(@Path("contractId") String contractId, @Path("year") int year);
}
