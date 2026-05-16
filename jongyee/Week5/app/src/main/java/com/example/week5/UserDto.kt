package com.example.week5

import com.google.gson.annotations.SerializedName

data class UserDto(
    val id: Int,
    val email: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    val avatar: String
)

data class SingleUserResponse(
    val data: UserDto
)

data class UserListResponse(
    val page: Int,
    @SerializedName("per_page") val perPage: Int,
    val total: Int,
    @SerializedName("total_pages") val totalPages: Int,
    val data: List<UserDto>
)
