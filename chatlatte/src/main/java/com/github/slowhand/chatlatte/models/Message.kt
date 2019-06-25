package com.github.slowhand.chatlatte.models

import java.util.*

enum class MessageType(val rawValue: Int) {
    TEXT(0),
    PICTURE(1),
    LINK(2);
}

enum class LayoutType(val rawValue: Int) {
    INCOMING_TEXT(0),
    OUTGOING_TEXT(1),
    INCOMING_PICTURE(2),
    OUTGOING_PICTURE(3),
    INCOMING_LINK(4),
    OUTGOING_LINK(5)
}

data class Message(
    val ownerId: UserId,
    var body: String = "",
    val type: MessageType = MessageType.TEXT,
    val isOwner: Boolean = true,
    var isDelete: Boolean = false,
    val createdAt: Date = Date()
) {
    fun layoutType(): LayoutType
        = when(this.type) {
            MessageType.TEXT -> {
                if (this.isOwner) LayoutType.OUTGOING_TEXT else LayoutType.INCOMING_TEXT
            }
            MessageType.PICTURE -> {
                if (this.isOwner) LayoutType.OUTGOING_PICTURE else LayoutType.INCOMING_PICTURE
            }
            MessageType.LINK -> {
                if (this.isOwner) LayoutType.OUTGOING_LINK else LayoutType.INCOMING_LINK
            }
        }
}