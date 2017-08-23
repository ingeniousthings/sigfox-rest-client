package ingeniousthings.sigfox.mapping;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements JsonSerializer<Date>, JsonDeserializer<Date> {

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    private SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);

    @Override
    public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        String source = jsonElement.getAsString();
        Date date = null;
        try {
            date = formatter.parse(source);
        } catch (ParseException e) {
        }
        return date;
    }

    @Override
    public JsonElement serialize(Date date, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(formatter.format(date));
    }
}
