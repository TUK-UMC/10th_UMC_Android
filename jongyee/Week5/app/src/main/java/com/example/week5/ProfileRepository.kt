package com.example.week5

class ProfileRepository(private val service: ReqResService) {

    suspend fun getUser(id: Int): Result<UserDto> = try {
        val response = service.getUser(id)
        Result.success(response.data)
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun getUsers(page: Int): Result<List<UserDto>> = try {
        val response = service.getUsers(page)
        Result.success(response.data)
    } catch (e: Exception) {
        Result.failure(e)
    }
}
