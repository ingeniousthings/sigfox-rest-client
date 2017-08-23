package ingeniousthings.sigfox.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public abstract class Status {

    public final long time;
    public final String message;
    public final Severity severity;
    public final String deviceTypeId;
    public final List<? extends StatusCallback> callbacks;

    public Status(long time, String message, Severity severity, String deviceTypeId, List<? extends StatusCallback> callbacks) {
        this.time = time;
        this.message = message;
        this.severity = severity;
        this.deviceTypeId = deviceTypeId;
        this.callbacks = callbacks;
    }

    public class Error extends Status{
        public final String deviceId;

        public Error(long time, String message, Severity severity, String deviceTypeId, List<? extends StatusCallback> callbacks, String deviceId) {
            super(time, message, severity, deviceTypeId, callbacks);
            this.deviceId = deviceId;
        }

        @Override
        public String toString() {
            return "Error{" +
                "time=" + time +
                ", message='" + message + '\'' +
                ", severity=" + severity +
                ", deviceTypeId='" + deviceTypeId + '\'' +
                ", callbacks=" + callbacks +
                ", deviceId='" + deviceId + '\'' +
                '}';
        }
    }

    public class Warning extends Status {
        public List<String> deviceIds;

        public Warning(long time, String message, Severity severity, String deviceTypeId, List<? extends StatusCallback> callbacks, List<String> deviceIds) {
            super(time, message, severity, deviceTypeId, callbacks);
            this.deviceIds = deviceIds;
        }

        @Override
        public String toString() {
            return "Warning{" +
                "time=" + time +
                ", message='" + message + '\'' +
                ", severity=" + severity +
                ", deviceTypeId='" + deviceTypeId + '\'' +
                ", callbacks=" + callbacks +
                ", deviceIds=" + deviceIds +
                '}';
        }
    }

    public enum Severity {
        @SerializedName("ERROR")
        ERROR,
        @SerializedName("WARN")
        WARN
    }

    public abstract class StatusCallback {

        public final int status;

        public StatusCallback(int status) {
            this.status = status;
        }
    }

    public class StatusHttpCallBack extends StatusCallback {
        public final String url;
        public final String info;

        public StatusHttpCallBack(String url, String info, int status) {
            super(status);
            this.url = url;
            this.info = info;
        }

        @Override
        public String toString() {
            return "StatusHttpCallBack{" +
                "status=" + status +
                ", url='" + url + '\'' +
                ", info='" + info + '\'' +
                '}';
        }
    }

    public class StatusEmailCallback extends StatusCallback {
        public final String subject;
        public final String message;

        public StatusEmailCallback(String subject, String message, int status) {
            super(status);
            this.subject = subject;
            this.message = message;
        }

        @Override
        public String toString() {
            return "StatusEmailCallback{" +
                "status=" + status +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                '}';
        }
    }
}
