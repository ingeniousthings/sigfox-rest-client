package ingeniousthings.sigfox.mapping;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import ingeniousthings.sigfox.model.Page;

import java.io.IOException;

public class SigfoxDataExtractor implements TypeAdapterFactory {

    public <T> TypeAdapter<T> create(Gson gson, final TypeToken<T> type) {

        final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);
        final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);

        return new TypeAdapter<T>() {

            public void write(JsonWriter out, T value) throws IOException {
                delegate.write(out, value);
            }

            public T read(JsonReader in) throws IOException {
                JsonElement jsonElement = elementAdapter.read(in);
                if (jsonElement.isJsonObject()) {
                    JsonObject jsonObject = jsonElement.getAsJsonObject();
                    if (jsonObject.has("data") && jsonObject.get("data").isJsonArray()
                        && !type.getRawType().equals(Page.class)) {
                        jsonElement = jsonObject.get("data");
                    }
                    if (jsonObject.has("id") && jsonObject.get("id").isJsonPrimitive()
                        && type.getRawType().equals(String.class)) {
                        jsonElement = jsonObject.get("id");
                    }
                }
                return delegate.fromJsonTree(jsonElement);
            }
        }.nullSafe();
    }

}
