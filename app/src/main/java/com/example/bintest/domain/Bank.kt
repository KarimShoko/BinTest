package com.example.bintest.domain

import com.google.gson.annotations.SerializedName

data class Bank (
    val name:String,
    val url:String? = "Not available",
    val phone:String? = "No phone number",
    val city:String? = "Unknown city"
)