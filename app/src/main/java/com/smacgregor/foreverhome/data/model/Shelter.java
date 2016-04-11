package com.smacgregor.foreverhome.data.model;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.SerializedName;

/**
 * Created by smacgregor on 4/10/16.
 */
@AutoValue
public abstract class Shelter {
    public static Shelter create(@Nullable String name,
                                 @Nullable String phoneNumber,
                                 @Nullable String state,
                                 @Nullable String emailAddress,
                                 @Nullable String city,
                                 @Nullable String postalCode) {
        return new AutoValue_Shelter(name, phoneNumber, state, emailAddress, city, postalCode);
    }

    public static TypeAdapterFactory typeAdapterFactory() {
        return AutoValue_Shelter.typeAdapterFactory();
    }

    @Nullable public abstract String name();
    @Nullable @SerializedName("phone") public abstract String phoneNumber();
    @Nullable public abstract String state();
    @Nullable @SerializedName("email") public abstract String emailAddress();
    @Nullable public abstract String city();
    @Nullable @SerializedName("zip") public abstract String postalCode();
}
