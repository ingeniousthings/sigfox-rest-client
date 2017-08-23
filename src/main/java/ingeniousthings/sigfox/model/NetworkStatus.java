package ingeniousthings.sigfox.model;

import com.google.gson.annotations.SerializedName;

public enum NetworkStatus {
    @SerializedName("OK")
    OK("OK"),
    @SerializedName("NOK")
    NOK("NOK");

    public final String value;

    NetworkStatus(String value) {
        this.value = value;
    }
}
