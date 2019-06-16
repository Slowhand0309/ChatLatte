package com.github.slowhand.chatlatte.models

import android.graphics.Bitmap

data class UserId(val value: String)

data class User(
    val id: UserId,
    var name: String = "",
    var icon: Bitmap? = null
)