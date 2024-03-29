package com.ebraratabay.aeskgo.services
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {
    @Provides
    @Singleton
    fun providesFirebaseService(): FirebaseAuth{
        return  FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun providesFirebaseCloudStore(): FirebaseFirestore
    {
      return  Firebase.firestore
    }
}