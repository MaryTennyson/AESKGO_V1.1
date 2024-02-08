package com.ebraratabay.aeskgo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.ebraratabay.aeskgo.R
import com.ebraratabay.aeskgo.models.FirebaseStoreAuth
import com.ebraratabay.aeskgo.services.FirebaseAuthService
import com.ebraratabay.aeskgo.services.FirebaseCloudStore
import com.ebraratabay.aeskgo.services.SharedPreferencesService
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
@HiltViewModel
class SignInPageViewModel @Inject constructor(val firestore: FirebaseCloudStore, val sharedPreferencesService: SharedPreferencesService) {

    fun continueButtonClicked(user: FirebaseStoreAuth){
        val userID= getUserID()
        firestore.setUser(userID,user)


    }

    fun getUserID(): String{
       return sharedPreferencesService.getStringFromSP("user_ID")!!
    }


}