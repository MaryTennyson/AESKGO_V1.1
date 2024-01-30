package com.ebraratabay.aeskgo.viewmodels

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.core.content.ContextCompat.startActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import com.ebraratabay.aeskgo.R
import com.ebraratabay.aeskgo.enums.AuthEnumClass
import com.ebraratabay.aeskgo.enums.AuthResults
import com.ebraratabay.aeskgo.services.FirebaseAuthService
import com.ebraratabay.aeskgo.services.repository.FirebaseAuthRepository
import com.ebraratabay.aeskgo.views.activities.MainActivity
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class LoginPageViewModel @Inject constructor(val firebaseAuthRepo: FirebaseAuthRepository) : ViewModel()  {

    val _authState= MutableStateFlow<AuthResults>(AuthResults.Loading)

    val authState: StateFlow<AuthResults> = _authState


    fun signinButton(context:Context, email: String, password:String) {
       viewModelScope.launch {
           firebaseAuthRepo.loginWithEmailAndPassword(email, password).collect{
               if(it!=null){
                 _authState.value= it
               }
           }
       }


    }

    fun signupButton(it:View, email: String,password: String){
     //   val authResult = FirebaseAuthService().createNewUser(email,password)
           val action= R.id.action_loginPageFragment_to_signInPageFragment
            Navigation.findNavController(it).navigate(action)

    }

    }
