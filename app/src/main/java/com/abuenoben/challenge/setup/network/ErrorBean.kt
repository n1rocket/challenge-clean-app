package com.abuenoben.challenge.setup.network

import com.google.gson.annotations.SerializedName

data class ErrorBean(
    val message: String,
    @SerializedName("message_dev")
    val messageDev: String
)