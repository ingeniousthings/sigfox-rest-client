package ingeniousthings.sigfox.client;

import ingeniousthings.sigfox.model.DetailedRating;
import ingeniousthings.sigfox.model.Page;
import ingeniousthings.sigfox.model.Rating;
import ingeniousthings.sigfox.model.Rating.State;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface RatingClient {

    @GET("api/ratings")
    Call<Page<Rating>> getRatings(
        @Query("limit") Integer limit,
        @Query("offset") Integer offset,
        @Query("groupId") String groupId,
        @Query("externalClientId") List<String> externalClientId,
        @Query("fromTime") String fromTime,
        @Query("toTime") String toTime,
        @Query("state") State state,
        @Query("invoiced") Boolean invoiced
    );

    @GET("api/ratings")
    Call<Page<Rating>> getRatings();

    @GET("api/ratings/detailed")
    Call<Page<DetailedRating>> getDetailedRatings(
        @Query("limit") int limit,
        @Query("offset") int offset,
        @Query("groupId") String groupId,
        @Query("externalClientId") List<String> externalClientId,
        @Query("fromTime") String fromTime,
        @Query("toTime") String toTime,
        @Query("state") State state,
        @Query("invoiced") boolean invoiced
    );

    @GET("api/ratings/detailed")
    Call<Page<DetailedRating>> getDetailedRatings();

}
