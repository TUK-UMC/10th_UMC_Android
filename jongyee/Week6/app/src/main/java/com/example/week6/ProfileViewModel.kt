package com.example.week6

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week6.domain.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProfileUiState(
    val userProfile: UserDto? = null,
    val followingList: List<UserDto> = emptyList(),
    val error: String? = null
)

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState

    init {
        loadUserProfile()
        loadFollowingList()
    }

    private fun loadUserProfile() {
        viewModelScope.launch {
            profileRepository.getUser(1).fold(
                onSuccess = { user -> _uiState.update { it.copy(userProfile = user) } },
                onFailure = { e -> _uiState.update { it.copy(error = e.message) } }
            )
        }
    }

    private fun loadFollowingList() {
        viewModelScope.launch {
            profileRepository.getUsers(1).fold(
                onSuccess = { users -> _uiState.update { it.copy(followingList = users) } },
                onFailure = { e -> _uiState.update { it.copy(error = e.message) } }
            )
        }
    }
}
