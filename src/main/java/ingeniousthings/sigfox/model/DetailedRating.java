package ingeniousthings.sigfox.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailedRating {

    public final GeneralInformation general;
    public final List<DailyRatingDetails> details;
    public final CommunicationPrice communicationPrice;

    public DetailedRating(GeneralInformation general, List<DailyRatingDetails> details, CommunicationPrice communicationPrice) {
        this.general = general;
        this.details = details;
        this.communicationPrice = communicationPrice;
    }

    @Override
    public String toString() {
        return "DetailedRating{" +
            "general=" + general +
            ", details=" + details +
            ", communicationPrice=" + communicationPrice +
            '}';
    }

    public class GeneralInformation {
        public final String contract;
        public final long from;
        public final long to;
        public final BillingMode billingMode;
        public final String timezone;
        public final String fixedPrice;
        public final String communicationPrice;
        public final String totalPrice;
        public final long maxNumberOfDevices;
        public final int activatedDevices;
        public final String subscriptionLevel;
        public final String unitPricePerYear;
        public final String fixedPartPercentage;

        public GeneralInformation(String contract, long from, long to, BillingMode billingMode, String timezone, String fixedPrice, String communicationPrice, String totalPrice, long maxNumberOfDevices, int activatedDevices, String subscriptionLevel, String unitPricePerYear, String fixedPartPercentage) {
            this.contract = contract;
            this.from = from;
            this.to = to;
            this.billingMode = billingMode;
            this.timezone = timezone;
            this.fixedPrice = fixedPrice;
            this.communicationPrice = communicationPrice;
            this.totalPrice = totalPrice;
            this.maxNumberOfDevices = maxNumberOfDevices;
            this.activatedDevices = activatedDevices;
            this.subscriptionLevel = subscriptionLevel;
            this.unitPricePerYear = unitPricePerYear;
            this.fixedPartPercentage = fixedPartPercentage;
        }

        @Override
        public String toString() {
            return "GeneralInformation{" +
                "contract='" + contract + '\'' +
                ", from=" + from +
                ", to=" + to +
                ", billingMode=" + billingMode +
                ", timezone='" + timezone + '\'' +
                ", fixedPrice='" + fixedPrice + '\'' +
                ", communicationPrice='" + communicationPrice + '\'' +
                ", totalPrice='" + totalPrice + '\'' +
                ", maxNumberOfDevices=" + maxNumberOfDevices +
                ", activatedDevices=" + activatedDevices +
                ", subscriptionLevel='" + subscriptionLevel + '\'' +
                ", unitPricePerYear='" + unitPricePerYear + '\'' +
                ", fixedPartPercentage='" + fixedPartPercentage + '\'' +
                '}';
        }
    }


    public enum BillingMode {
        @SerializedName("1")
        MORE_THAN_50000_UNITS(1),
        @SerializedName("2")
        LESS_THAN_50000_UNITS(2);

        public final int values;

        BillingMode(int values) {
            this.values = values;
        }
    }

    public class DailyRatingDetails {
        public final long date;
        public final int cumulatedActivatedDevices;
        public final int activatedDeviceOnPeriod;
        public final int messagesSentOnPeriod;
        public final double dailyCost;

        public DailyRatingDetails(long date, int cumulatedActivatedDevices, int activatedDeviceOnPeriod, int messagesSentOnPeriod, double dailyCost) {
            this.date = date;
            this.cumulatedActivatedDevices = cumulatedActivatedDevices;
            this.activatedDeviceOnPeriod = activatedDeviceOnPeriod;
            this.messagesSentOnPeriod = messagesSentOnPeriod;
            this.dailyCost = dailyCost;
        }

        @Override
        public String toString() {
            return "DailyRatingDetails{" +
                "date=" + date +
                ", cumulatedActivatedDevices=" + cumulatedActivatedDevices +
                ", activatedDeviceOnPeriod=" + activatedDeviceOnPeriod +
                ", messagesSentOnPeriod=" + messagesSentOnPeriod +
                ", dailyCost=" + dailyCost +
                '}';
        }
    }

    public class CommunicationPrice {
        public final double unitPrice;
        public final int contractDuration;
        public final boolean terminated;
        public final int duration;
        public final double communicationPartPercentage;

        public CommunicationPrice(double unitPrice, int contractDuration, boolean terminated, int duration, double communicationPartPercentage) {
            this.unitPrice = unitPrice;
            this.contractDuration = contractDuration;
            this.terminated = terminated;
            this.duration = duration;
            this.communicationPartPercentage = communicationPartPercentage;
        }

        @Override
        public String toString() {
            return "CommunicationPrice{" +
                "unitPrice=" + unitPrice +
                ", contractDuration=" + contractDuration +
                ", terminated=" + terminated +
                ", duration=" + duration +
                ", communicationPartPercentage=" + communicationPartPercentage +
                '}';
        }
    }
}
