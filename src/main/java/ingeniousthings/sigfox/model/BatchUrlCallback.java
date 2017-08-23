package ingeniousthings.sigfox.model;

public class BatchUrlCallback extends Callback {

    public final String urlPattern;
    public final HttpMethod httpMethod;
    public final String linePattern;

    public BatchUrlCallback(String id, Channel channel, CallbackType callbackType, CallbackSubtype callbackSubtype, String payloadConfig, boolean enabled, boolean sendDuplicate, boolean dead, String urlPattern, HttpMethod httpMethod, String linePattern) {
        super(id, channel, callbackType, callbackSubtype, payloadConfig, enabled, sendDuplicate, dead);
        this.urlPattern = urlPattern;
        this.httpMethod = httpMethod;
        this.linePattern = linePattern;
    }

    @Override
    public String toString() {
        return "BatchUrlCallback{" +
            "urlPattern='" + urlPattern + '\'' +
            ", httpMethod=" + httpMethod +
            ", linePattern='" + linePattern + '\'' +
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
