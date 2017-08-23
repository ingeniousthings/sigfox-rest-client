package ingeniousthings.sigfox.model;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class UrlCallback extends Callback {

    public final String urlPattern;
    public final HttpMethod httpMethod;
    public final boolean downlinkHook;
    public final Map<String, String> headers;

    public UrlCallback(String id, Channel channel, CallbackType callbackType, CallbackSubtype callbackSubtype, String payloadConfig, boolean enabled, boolean sendDuplicate, boolean dead, String urlPattern, HttpMethod httpMethod, boolean downlinkHook, Map<String, String> headers) {
        super(id, channel, callbackType, callbackSubtype, payloadConfig, enabled, sendDuplicate, dead);
        this.urlPattern = urlPattern;
        this.httpMethod = httpMethod;
        this.downlinkHook = downlinkHook;
        this.headers = headers;
    }

    @Override
    public String toString() {
        return "UrlCallback{" +
            "urlPattern='" + urlPattern + '\'' +
            ", httpMethod=" + httpMethod +
            ", downlinkHook=" + downlinkHook +
            ", headers=" + headers +
            ", id='" + id + '\'' +
            ", channel=" + channel +
            ", callbackType=" + callbackType +
            ", callbackSubtype=" + callbackSubtype +
            ", payloadConfig='" + payloadConfig + '\'' +
            ", enabled=" + enabled +
            ", sendDuplicate=" + sendDuplicate +
            ", dead=" + dead +
            '}';
    }
}
