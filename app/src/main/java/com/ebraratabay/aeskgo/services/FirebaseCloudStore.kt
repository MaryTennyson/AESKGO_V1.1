package com.ebraratabay.aeskgo.services

import com.ebraratabay.aeskgo.enums.AuthResults
import com.ebraratabay.aeskgo.models.FirebaseStoreAuth
import com.ebraratabay.aeskgo.models.Vehicle
import com.google.firebase.FirebaseException
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseCloudStore @Inject constructor(val firestore: FirebaseFirestore) {
    val firebaseUser = HashMap<String, Any>()
    var vehicleList: ArrayList<Vehicle> = arrayListOf()


    fun setUser(userID: String, user: FirebaseStoreAuth): Flow<AuthResults> = flow {
        firebaseUser["name"] = user.name
        firebaseUser["surname"] = user.surname
        firebaseUser["phone_number"] = user.phoneNumber
        try {
            firestore.collection("Users").document(userID).set(firebaseUser).await()
            emit(AuthResults.Success("Successful"))

        } catch (e: FirebaseException) {
            emit(AuthResults.Failure(e))
        }
    }

    fun getUser(userID: String): Flow<AuthResults> = flow {

        try {
            val user = firestore.collection("Users").document(userID).get().await()
            if (user != null) {
                val userInfo = user.toObject(FirebaseStoreAuth::class.java) ?: FirebaseStoreAuth()
                emit(AuthResults.Success(userInfo))
            } else {
                emit(AuthResults.Failure(Exception("User Can Not Found")))
            }

        } catch (e: FirebaseException) {
            emit(AuthResults.Failure(e))
        }
    }

    fun getVehicles(): Flow<AuthResults> = flow {

        try {
            val vehicles = firestore.collection("Users").get()
            if (vehicles != null) {
                for (vehicle in vehicles.result) {
                    val vehicle = vehicle.toObject(Vehicle::class.java)
                    vehicleList.add(vehicle)
                }
                emit(AuthResults.Success(vehicleList))
            } else {
                emit(AuthResults.Failure(Exception("Vehicles have not found")))
            }

        } catch (e: FirebaseException) {
            emit(AuthResults.Failure(e))
        }
    }

}
