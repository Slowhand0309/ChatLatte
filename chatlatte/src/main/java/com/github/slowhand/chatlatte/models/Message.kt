package com.github.slowhand.chatlatte.models

import java.util.*

enum class MessageType {
    TEXT,
    PICTURE,
    LINK
}

data class Message(
    val ownerId: UserId,
    var body: String = "",
    val type: MessageType = MessageType.TEXT,
    var isDelete: Boolean = false,
    val createdAt: Date = Date()
)