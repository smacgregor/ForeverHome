package com.smacgregor.foreverhome.data.model;

import com.google.auto.value.AutoValue;
import com.google.gson.TypeAdapterFactory;

import java.util.List;

/**
 * Created by smacgregor on 4/3/16.
 */

@AutoValue
public abstract class Pet {

    public static Pet create(String name, String description, List<Breed> breeds) {
        return new AutoValue_Pet(name, description, breeds);
    }

    public static TypeAdapterFactory typeAdapterFactory() {
        return AutoValue_Pet.typeAdapterFactory();
    }

    public abstract String name();
    public abstract String description();
    public abstract List<Breed> breeds();
}
