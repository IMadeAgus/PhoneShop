package com.example.phoneshop

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Phone(
    val name: String,
    val price: Int,
    val description: String,
    val photo: Int
) : Parcelable
