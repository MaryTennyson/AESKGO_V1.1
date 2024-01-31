package com.ebraratabay.aeskgo.services.repository


import com.ebraratabay.aeskgo.enums.AuthResults
import com.ebraratabay.aeskgo.services.FirebaseAuthService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FirebaseAuthRepository @Inject constructor(
    val firebaseAuthService: FirebaseAuthService
) {
    suspend fun signUpWithEmailAndPassword(email: String, password: String): Flow<AuthResults> {
        return firebaseAuthService.createNewUser(email, password)
    }


    suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<AuthResults> {
        return firebaseAuthService.signInUser(email, password)
    }


}