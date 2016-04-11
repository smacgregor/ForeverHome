package com.smacgregor.foreverhome.data.model;

import com.google.auto.value.AutoValue;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by smacgregor on 4/3/16.
 */

@AutoValue
public abstract class Pet {

    public static Pet create(String name,
                             String description,
                             Age petAge,
                             Size size,
                             Gender gender,
                             Species species,
                             long serverId,
                             List<Breed> breeds,
                             List<Media> media,
                             Shelter shelter) {
        return new AutoValue_Pet(name, description, petAge, size, gender, species, serverId, breeds, media, shelter);
    }

    public static TypeAdapterFactory typeAdapterFactory() {
        return AutoValue_Pet.typeAdapterFactory();
    }

    public abstract String name();
    public abstract String description();
    public abstract Age age();
    public abstract Size size();

    @SerializedName("sex") public abstract Gender gender();
    @SerializedName("animal") public abstract Species species();
    @SerializedName("id") public abstract long serverId();

    public abstract List<Breed> breeds();
    public abstract List<Media> media();

    @SerializedName("contact") public abstract Shelter shelter();
}
