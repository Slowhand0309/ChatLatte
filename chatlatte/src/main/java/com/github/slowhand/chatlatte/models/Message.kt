package com.github.slowhand.chatlatte.models

import java.util.*

enum class MessageType(val rawValue: Int) {
    TEXT(0) {
        override fun rawValue(): Int = rawValue
    },
    PICTURE(1) {
        override fun rawValue(): Int = rawValue
    },
    LINK(2) {
        override fun rawValue(): Int = rawValue
    };

    abstract fun rawValue(): Int
}

data class Message(
    val ownerId: UserId,
    var body: String = "",
    val type: MessageType = MessageType.TEXT,
    var isDelete: Boolean = false,
    val createdAt: Date = Date()
)