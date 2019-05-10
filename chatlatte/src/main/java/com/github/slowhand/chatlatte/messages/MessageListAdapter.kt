package com.github.slowhand.chatlatte.messages

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.slowhand.chatlatte.R
import com.github.slowhand.chatlatte.models.Message

class MessageListAdapter(
    private val context: Context,
    private val items: List<Message>
) : RecyclerView.Adapter<MessageListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageListViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.row_message_item_view, parent, false)
        return MessageListViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MessageListViewHolder, position: Int) {
        val message = items[position]
        holder.messageText.text = message.body
    }
}