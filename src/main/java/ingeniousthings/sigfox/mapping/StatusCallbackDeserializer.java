package ingeniousthings.sigfox.mapping;

import com.google.gson.*;
import ingeniousthings.sigfox.model.Status;

import java.lang.reflect.Type;

public class StatusCallbackDeserializer implements JsonDeserializer<Status.StatusCallback> {

    private Gson gson = new Gson();

    @Override
    public Status.StatusCallback deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject object = jsonElement.getAsJsonObject();
        Class<? extends Status.StatusCallback> clazz =
            (object.has("subject") && !object.has("url")) ?
                Status.StatusEmailCallback.class : Status.StatusHttpCallBack.class;
        return gson.fromJson(object, clazz);
    }
}
