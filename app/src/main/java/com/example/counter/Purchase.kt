package com.example.counter

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Purchase(
    val name : String? = null,
    val price : Float? = null,
    val category : String? = null,
    val card : Boolean? = null
)