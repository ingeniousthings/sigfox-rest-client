package ingeniousthings.sigfox.model;

import static java.util.Objects.requireNonNull;

public class GroupRequest {

    public final String id;
    public final String name;
    public final String description;
    public final String parent;
    public final boolean billable;
    public final String clientName;
    public final String clientEmail;
    public final String clientAddress;
    public final String bssId;

    public GroupRequest(String id, String name, String description, String parent, boolean billable, String clientName, String clientEmail, String clientAddress, String bssId) {
        if (billable) {
            requireNonNull(clientName, "Expected group's clientName not to be null since billable is true");
            requireNonNull(clientEmail, "Expected group's clientEmail not to be null since billable is true");
            requireNonNull(clientAddress, "Expected group's clientAddress not to be null since billable is true");
        }
        this.id = id;
        this.name = name;
        this.description = description;
        this.parent = parent;
        this.billable = billable;
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.clientAddress = clientAddress;
        this.bssId = bssId;
    }

    public GroupRequest(String name, String description, String parent, boolean billable, String clientName, String clientEmail, String clientAddress, String bssId) {
        this(
            null,
            name,
            description,
            parent,
            billable,
            clientName,
            clientEmail,
            clientAddress,
            bssId
        );
    }

    public static class Builder {

        private String id;
        private String name;
        private String description;
        private String parent;
        private boolean billable;
        private String clientName;
        private String clientEmail;
        private String clientAddress;
        private String bssId;

        public GroupRequest build() {
            return new GroupRequest(
                id,
                name,
                description,
                parent,
                billable,
                clientName,
                clientEmail,
                clientAddress,
                bssId
            );

        }

        public Builder from(Group group) {
            this.id = group.id;
            this.name = group.name;
            this.description = group.description;
            this.billable = group.billable;
            this.bssId = group.bssId;
            return this;
        }

        public String id() {
            return id;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public String name() {
            return name;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public String description() {
            return description;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public String parent() {
            return parent;
        }

        public Builder parent(String parent) {
            this.parent = parent;
            return this;
        }

        public boolean billable() {
            return billable;
        }

        public Builder billable(boolean billable) {
            this.billable = billable;
            return this;
        }

        public String clientName() {
            return clientName;
        }

        public Builder clientName(String clientName) {
            this.clientName = clientName;
            return this;
        }

        public String clientEmail() {
            return clientEmail;
        }

        public Builder clientEmail(String clientEmail) {
            this.clientEmail = clientEmail;
            return this;
        }

        public String clientAddress() {
            return clientAddress;
        }

        public Builder clientAddress(String clientAddress) {
            this.clientAddress = clientAddress;
            return this;
        }

        public String bssId() {
            return bssId;
        }

        public Builder bssId(String bssId) {
            this.bssId = bssId;
            return this;
        }

    }
}
