package com.ebraratabay.aeskgo.services

import com.ebraratabay.aeskgo.enums.AuthResults
import com.google.firebase.FirebaseException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class FirebaseAuthService @Inject constructor(var firebaseAuth: FirebaseAuth) {

     fun createNewUser(email: String, password: String): Flow<AuthResults> = flow {
        try {
            val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            authResult.user
            emit(AuthResults.Success(authResult))
        } catch (e: FirebaseException) {
            emit(AuthResults.Failure(e))
        }
    }


     fun signInUser(email: String, password: String): Flow<AuthResults> = flow {

        try {
            val user = firebaseAuth.signInWithEmailAndPassword(email, password).await()

            emit(AuthResults.Success(user))

        } catch (e: FirebaseException) {
            emit(AuthResults.Failure(e))
        }
    }


}