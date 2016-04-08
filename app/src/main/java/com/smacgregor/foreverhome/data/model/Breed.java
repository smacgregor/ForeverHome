package com.smacgregor.foreverhome.data.model;

import com.google.auto.value.AutoValue;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.SerializedName;

/**
 * Created by smacgregor on 3/27/16.
 */
@AutoValue
public abstract class Breed {

    public static Breed create(String name) {
        return new AutoValue_Breed(name);
    }

    public static TypeAdapterFactory typeAdapterFactory() {
        return AutoValue_Breed.typeAdapterFactory();
    }

    @SerializedName("$t") public abstract String name();
}
