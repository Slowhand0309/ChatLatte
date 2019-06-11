package com.github.slowhand.chatlatte.messages.viewholder

import android.view.View
import android.widget.TextView
import com.github.slowhand.chatlatte.R

class TextViewHolder(view: View) : BaseViewHolder(view) {

    val messageText = view.findViewById<TextView>(R.id.messageText)
}