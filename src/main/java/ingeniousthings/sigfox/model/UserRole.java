package ingeniousthings.sigfox.model;

import java.util.List;

public class UserRole {
    public final Group group;
    public final CustomRole customRole;

    public UserRole(Group group, CustomRole customRole) {
        this.group = group;
        this.customRole = customRole;
    }

    @Override
    public String toString() {
        return "UserRole{" +
            "group=" + group +
            ", customRole=" + customRole +
            '}';
    }

    public class Group {

        public final String id;
        public final String name;
        public final String nameCI;
        public final String description;
        public final List<String> path;
        public final boolean billable;

        public Group(String id, String name, String nameCI, String description, List<String> path, boolean billable) {
            this.id = id;
            this.name = name;
            this.nameCI = nameCI;
            this.description = description;
            this.path = path;
            this.billable = billable;
        }

        @Override
        public String toString() {
            return "Group{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", nameCI='" + nameCI + '\'' +
                ", description='" + description + '\'' +
                ", path=" + path +
                ", billable=" + billable +
                '}';
        }
    }

    public class CustomRole {
        public final String id;
        public final String name;

        public CustomRole(String id, String name, String id1, String name1) {
            this.id = id1;
            this.name = name1;
        }

        @Override
        public String toString() {
            return "CustomRole{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
        }
    }
}
