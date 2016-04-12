package com.smacgregor.foreverhome.data.model;

import com.google.auto.value.AutoValue;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.SerializedName;

/**
 * Created by smacgregor on 4/9/16.
 */
@AutoValue
public abstract class Media {
    public static Media create(String url, String size) {
        return new AutoValue_Media(url, size);
    }

    public static TypeAdapterFactory typeAdapterFactory() {
        return AutoValue_Media.typeAdapterFactory();
    }

    @SerializedName("$t") abstract String url();
    @SerializedName("@size") abstract String size();
}
