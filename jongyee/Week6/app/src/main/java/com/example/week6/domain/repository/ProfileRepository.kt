package com.example.week6.domain.repository

import com.example.week6.UserDto

interface ProfileRepository {
    suspend fun getUser(id: Int): Result<UserDto>
    suspend fun getUsers(page: Int): Result<List<UserDto>>
}
