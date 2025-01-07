package com.example.bintest.domain

import com.google.gson.annotations.SerializedName

data class Bank (
    @SerializedName("name")
    val name:String,
    @SerializedName("url")
    val url:String?=null,
    @SerializedName("phone")
    val phone:String?=null,
    @SerializedName("city")
    val city:String?=null
)