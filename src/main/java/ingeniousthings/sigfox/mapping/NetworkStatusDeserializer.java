package ingeniousthings.sigfox.mapping;

import com.google.gson.*;
import ingeniousthings.sigfox.model.NetworkStatus;

import java.lang.reflect.Type;

public class NetworkStatusDeserializer implements JsonDeserializer<NetworkStatus> {

    @Override
    public NetworkStatus deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        String value = jsonElement.getAsJsonObject().get("networkStatus").getAsString();
        return NetworkStatus.valueOf(value);
    }
}
