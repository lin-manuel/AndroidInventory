package com.were.myfirstapp.network



import com.were.myfirstapp.model.CloudinaryResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface CloudinaryApi {

    @Multipart
    @POST("v1_1/dpb55u4fy/image/upload")
    suspend fun uploadImage(
        @Part file: MultipartBody.Part,
        @Part("upload_preset") uploadPreset: RequestBody
    ): Response<CloudinaryResponse>
}