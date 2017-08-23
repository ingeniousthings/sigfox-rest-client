package ingeniousthings.sigfox.mapping;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import ingeniousthings.sigfox.model.DetailedRating;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DetailedRatingDeserializer implements JsonDeserializer<DetailedRating> {

    private Gson gson = new Gson();

    @Override
    public DetailedRating deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject object = jsonElement.getAsJsonObject();
        Type listOfDailyRatingDetailsType = new TypeToken<ArrayList<DetailedRating.DailyRatingDetails>>() {}.getType();

        return new DetailedRating(
            gson.fromJson(object.get("general"), DetailedRating.GeneralInformation.class),
            gson.fromJson(object.get("details"), listOfDailyRatingDetailsType),
            gson.fromJson(object.get("communicationPrice"), DetailedRating.CommunicationPrice.class)
        );

    }
}
