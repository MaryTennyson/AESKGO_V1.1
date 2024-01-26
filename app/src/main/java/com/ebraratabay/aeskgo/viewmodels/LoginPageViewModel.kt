package com.ebraratabay.aeskgo.viewmodels

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.core.content.ContextCompat.startActivity

import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.ebraratabay.aeskgo.R
import com.ebraratabay.aeskgo.enums.AuthEnumClass
import com.ebraratabay.aeskgo.services.FirebaseAuthService
import com.ebraratabay.aeskgo.services.repository.FirebaseAuthRepository
import com.ebraratabay.aeskgo.views.activities.MainActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class LoginPageViewModel @Inject constructor(val firebaseAuthRepo: FirebaseAuthRepository) : ViewModel()  {

    fun signinButton(context:Context, email: String, password:String) {
        //TODO burada kalındı
       var intent= Intent(context, MainActivity::class.java)

    }

    fun signupButton(it:View, email: String,password: String){
     //   val authResult = FirebaseAuthService().createNewUser(email,password)
           val action= R.id.action_loginPageFragment_to_signInPageFragment
            Navigation.findNavController(it).navigate(action)

    }

    }
