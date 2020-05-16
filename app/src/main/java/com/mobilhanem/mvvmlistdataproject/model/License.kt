package com.mobilhanem.mvvmlistdataproject.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class License(
    val key: String,
    val name: String,
    @SerializedName("node_id")
    val nodeId: String,
    @SerializedName("spdx_id")
    val spdxId: String,
    val url: String
) : Serializable