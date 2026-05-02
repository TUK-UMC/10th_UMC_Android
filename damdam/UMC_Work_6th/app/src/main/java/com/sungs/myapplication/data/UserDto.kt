package com.sungs.myapplication.data

import com.google.gson.annotations.SerializedName

// GET /api/users/{id} 응답
data class UserResponse(
    val data: UserData,
    val support: SupportData? = null
)

// GET /api/users?page=N 응답
data class UserListResponse(
    val page: Int,
    @SerializedName("per_page") val perPage: Int,
    val total: Int,
    @SerializedName("total_pages") val totalPages: Int,
    val data: List<UserData>,
    val support: SupportData? = null
)

// 실제 유저 데이터
data class UserData(
    val id: Int,
    val email: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    val avatar: String
)

// ReqRes 응답에 붙어오는 홍보 문구
data class SupportData(
    val url: String,
    val text: String
)