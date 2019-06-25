package com.github.slowhand.chatlatte.messages

import android.content.Context
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import com.github.slowhand.chatlatte.R
import com.github.slowhand.chatlatte.messages.viewholder.BaseViewHolder
import com.github.slowhand.chatlatte.messages.viewholder.TextViewHolder
import com.github.slowhand.chatlatte.models.LayoutType
import com.github.slowhand.chatlatte.models.Message
import com.github.slowhand.chatlatte.models.User
import com.github.slowhand.chatlatte.models.UserId

class MessageListAdapter(
    private val context: Context,
    private val items: List<Message>
) : RecyclerView.Adapter<BaseViewHolder>() {

    var self = User(id = UserId(""))
    var members: MutableList<User> = ArrayList()

    /**
     * viewType毎に異なるViewHolderを生成する
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when(viewType) {
            LayoutType.INCOMING_TEXT.rawValue -> {
                val layoutInflater = LayoutInflater.from(context)
                val view = layoutInflater.inflate(R.layout.row_message_incoming_text_view, parent, false)
                TextViewHolder(view)
            }
            LayoutType.OUTGOING_TEXT.rawValue -> {
                val layoutInflater = LayoutInflater.from(context)
                val view = layoutInflater.inflate(R.layout.row_message_outgoing_text_view, parent, false)
                TextViewHolder(view)
            }
            LayoutType.INCOMING_PICTURE.rawValue -> {
                val layoutInflater = LayoutInflater.from(context)
                val view = layoutInflater.inflate(R.layout.row_message_incoming_picture_view, parent, false)
                TextViewHolder(view) // TODO
            }
            LayoutType.OUTGOING_PICTURE.rawValue -> {
                val layoutInflater = LayoutInflater.from(context)
                val view = layoutInflater.inflate(R.layout.row_message_outgoing_picture_view, parent, false)
                TextViewHolder(view) // TODO
            }
            LayoutType.INCOMING_LINK.rawValue -> {
                val layoutInflater = LayoutInflater.from(context)
                val view = layoutInflater.inflate(R.layout.row_message_incoming_link_view, parent, false)
                TextViewHolder(view) // TODO
            }
            LayoutType.OUTGOING_LINK.rawValue -> {
                val layoutInflater = LayoutInflater.from(context)
                val view = layoutInflater.inflate(R.layout.row_message_outgoing_link_view, parent, false)
                TextViewHolder(view) // TODO
            }
            else -> {
                val layoutInflater = LayoutInflater.from(context)
                val view = layoutInflater.inflate(R.layout.row_message_incoming_text_view, parent, false)
                TextViewHolder(view)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    /**
     * MessageTypeのrawValueを返す
     */
    override fun getItemViewType(position: Int): Int
        = items[position].layoutType().rawValue

    /**
     * MessageType毎のViewHolderによって処理を行う
     */
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val message = items[position]

        when(getItemViewType(position)) {
            LayoutType.INCOMING_TEXT.rawValue -> {
                (holder as? TextViewHolder)?.also {
                    bindTextMessage(it, message)
                }
            }
            LayoutType.OUTGOING_TEXT.rawValue -> {
                (holder as? TextViewHolder)?.also {
                    bindTextMessage(it, message)
                }
            }
            LayoutType.INCOMING_PICTURE.rawValue -> {
                R.layout.row_message_incoming_picture_view
            }
            LayoutType.OUTGOING_PICTURE.rawValue -> {
                R.layout.row_message_outgoing_picture_view
            }
            LayoutType.INCOMING_LINK.rawValue -> {
                R.layout.row_message_incoming_link_view
            }
            LayoutType.OUTGOING_LINK.rawValue -> {
                R.layout.row_message_outgoing_link_view
            }
            else -> 0
        }
    }

    private fun bindTextMessage(holder: TextViewHolder, message: Message) {
        holder.messageText.text = message.body
        holder.messageTime.text = DateFormat.format("kk:mm", message.createdAt)

        // avatar image
        holder.avatarImage?.also { imageView ->
            members.filter { it.id == message.ownerId }.firstOrNull()?.also { owner ->
                owner.icon?.let { imageView.setImageBitmap(it) }
            }
        }
    }
}