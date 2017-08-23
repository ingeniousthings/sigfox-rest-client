package ingeniousthings.sigfox.model;

import com.google.gson.annotations.SerializedName;

public enum HttpMethod {
    @SerializedName("GET")
    GET,
    @SerializedName("POST")
    POST,
    @SerializedName("PUT")
    PUT
}
