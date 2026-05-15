package com.sungs.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sungs.myapplication.data.remote.dto.UserData
import com.sungs.myapplication.domain.repository.UserRemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: UserRemoteRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        loadUserProfile()
        loadFollowingList()
    }

    private fun loadUserProfile() {
        viewModelScope.launch {
            try {
                val user = repository.getUser(id = 1)
                _uiState.update {
                    it.copy(
                        user = user,
                        nicknameText = "${user.firstName} ${user.lastName}"
                    )
                }
                Log.d(TAG, "유저 로드 성공: $user")
            } catch (e: Exception) {
                _uiState.update { it.copy(errorMessage = e.message) }
                Log.e(TAG, "유저 로드 실패", e)
            }
        }
    }

    private fun loadFollowingList() {
        viewModelScope.launch {
            try {
                val users = repository.getUsers(page = 1)
                _uiState.update {
                    it.copy(
                        following = users,
                        followingCountText = "팔로잉 (${users.size})"
                    )
                }
                Log.d(TAG, "팔로잉 로드 성공: ${users.size}명")
            } catch (e: Exception) {
                _uiState.update { it.copy(errorMessage = e.message) }
                Log.e(TAG, "팔로잉 로드 실패", e)
            }
        }
    }

    companion object {
        private const val TAG = "ProfileViewModel"
    }
}

data class ProfileUiState(
    val user: UserData? = null,
    val nicknameText: String = "",
    val following: List<UserData> = emptyList(),
    val followingCountText: String = "팔로잉 (0)",
    val errorMessage: String? = null
)
