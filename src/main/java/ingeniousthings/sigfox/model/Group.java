package ingeniousthings.sigfox.model;

import java.util.Arrays;

public class Group {

    public final  String id;
    public final  String name;
    public final  String nameCI;
    public final  String description;
    public final  String[] path;
    public final  boolean billable;
    public final  String bssId;

    public Group(String id, String name, String nameCI, String description, String[] path, boolean billable, String bssId) {
        this.id = id;
        this.name = name;
        this.nameCI = nameCI;
        this.description = description;
        this.path = path;
        this.billable = billable;
        this.bssId = bssId;
    }

    @Override
    public String toString() {
        return "Group{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", nameCI='" + nameCI + '\'' +
            ", description='" + description + '\'' +
            ", path=" + Arrays.toString(path) +
            ", billable=" + billable +
            ", bssId='" + bssId + '\'' +
            '}';
    }
}
