package com.sungs.myapplication.network

import com.sungs.myapplication.data.UserListResponse
import com.sungs.myapplication.data.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReqResService {

    // 단일 유저 조회
    @GET("api/users/{id}")
    suspend fun getUser(
        @Path("id") id: Int
    ): UserResponse

    // 유저 리스트 조회
    @GET("api/users")
    suspend fun getUsers(
        @Query("page") page: Int = 1
    ): UserListResponse
}