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
public class StringTypeAdapterFactory implements TypeAdapterFactory {

    private static final String WEIRD_STRING_KEY_NAME = "$t";

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
                if (jsonElement.isJsonObject()) {
                    JsonObject jsonObject = jsonElement.getAsJsonObject();
                    if (jsonObject.has(WEIRD_STRING_KEY_NAME) && jsonObject.get(WEIRD_STRING_KEY_NAME).isJsonPrimitive()) {
                        jsonElement = jsonObject.getAsJsonPrimitive(WEIRD_STRING_KEY_NAME);
                    }
                }
                return delegate.fromJsonTree(jsonElement);
            }
        };
    }
}