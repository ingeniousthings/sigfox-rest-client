package ingeniousthings.sigfox.mapping;

import com.google.gson.*;
import ingeniousthings.sigfox.model.Redundancy;
import ingeniousthings.sigfox.model.Redundancy.BaseStationRedundancy;

import java.lang.reflect.Type;

public class RedundancyDeserializer implements JsonDeserializer<Redundancy> {

    @Override
    public Redundancy deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject object = jsonElement.getAsJsonObject();
        int redundancy = object.get("redundancy").getAsInt();
        return new Redundancy(BaseStationRedundancy.valueOf(redundancy));
    }
}
