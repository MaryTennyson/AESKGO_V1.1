package com.ebraratabay.aeskgo.services

import com.ebraratabay.aeskgo.enums.AuthEnumClass

import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser

class FirebaseAuthService {

    private lateinit var user: FirebaseUser
    val auth= FirebaseAuth.getInstance()

    sealed class AuthResult {
        data class Success(val userId: String) : AuthResult()
        data class Failure(val errorMessage: String) : AuthResult()
    }

    fun createNewUser(email: String, password: String) :AuthEnumClass  {
        try {
            val user= auth.createUserWithEmailAndPassword(email,password)
            if(user.isSuccessful){
               val userUID=auth.currentUser
                print("Kayıt başarılı")
                return AuthEnumClass.onSuccess
            }
           else {
                println("else içi hata")
                return AuthEnumClass.onFailuare}
        }catch(e: FirebaseException){
            print("Firebase Messsajı: ${e}")
            return AuthEnumClass.onFailuare
        }

    }


        fun signInUser(email: String, password: String): AuthEnumClass{

           try{
              val user= auth.signInWithEmailAndPassword(email,password)
               if(user.isSuccessful){
                   print("Giriş başarılı")
                   return AuthEnumClass.onSuccess
               }  else {
                   println("else içi hata")
                   return AuthEnumClass.onFailuare}
           } catch(e: FirebaseException){
               print("Firebase Mesajı: ${e}")
               return AuthEnumClass.onFailuare
              }
}





}