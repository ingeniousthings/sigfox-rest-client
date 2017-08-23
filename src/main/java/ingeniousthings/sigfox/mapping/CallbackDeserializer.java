package ingeniousthings.sigfox.mapping;

import com.google.gson.*;
import ingeniousthings.sigfox.model.BatchUrlCallback;
import ingeniousthings.sigfox.model.Callback;
import ingeniousthings.sigfox.model.Callback.Channel;
import ingeniousthings.sigfox.model.EmailCallback;
import ingeniousthings.sigfox.model.UrlCallback;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import static ingeniousthings.sigfox.model.Callback.Channel.*;

public class CallbackDeserializer implements JsonDeserializer<Callback> {

    private Gson gson;
    private Map<Channel, Class<? extends Callback>> mapping;

    public CallbackDeserializer() {
        this.gson = new Gson();
        mapping = new HashMap<>();
        mapping.put(URL, UrlCallback.class);
        mapping.put(BATCH_URL, BatchUrlCallback.class);
        mapping.put(EMAIL, EmailCallback.class);
    }

    @Override
    public Callback deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject object = jsonElement.getAsJsonObject();
        Channel channel = Channel.valueOf(object.get("channel").getAsString());
        return gson.fromJson(object, mapping.get(channel));
    }
}
