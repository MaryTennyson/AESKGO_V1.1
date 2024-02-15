package com.ebraratabay.aeskgo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ebraratabay.aeskgo.enums.AuthResults
import com.ebraratabay.aeskgo.models.FirebaseStoreUser
import com.ebraratabay.aeskgo.services.FirebaseCloudStore
import com.ebraratabay.aeskgo.services.SharedPreferencesService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInPageViewModel @Inject constructor(
    val firestore: FirebaseCloudStore,

) :
    ViewModel() {

    val _storeState = MutableStateFlow<AuthResults>(AuthResults.Loading)

    val storeState: StateFlow<AuthResults> = _storeState

    fun continueButtonClicked(user: FirebaseStoreUser) {
        val userID = getUserID()
        viewModelScope.launch {
            firestore.setUser(userID, user).collect {
                if (it != null) {
                    _storeState.value = it
                }
            }
        }

    }

    fun getUserID(): String {
        return SharedPreferencesService("user_ID", )
    }


}