package ingeniousthings.sigfox.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ingeniousthings.sigfox.mapping.*;
import ingeniousthings.sigfox.model.Callback;
import ingeniousthings.sigfox.model.*;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.Date;

public class SigfoxClient {

    public static SigfoxClient create(String login, String password) {
        return new SigfoxClient.Builder().credentials(login, password).build();
    }

    public static SigfoxClient create(String baseUrl, String login, String password) {
        return new Builder().baseUrl(baseUrl).credentials(login, password).build();
    }

    public static SigfoxClient create(String baseUrl, String login, String password, Level level) {
        return new Builder().baseUrl(baseUrl).credentials(login, password).logLevel(level).build();
    }

    public static SigfoxClient create(String login, String password, Level level) {
        return new Builder().credentials(login, password).logLevel(level).build();
    }

    public final CallbackClient callbacks;
    public final ContractClient contracts;
    public final CoverageClient coverages;
    public final DeviceClient devices;
    public final DeviceTypeClient deviceTypes;
    public final GroupClient groups;
    public final UserClient users;
    public final RatingClient ratings;

    public SigfoxClient(CallbackClient callbacks, ContractClient contracts, CoverageClient coverages, DeviceClient devices, DeviceTypeClient deviceTypes, GroupClient groups, UserClient users, RatingClient ratings) {
        this.callbacks = callbacks;
        this.contracts = contracts;
        this.coverages = coverages;
        this.devices = devices;
        this.deviceTypes = deviceTypes;
        this.groups = groups;
        this.users = users;
        this.ratings = ratings;
    }

    public static class Builder {

        public static final String DEFAULT_BASE_URL = "https://backend.sigfox.com";
        public static final Level DEFAULT_LOG_LEVEL = Level.NONE;

        private String baseUrl = DEFAULT_BASE_URL;
        private String login;
        private String password;
        private Level logLevel = DEFAULT_LOG_LEVEL;

        private static HttpLoggingInterceptor loggingInterceptor(Level logLevel) {
            return new HttpLoggingInterceptor().setLevel(logLevel);
        }

        private static Authenticator basicAuthenticator(String login, String password) {
            return (Route route, Response response) -> {
                String credentials = Credentials.basic(login, password);

                // If we already failed with these credentials, don't retry.
                if (credentials.equals(response.request().header("Authorization"))) {
                    return null;
                }

                return response
                    .request()
                    .newBuilder()
                    .addHeader("Authorization", credentials)
                    .build();
            };
        }

        private static OkHttpClient httpClient(Authenticator authenticator, Interceptor... interceptors) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            for (Interceptor interceptor : interceptors) {
                builder.addInterceptor(interceptor);
            }
            builder.authenticator(authenticator);
            return builder.build();
        }

        private static Gson gson() {
            return new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateConverter())
                .registerTypeAdapterFactory(new SigfoxDataExtractor())
                .registerTypeHierarchyAdapter(Callback.class, new CallbackDeserializer())
                .registerTypeHierarchyAdapter(Status.StatusCallback.class, new StatusCallbackDeserializer())
                .registerTypeAdapter(NetworkStatus.class, new NetworkStatusDeserializer())
                .registerTypeHierarchyAdapter(CallbackError.Callback.class, new CallbackErrorCallbackDeserializer())
                .registerTypeAdapter(Redundancy.class, new RedundancyDeserializer())
                .registerTypeAdapter(CoverageMargins.class, new CoverageMarginsDeserializer())
                .registerTypeAdapter(DeviceYearlyConsumption.class, new YearlyConsumptionDeserializer(DeviceYearlyConsumption.class))
                .registerTypeAdapter(Rating.class, new RatingDeserializer())
                .registerTypeAdapter(DetailedRating.class, new DetailedRatingDeserializer())
                .registerTypeAdapter(ContractYearlyConsumption.class, new YearlyConsumptionDeserializer(ContractYearlyConsumption.class))
                .create();
        }

        SigfoxClient build() {
            OkHttpClient httpClient = httpClient(
                basicAuthenticator(this.login, this.password),
                loggingInterceptor(logLevel)
            );

            Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(this.baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson()))
                .client(httpClient)
                .build();

            return new SigfoxClient(
                retrofit.create(CallbackClient.class),
                retrofit.create(ContractClient.class),
                retrofit.create(CoverageClient.class),
                retrofit.create(DeviceClient.class),
                retrofit.create(DeviceTypeClient.class),
                retrofit.create(GroupClient.class),
                retrofit.create(UserClient.class),
                retrofit.create(RatingClient.class)
            );
        }

        Builder credentials(String login, String password) {
            this.login = login;
            this.password = password;
            return this;
        }

        Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        Builder logLevel(Level logLevel) {
            this.logLevel = logLevel;
            return this;
        }
    }

}
