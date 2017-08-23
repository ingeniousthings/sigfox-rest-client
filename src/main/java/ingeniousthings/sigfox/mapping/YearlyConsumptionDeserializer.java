package ingeniousthings.sigfox.mapping;

import com.google.gson.*;
import ingeniousthings.sigfox.model.DeviceYearlyConsumption;

import java.lang.reflect.Type;

public class YearlyConsumptionDeserializer<T> implements JsonDeserializer<T> {

    private Gson gson = new Gson();

    private Class<T> clazz;

    public YearlyConsumptionDeserializer(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public T deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject object = jsonElement.getAsJsonObject();
        return gson.fromJson(object.getAsJsonObject("consumption"), clazz);
    }
}
