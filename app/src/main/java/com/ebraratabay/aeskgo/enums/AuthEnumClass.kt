package com.ebraratabay.aeskgo.enums

import kotlinx.coroutines.flow.MutableStateFlow

enum class AuthEnumClass {
    onSuccess,
    onFailuare,
}


sealed class AuthResults  {
    class Success(val value: Any) : AuthResults()
    class Failure(val e: Exception) : AuthResults()
    object Loading : AuthResults()

}


// data/model/Result.kt


