package com.alanecher.testegok.repository.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cash(
    @SerializedName("title")
    val title: String,
    @SerializedName("bannerURL")
    val bannerURL: String,
    @SerializedName("description")
    val description: String
) : Parcelable