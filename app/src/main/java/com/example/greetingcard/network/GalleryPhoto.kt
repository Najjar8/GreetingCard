package com.example.greetingcard.network


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json


@Serializable
data class GalleryPhoto (

    @SerialName(value = "img_src")
    val imgSrc: String

)