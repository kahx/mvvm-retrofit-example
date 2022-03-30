package com.kaansa.nuevo.models

data class PhotoItem(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)