package com.github.slowhand.chatlatte.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.slowhand.chatlatte.R
import com.github.slowhand.chatlatte.messages.MessageListAdapter
import com.github.slowhand.chatlatte.models.Message
import kotlinx.android.synthetic.main.chat_latte_view.view.*

class ChatLatteView: ConstraintLayout {

    // property
    private var _messages: MutableList<Message> = ArrayList()
    var messages: List<Message> = emptyList()
        set(value) {
            _messages.clear()
            _messages.addAll(0, value)
            field = value
        }

    private val adapter: MessageListAdapter by lazy {
        MessageListAdapter(context, _messages)
    }

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initialize()
    }

    private fun initialize() {
        LayoutInflater.from(context).inflate(R.layout.chat_latte_view, this)

        messageListView.adapter = adapter
        messageListView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        adapter.notifyDataSetChanged()
    }
}