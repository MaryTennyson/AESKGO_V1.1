package com.ebraratabay.aeskgo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ebraratabay.aeskgo.enums.AuthResults
import com.ebraratabay.aeskgo.services.FirebaseCloudStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainPageViewModel @Inject constructor(
    val firestore: FirebaseCloudStore,

    ) : ViewModel() {

    val _vehicleState = MutableStateFlow<AuthResults>(AuthResults.Loading)

    val vehicleState: StateFlow<AuthResults> = _vehicleState

        fun getVehicleData(){
            viewModelScope.launch {
                firestore.getVehicles().collect{
                    if(it!=null){
                        _vehicleState.value= it
                    }
                }
            }
        }

}