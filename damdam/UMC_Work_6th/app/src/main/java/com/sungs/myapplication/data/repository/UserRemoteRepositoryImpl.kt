package com.sungs.myapplication.data.repository

import com.sungs.myapplication.data.remote.dto.UserData
import com.sungs.myapplication.domain.repository.UserRemoteRepository
import com.sungs.myapplication.network.ReqResService
import javax.inject.Inject

class UserRemoteRepositoryImpl @Inject constructor(
    private val service: ReqResService
) : UserRemoteRepository {

    override suspend fun getUser(id: Int): UserData {
        return service.getUser(id).data
    }

    override suspend fun getUsers(page: Int): List<UserData> {
        return service.getUsers(page).data
    }
}
