package com.ebraratabay.aeskgo.services.repository

import com.ebraratabay.aeskgo.services.FirebaseAuthService
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


import javax.inject.Inject

class FirebaseAuthRepository @Inject constructor(
     val firebaseAuthService: FirebaseAuthService
) {
    suspend fun registerWithEmailAndPassword(email: String, password: String):Flow<Result<AuthResult>>{
        return firebaseAuthService.createNewUser(email, password)
    }


   suspend fun loginWithEmailAndPassword(email: String, password: String): Flow<Result<AuthResult>> {
        return firebaseAuthService.signInUser(email, password)
    }


}