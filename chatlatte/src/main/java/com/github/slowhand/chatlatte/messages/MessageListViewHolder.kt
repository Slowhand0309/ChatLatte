package com.github.slowhand.chatlatte.messages

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.slowhand.chatlatte.R

class MessageListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val messageText = view.findViewById<TextView>(R.id.messageText)
}