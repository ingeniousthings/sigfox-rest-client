package ingeniousthings.sigfox.mapping;

import com.google.gson.*;
import ingeniousthings.sigfox.model.Rating;

import java.lang.reflect.Type;

public class RatingDeserializer implements JsonDeserializer<Rating> {

    private Gson gson = new Gson();

    @Override
    public Rating deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject object = jsonElement.getAsJsonObject();
        if (object.has("rating")) {
            return gson.fromJson(object.getAsJsonObject("rating"), Rating.class);
        }
        return gson.fromJson(object, Rating.class);
    }
}
