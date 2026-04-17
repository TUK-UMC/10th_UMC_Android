package com.example.week5

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    private val repository = ProfileRepository(ApiClient.reqResService)

    private val _userProfile = MutableLiveData<Result<UserDto>>()
    val userProfile: LiveData<Result<UserDto>> = _userProfile

    private val _followingList = MutableLiveData<Result<List<UserDto>>>()
    val followingList: LiveData<Result<List<UserDto>>> = _followingList

    init {
        loadUserProfile()
        loadFollowingList()
    }

    private fun loadUserProfile() {
        viewModelScope.launch {
            val result = repository.getUser(1)
            _userProfile.postValue(result)
        }
    }

    private fun loadFollowingList() {
        viewModelScope.launch {
            val result = repository.getUsers(1)
            _followingList.postValue(result)
        }
    }
}
