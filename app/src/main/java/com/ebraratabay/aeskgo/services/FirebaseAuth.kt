package com.ebraratabay.aeskgo.services

import com.ebraratabay.aeskgo.enums.AuthResults
import com.google.firebase.FirebaseException
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
            println(e.message)
            emit(AuthResults.Failure(e))
        }
    }

    fun currentUser(): Boolean {
        var isUserAvailable: Boolean
        if (firebaseAuth.currentUser?.uid != null) {
            isUserAvailable = true
        } else {
            isUserAvailable = false
        }
        return isUserAvailable
    }

    fun signInUser(email: String, password: String): Flow<AuthResults> = flow {
        try {

            println("1")
           val user= firebaseAuth.signInWithEmailAndPassword(email, password)
            println("2")
            emit(AuthResults.Success(user.result.user!!.uid))
            println("3")


        } catch (e: FirebaseException) {
            emit(AuthResults.Failure(e))
        }
    }


}