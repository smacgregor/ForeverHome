package com.smacgregor.foreverhome.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by smacgregor on 3/27/16.
 */

public class Breed {

    @SerializedName("$t") String mName;

    public Breed() {}

    public Breed(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    @Override
    public String toString() {
        return getName();
    }

}
