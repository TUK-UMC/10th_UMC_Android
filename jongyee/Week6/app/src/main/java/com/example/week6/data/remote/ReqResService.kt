package com.example.week6.data.remote

import com.example.week6.SingleUserResponse
import com.example.week6.UserListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReqResService {

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Int): SingleUserResponse

    @GET("users")
    suspend fun getUsers(@Query("page") page: Int): UserListResponse
}
