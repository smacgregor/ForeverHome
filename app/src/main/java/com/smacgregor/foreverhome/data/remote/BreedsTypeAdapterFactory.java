package com.smacgregor.foreverhome.data.remote;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.smacgregor.foreverhome.data.model.Breed;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by smacgregor on 3/29/16.
 */
public class BreedsTypeAdapterFactory implements TypeAdapterFactory {
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {

        if (!List.class.isAssignableFrom(type.getRawType())) {
            return null;
        }

        Type elementType = ((ParameterizedType) type.getType()).getActualTypeArguments()[0];
        if (!Breed.class.isAssignableFrom(TypeToken.get(elementType).getRawType())) {
            return null;
        }

        final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);
        final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);

        return new TypeAdapter<T>() {
            @Override
            public void write(JsonWriter out, T value) throws IOException {
                delegate.write(out, value);
            }

            @Override
            public T read(JsonReader in) throws IOException {
                JsonElement jsonElement = elementAdapter.read(in);
                // breeds --> breed --> []
                if (jsonElement.isJsonObject()) {
                    JsonObject jsonObject = jsonElement.getAsJsonObject();
                    if (jsonObject.has("breeds")) {
                        jsonObject = jsonObject.getAsJsonObject("breeds");
                    }

                    if (jsonObject.has("breed")) {
                        // The petfinder API is not very clean. It can return a JsonArray or a JsonObject.
                        // Clean up the response here.
                        jsonElement = jsonObject.get("breed");
                        if (!jsonObject.get("breed").isJsonArray()) {
                            // convert the JsonObject to an array
                            JsonArray newArray = new JsonArray();
                            newArray.add(jsonObject.get("breed"));
                            jsonElement = newArray;
                        }
                    }
                }
                return delegate.fromJsonTree(jsonElement);
            }
        }.nullSafe();
    }
}
