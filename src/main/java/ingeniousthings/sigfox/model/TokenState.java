package ingeniousthings.sigfox.model;

import com.google.gson.annotations.SerializedName;
import ingeniousthings.sigfox.model.Device.TokenType;

public class TokenState {

    public final Code code;
    public final DetailMessage detailMessage;
    public final TokenType tokenType;
    public final String contractId;
    public final long tokenEnd;

    public TokenState(Code code, DetailMessage detailMessage, TokenType tokenType, String contractId, long tokenEnd) {
        this.code = code;
        this.detailMessage = detailMessage;
        this.tokenType = tokenType;
        this.contractId = contractId;
        this.tokenEnd = tokenEnd;
    }

    public enum Code {
        @SerializedName("0")
        OK(0, "OK"),
        @SerializedName("1")
        KO(1, "KO"),
        @SerializedName("2")
        NOT_APPLICABLE(2, "N/A");

        public final int value;
        public final String message;

        Code(int value, String message) {
            this.value = value;
            this.message = message;
        }
    }

    public enum DetailMessage {
        @SerializedName("Off contract")
        OFF_CONTRACT("Off contract"),
        @SerializedName("Not communicated")
        NOT_COMMUNICATED("Not communicated"),
        @SerializedName("Invalid token")
        INVALID_TOKEN("Invalid token"),
        @SerializedName("Ok")
        OK("Ok");

        public String value;

        DetailMessage(String value) {
            this.value = value;
        }
    }

    @Override
    public String toString() {
        return "TokenState{" +
            "code=" + code +
            ", detailMessage=" + detailMessage +
            ", tokenType=" + tokenType +
            ", contractId='" + contractId + '\'' +
            ", tokenEnd=" + tokenEnd +
            '}';
    }
}
