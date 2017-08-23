package ingeniousthings.sigfox.mapping;

import com.google.gson.*;
import ingeniousthings.sigfox.model.CoverageMargins;

import java.lang.reflect.Type;

public class CoverageMarginsDeserializer implements JsonDeserializer<CoverageMargins> {

    @Override
    public CoverageMargins deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonElement element = jsonElement;
        if (jsonElement.isJsonObject()) {
            element = jsonElement.getAsJsonObject().get("margins");
        }
        JsonArray margins = element.getAsJsonArray();
        return new CoverageMargins(
            margins.get(0).getAsDouble(),
            margins.get(1).getAsDouble(),
            margins.get(2).getAsDouble()
        );
    }
}
