package ingeniousthings.sigfox.model;

public class Contract {

    public final String id;
    public final String groupId;
    public final String name;
    public final long startTime;
    public final long endTime;
    public final long communicationEndTime;
    public final String timezone;
    public final int tokenDuration;
    public final int activationPeriodDuration;
    public final int maxTokens;
    public final int maxTokensInUse;
    public final int tokensInUse;
    public final int state;
    public final int type;
    public final boolean bidir;
    public final int maximumNumberOfDownlinkFrames;

    public Contract(String id, String groupId, String name, long startTime, long endTime, long communicationEndTime, String timezone, int tokenDuration, int activationPeriodDuration, int maxTokens, int maxTokensInUse, int tokensInUse, int state, int type, boolean bidir, int maximumNumberOfDownlinkFrames) {
        this.id = id;
        this.groupId = groupId;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.communicationEndTime = communicationEndTime;
        this.timezone = timezone;
        this.tokenDuration = tokenDuration;
        this.activationPeriodDuration = activationPeriodDuration;
        this.maxTokens = maxTokens;
        this.maxTokensInUse = maxTokensInUse;
        this.tokensInUse = tokensInUse;
        this.state = state;
        this.type = type;
        this.bidir = bidir;
        this.maximumNumberOfDownlinkFrames = maximumNumberOfDownlinkFrames;
    }

    @Override
    public String toString() {
        return "Contract{" +
            "id='" + id + '\'' +
            ", groupId='" + groupId + '\'' +
            ", name='" + name + '\'' +
            ", startTime=" + startTime +
            ", endTime=" + endTime +
            ", communicationEndTime=" + communicationEndTime +
            ", timezone='" + timezone + '\'' +
            ", tokenDuration=" + tokenDuration +
            ", activationPeriodDuration=" + activationPeriodDuration +
            ", maxTokens=" + maxTokens +
            ", maxTokensInUse=" + maxTokensInUse +
            ", tokensInUse=" + tokensInUse +
            ", state=" + state +
            ", type=" + type +
            ", bidir=" + bidir +
            ", maximumNumberOfDownlinkFrames=" + maximumNumberOfDownlinkFrames +
            '}';
    }
}
