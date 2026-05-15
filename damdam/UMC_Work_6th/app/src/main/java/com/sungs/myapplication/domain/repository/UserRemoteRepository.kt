package com.sungs.myapplication.domain.repository

import com.sungs.myapplication.data.remote.dto.UserData

interface UserRemoteRepository {
    suspend fun getUser(id: Int): UserData
    suspend fun getUsers(page: Int): List<UserData>
}
