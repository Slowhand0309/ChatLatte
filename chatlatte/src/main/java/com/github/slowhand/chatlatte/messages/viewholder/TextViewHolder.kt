package com.github.slowhand.chatlatte.messages.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.github.slowhand.chatlatte.R

class TextViewHolder(view: View) : BaseViewHolder(view) {

    val messageText: TextView by lazy {
        view.findViewById<TextView>(R.id.messageText)
    }

    val messageTextContainer: LinearLayout by lazy {
        view.findViewById<LinearLayout>(R.id.messageTextContainer)
    }

    val messageTime: TextView by lazy {
        view.findViewById<TextView>(R.id.messageTime)
    }

    val messageStatus: TextView? by lazy {
        view.findViewById<TextView>(R.id.messageStatus)
    }

    val avatarImage: ImageView? by lazy {
        view.findViewById<ImageView>(R.id.avatarImage)
    }
}