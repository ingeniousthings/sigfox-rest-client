package ingeniousthings.sigfox.mapping;

import com.google.gson.*;
import ingeniousthings.sigfox.model.CallbackError;

import java.lang.reflect.Type;

public class CallbackErrorCallbackDeserializer implements JsonDeserializer<CallbackError.Callback> {

    private Gson gson = new Gson();

    @Override
    public CallbackError.Callback deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject object = jsonElement.getAsJsonObject();
        Class<? extends CallbackError.Callback> clazz =
            (object.has("subject")) ? CallbackError.EmailCallback.class : CallbackError.UrlCallback.class;
        return gson.fromJson(jsonElement, clazz);
    }
}
