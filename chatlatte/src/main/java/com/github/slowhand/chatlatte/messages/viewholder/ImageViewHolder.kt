package com.github.slowhand.chatlatte.messages.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.github.slowhand.chatlatte.R
import com.github.slowhand.chatlatte.view.RoundedImageView

class ImageViewHolder(view: View) : BaseViewHolder(view) {

    val messageImage: RoundedImageView by lazy {
        view.findViewById<RoundedImageView>(R.id.messageImage)
    }

    val messageImageContainer: LinearLayout by lazy {
        view.findViewById<LinearLayout>(R.id.messageImageContainer)
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