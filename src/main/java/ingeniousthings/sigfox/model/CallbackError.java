package ingeniousthings.sigfox.model;

import java.util.Map;

public class CallbackError {

    public final String device;
    public final String deviceType;
    public final long time;
    public final String data;
    public final double snr;
    public final String message;
    public final Callback callback;
    public final Parameters parameters;

    public CallbackError(String device, String deviceType, long time, String data, double snr, String message, Callback callback, Parameters parameters) {
        this.device = device;
        this.deviceType = deviceType;
        this.time = time;
        this.data = data;
        this.snr = snr;
        this.message = message;
        this.callback = callback;
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "CallbackError{" +
            "device='" + device + '\'' +
            ", deviceType='" + deviceType + '\'' +
            ", time=" + time +
            ", data='" + data + '\'' +
            ", snr=" + snr +
            ", message='" + message + '\'' +
            ", callback=" + callback +
            ", parameters=" + parameters +
            '}';
    }

    public static abstract class Callback {

    }

    public static class UrlCallback extends Callback {
        public final String url;
        public final Map<String, String> headers;
        public final String body;
        public final String contentType;
        public final HttpMethod method;

        public UrlCallback(String url, Map<String, String> headers, String body, String contentType, HttpMethod method) {
            this.url = url;
            this.headers = headers;
            this.body = body;
            this.contentType = contentType;
            this.method = method;
        }

        @Override
        public String toString() {
            return "UrlCallbackError{" +
                "url='" + url + '\'' +
                ", headers=" + headers +
                ", body='" + body + '\'' +
                ", contentType='" + contentType + '\'' +
                ", method=" + method +
                '}';
        }
    }

    public static class EmailCallback extends Callback {
        public final String subject;
        public final String message;

        public EmailCallback(String subject, String message) {
            this.subject = subject;
            this.message = message;
        }

        @Override
        public String toString() {
            return "EmailCallbackError{" +
                "subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                '}';
        }
    }

    public static class Parameters {
        public final long time;
        public final String station;
        public final String data;
        public final String device;
        public final double snr;

        public Parameters(long time, String station, String data, String device, double snr) {
            this.time = time;
            this.station = station;
            this.data = data;
            this.device = device;
            this.snr = snr;
        }

        @Override
        public String toString() {
            return "Parameters{" +
                "time=" + time +
                ", station='" + station + '\'' +
                ", data='" + data + '\'' +
                ", device='" + device + '\'' +
                ", snr=" + snr +
                '}';
        }
    }
}
