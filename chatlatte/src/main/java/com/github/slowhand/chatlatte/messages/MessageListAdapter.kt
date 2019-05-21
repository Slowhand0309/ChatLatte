package com.github.slowhand.chatlatte.messages

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.slowhand.chatlatte.R
import com.github.slowhand.chatlatte.messages.viewholder.BaseViewHolder
import com.github.slowhand.chatlatte.messages.viewholder.TextViewHolder
import com.github.slowhand.chatlatte.models.Message
import com.github.slowhand.chatlatte.models.MessageType

class MessageListAdapter(
    private val context: Context,
    private val items: List<Message>
) : RecyclerView.Adapter<BaseViewHolder>() {

    /**
     * viewType毎に異なるViewHolderを生成する
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when(viewType) {
                MessageType.LINK.rawValue() -> {
                    // TODO
                    val layoutInflater = LayoutInflater.from(context)
                    val view = layoutInflater.inflate(R.layout.row_message_item_view, parent, false)
                    TextViewHolder(view)
                }
                else -> {
                    val layoutInflater = LayoutInflater.from(context)
                    val view = layoutInflater.inflate(R.layout.row_message_item_view, parent, false)
                    TextViewHolder(view)
                }
            }
    }

    override fun getItemCount(): Int = items.size

    /**
     * MessageTypeのrawValueを返す
     */
    override fun getItemViewType(position: Int): Int
        = items[position].type.rawValue()

    /**
     * MessageType毎のViewHolderによって処理を行う
     */
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val message = items[position]

        val type = getItemViewType(position)
        when(type) {
            // TODO
        }
        //holder.messageText.text = message.body
    }
}