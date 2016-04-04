package com.smacgregor.foreverhome.data.remote;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Created by smacgregor on 4/3/16.
 */
public class PetTypeAdapterFactory implements TypeAdapterFactory {
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);
        final TypeAdapter<JsonElement> elementTypeAdapter = gson.getAdapter(JsonElement.class);

        return new TypeAdapter<T>() {
            @Override
            public void write(JsonWriter out, T value) throws IOException {
                delegate.write(out, value);
            }

            @Override
            public T read(JsonReader in) throws IOException {
                JsonElement jsonElement = elementTypeAdapter.read(in);
                // petfinder (Object) --> pets (Object) --> pet (Array) --> pet model (Object)
                if (jsonElement.isJsonObject()) {
                    JsonObject petsObject = jsonElement.getAsJsonObject();
                    if (petsObject.has("petfinder") && petsObject.get("petfinder").isJsonObject()) {
                        petsObject = petsObject.getAsJsonObject("petfinder");
                        if (petsObject.has("pets") && petsObject.get("pets").isJsonObject()) {
                            petsObject = petsObject.getAsJsonObject("pets");
                            if (petsObject.has("pet") && petsObject.get("pet").isJsonArray()) {
                                jsonElement = petsObject.get("pet");
                            }
                        }
                    }
                }
                return delegate.fromJsonTree(jsonElement);
            }
        };
    }
}
