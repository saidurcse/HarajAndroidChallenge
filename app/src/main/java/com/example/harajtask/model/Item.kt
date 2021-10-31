package com.example.harajtask.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(
    @SerializedName("title") var title: String,
    @SerializedName("username") var username: String,
    @SerializedName("thumbURL") var thumbURL: String,
    @SerializedName("commentCount") var commentCount: Int,
    @SerializedName("city") var city: String,
    @SerializedName("date") var date: String,
    @SerializedName("body") var body: String
) : Parcelable
