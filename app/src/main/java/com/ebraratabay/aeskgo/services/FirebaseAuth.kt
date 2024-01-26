package com.ebraratabay.aeskgo.services

import com.google.firebase.FirebaseException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class FirebaseAuthService @Inject constructor(var firebaseAuth: FirebaseAuth) {

     fun createNewUser(email: String, password: String): Flow<Result<AuthResult>> = flow {
        try {
            val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            emit(Result.success(authResult))
        } catch (e: FirebaseException) {
            emit(Result.failure(e))
        }
    }


     fun signInUser(email: String, password: String): Flow<Result<AuthResult>> = flow {

        try {
            val user = firebaseAuth.signInWithEmailAndPassword(email, password).await()

            emit(Result.success(user))

        } catch (e: FirebaseException) {
            emit(Result.failure(e))
        }
    }


}