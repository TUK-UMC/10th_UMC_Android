package com.example.week6.data.repository

import com.example.week6.UserDto
import com.example.week6.data.remote.ReqResService
import com.example.week6.domain.repository.ProfileRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepositoryImpl @Inject constructor(
    private val service: ReqResService
) : ProfileRepository {

    override suspend fun getUser(id: Int): Result<UserDto> = try {
        Result.success(service.getUser(id).data)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getUsers(page: Int): Result<List<UserDto>> = try {
        Result.success(service.getUsers(page).data)
    } catch (e: Exception) {
        Result.failure(e)
    }
}
