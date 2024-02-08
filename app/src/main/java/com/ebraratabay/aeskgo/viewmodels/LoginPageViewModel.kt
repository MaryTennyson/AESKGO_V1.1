package com.ebraratabay.aeskgo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ebraratabay.aeskgo.enums.AuthResults
import com.ebraratabay.aeskgo.models.FirebaseAuthUser
import com.ebraratabay.aeskgo.services.SharedPreferencesService
import com.ebraratabay.aeskgo.services.repository.FirebaseAuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginPageViewModel @Inject constructor(
    val firebaseAuthRepo: FirebaseAuthRepository,
    val sharedPreferencesService: SharedPreferencesService
) :
    ViewModel() {

    val _authState = MutableStateFlow<AuthResults>(AuthResults.Loading)

    val authState: StateFlow<AuthResults> = _authState

    fun signInUser(user: FirebaseAuthUser) {
        _authState.value = AuthResults.Loading
        signInUser(user)
    }

    fun signUpUser(user: FirebaseAuthUser) {
        _authState.value = AuthResults.Loading
        signUpButton(user)
    }

    fun signInButton(user: FirebaseAuthUser) {
        viewModelScope.launch {
            firebaseAuthRepo.signInWithEmailAndPassword(user.email, user.password).collect {
                if (it != null) {
                    _authState.value = it
                }
            }
        }
    }

    fun signUpButton(user: FirebaseAuthUser) {
        viewModelScope.launch {
            firebaseAuthRepo.signUpWithEmailAndPassword(user.email, user.password).collect() {
                if (it != null) {
                    _authState.value = it
                }
            }
        }
    }

    fun editUserID(dataKey: String, userID: String) {
        sharedPreferencesService.editStringFromSP(dataKey, userID)
    }
}
