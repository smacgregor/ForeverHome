package com.smacgregor.foreverhome.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by smacgregor on 4/7/16.
 */
public enum Age {
    @SerializedName("Baby") BABY,
    @SerializedName("Young") YOUNG,
    @SerializedName("Adult") ADULT,
    @SerializedName("Senior") SENIOR
}
