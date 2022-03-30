package com.kaansa.nuevo.service

import com.kaansa.nuevo.models.Comment
import com.kaansa.nuevo.models.Photo
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {
    @GET("photos")
    suspend fun getPhotoData():Photo

    @GET("comments")
    suspend fun getCommentData(@Query("postId") i: Int):Comment
}