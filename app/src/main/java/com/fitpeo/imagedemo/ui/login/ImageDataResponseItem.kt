package com.fitpeo.imagedemo.ui.login

data class ImageDataResponseItem(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
):java.io.Serializable