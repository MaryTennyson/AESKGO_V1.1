package com.ebraratabay.aeskgo.services

import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class FirebaseCloudStore @Inject constructor(val firestore: FirebaseFirestore) {

    fun storeUser() {

        firestore.collection("Users").document()


    }
}