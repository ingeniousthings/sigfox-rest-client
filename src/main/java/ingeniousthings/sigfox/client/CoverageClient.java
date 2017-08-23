package ingeniousthings.sigfox.client;

import ingeniousthings.sigfox.model.CoverageMargins;
import ingeniousthings.sigfox.model.CoverageMarginsByLocation;
import ingeniousthings.sigfox.model.CoverageRequest;
import ingeniousthings.sigfox.model.Redundancy;
import ingeniousthings.sigfox.model.Redundancy.Mode;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.List;

public interface CoverageClient {

    @GET("api/coverages/redundancy")
    Call<Redundancy> getRedundancy(@Query("lat") Double lat, @Query("lng") Double lng, @Query("mode") Mode mode);

    @GET("api/coverages/redundancy")
    default Call<Redundancy> getRedundancy(@Query("lat") Double lat, @Query("lng") Double lng) {
        return getRedundancy(lat, lng, null);
    }

    @GET("api/coverages/global/predictions")
    Call<CoverageMargins> getGlobalCoverage(@Query("lat") Double lat, @Query("lng") Double lng, @Query("radius") Integer radius);

    @GET("api/coverages/global/predictions")
    default Call<CoverageMargins> getGlobalCoverage(@Query("lat") Double lat, @Query("lng") Double lng) {
        return getGlobalCoverage(lat, lng, null);
    }

//
//    @GET("api/coverages/global/predictions")
//    default Call<SinglePointedGlobalCoverage> getSinglePointedGlobalCoverage(@Query("lat") Double lat, @Query("lng") Double lng) {
//        return getSinglePointedGlobalCoverage(lat, lng, null);
//    }
//
    @POST("api/coverages/global/predictions")
    Call<List<CoverageMarginsByLocation>> getGlobalCoverageByLocations(@Body CoverageRequest coverageRequest);

}
