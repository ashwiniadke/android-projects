package com.example.earthquakedataapp.model

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("date")
    var date: String? = null,
)
