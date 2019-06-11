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
                if (this.isOwner) LayoutType.INCOMING_TEXT else LayoutType.OUTGOING_TEXT
            }
            MessageType.PICTURE -> {
                if (this.isOwner) LayoutType.INCOMING_PICTURE else LayoutType.OUTGOING_PICTURE
            }
            MessageType.LINK -> {
                if (this.isOwner) LayoutType.INCOMING_LINK else LayoutType.OUTGOING_LINK
            }
        }
}