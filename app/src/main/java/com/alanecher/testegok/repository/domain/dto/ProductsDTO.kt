package com.alanecher.testegok.repository.domain.dto

import android.os.Parcelable
import com.alanecher.testegok.repository.domain.Cash
import com.alanecher.testegok.repository.domain.Product
import com.alanecher.testegok.repository.domain.Spotlight
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductsDTO(
    @SerializedName("spotlight")
    val spotlights: List<Spotlight>,
    @SerializedName("products")
    val products: List<Product>,
    @SerializedName("cash")
    val cash: Cash
) : Parcelable