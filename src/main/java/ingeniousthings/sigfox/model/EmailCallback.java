package ingeniousthings.sigfox.model;

public class EmailCallback extends Callback{

    public final String subject;
    public final String recipient;
    public final String message;

    public EmailCallback(String id, Channel channel, CallbackType callbackType, CallbackSubtype callbackSubtype, String payloadConfig, boolean enabled, boolean sendDuplicate, boolean dead, String subject, String recipient, String message) {
        super(id, channel, callbackType, callbackSubtype, payloadConfig, enabled, sendDuplicate, dead);
        this.subject = subject;
        this.recipient = recipient;
        this.message = message;
    }

    @Override
    public String toString() {
        return "EmailCallback{" +
            "subject='" + subject + '\'' +
            ", recipient='" + recipient + '\'' +
            ", message='" + message + '\'' +
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
