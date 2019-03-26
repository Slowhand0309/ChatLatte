package com.github.slowhand.chatlatte.models

import java.util.*

enum class MessageType {
    TEXT,
    PICTURE,
    LINK
}

data class Message(
    var text: String = "",
    val createdAt: Date = Date()
)