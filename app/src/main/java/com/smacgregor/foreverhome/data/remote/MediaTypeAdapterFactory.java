package com.smacgregor.foreverhome.data.remote;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.smacgregor.foreverhome.data.model.Media;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by smacgregor on 4/10/16.
 */
public class MediaTypeAdapterFactory implements TypeAdapterFactory {
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        if (!List.class.isAssignableFrom(type.getRawType())) {
            return null;
        }

        Type elementType = ((ParameterizedType) type.getType()).getActualTypeArguments()[0];
        if (!Media.class.isAssignableFrom(TypeToken.get(elementType).getRawType())) {
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
                // Media {} --> photos {} --> photo []
                if (jsonElement.isJsonObject()) {
                    JsonObject jsonObject = jsonElement.getAsJsonObject();
                    if (jsonObject.has("media")) {
                        jsonObject = jsonObject.getAsJsonObject("media");
                    }

                    if (jsonObject.has("photos")) {
                        jsonObject = jsonObject.getAsJsonObject("photos");
                    }

                    if (jsonObject.has("photo")) {
                        jsonElement = jsonObject.get("photo");
                    }
                }

                return delegate.fromJsonTree(jsonElement);
            }
        };
    }
}
