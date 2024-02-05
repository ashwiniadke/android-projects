package com.example.earthquakedataapp.model

import kotlinx.serialization.Serializable
import com.google.gson.annotations.SerializedName

@Serializable
data class EarthquakeData(

    @SerializedName("desc")
    var desc: String? = null,
    @SerializedName("metadata")
    var metadata: Metadata? = Metadata(),
    @SerializedName("result")
    var result: ArrayList<Result> = arrayListOf()
)
