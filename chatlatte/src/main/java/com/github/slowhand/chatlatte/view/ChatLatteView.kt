package com.github.slowhand.chatlatte.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.OneShotPreDrawListener.add
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.slowhand.chatlatte.R
import com.github.slowhand.chatlatte.messages.MessageListAdapter
import com.github.slowhand.chatlatte.models.Message
import com.github.slowhand.chatlatte.models.User
import com.github.slowhand.chatlatte.models.UserId
import kotlinx.android.synthetic.main.chat_latte_view.view.*

class ChatLatteView: ConstraintLayout {

    /* -- public methods and properties -- */

    // all messages
    private var _messages: MutableList<Message> = ArrayList()
    var messages: List<Message> = emptyList()
        set(value) {
            _messages.clear()
            _messages.addAll(0, value)
            field = value
        }

    // self user
    var self: User
        set(value) {
            adapter.self = value
        }
        get() = adapter.self

    // scroll to end position via send text
    var autoScrollEnabled = true

    // clear text field via send text
    var autoClearTextEnabled = true

    // members
    fun join(user: User) = adapter.apply {
            members.add(user)
            notifyDataSetChanged()
        }

    fun leaveAll() = adapter.apply {
            members.clear()
            notifyDataSetChanged()
        }

    fun sendMessage(message: Message) {
        _messages.add(message)
        adapter.notifyDataSetChanged()

        if (autoScrollEnabled) messageListView.scrollToEnd()
        if (autoClearTextEnabled) inputEditText.text.clear()
    }

    fun setOnClickSendButtonListener(listener: (view: View) -> Unit) {
        sendButton.setOnClickListener { listener(it) }
    }

    fun setPlaceholder(message: String) {
        inputEditText.hint = message
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

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
    }
}