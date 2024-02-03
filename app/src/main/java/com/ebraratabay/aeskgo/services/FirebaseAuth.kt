package com.ebraratabay.aeskgo.services

import com.ebraratabay.aeskgo.enums.AuthResults
import com.google.firebase.FirebaseException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class FirebaseAuthService @Inject constructor(val firebaseAuth: FirebaseAuth) {

     fun createNewUser(email: String, password: String): Flow<AuthResults> = flow {
        try {
            val newUserResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            emit(AuthResults.Success(newUserResult.user!!.uid))
        } catch (e: FirebaseException) {
            emit(AuthResults.Failure(e))
        }
    }


     fun signInUser(email: String, password: String): Flow<AuthResults> = flow {

        try {
            val signInResult = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            emit(AuthResults.Success(signInResult.user!!.uid))

        } catch (e: FirebaseException) {
            emit(AuthResults.Failure(e))
        }
    }


}