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

    // join member
    fun join(user: User) = adapter.apply {
            members.add(user)
            notifyDataSetChanged()
        }

    // join members
    fun joins(vararg users: User) = adapter.apply {
        users.forEach { members.add(it) }
        notifyDataSetChanged()
    }

    // leave member
    fun leave(user: User) = adapter.apply {
        val index = members.indexOfFirst { it.id == user.id }
        if (index < 0) return@apply
        members.removeAt(index)
    }

    // leave all members
    fun leaveAll() = adapter.apply {
            members.clear()
            notifyDataSetChanged()
        }

    // send message
    fun sendMessage(message: Message) {
        _messages.add(message)
        adapter.notifyDataSetChanged()

        if (autoScrollEnabled) messageListView.scrollToEnd()
        if (autoClearTextEnabled) inputEditText.text.clear()
    }

    // send button clicked
    fun setOnClickSendButtonListener(listener: (view: View, message: String) -> Unit) {
        sendButton.setOnClickListener { listener(it, inputEditText.text.toString()) }
    }

    // gallery button clicked
    fun setOnClickGalleryListener(listener: (view: View) -> Unit) {
        galleryButton.setOnClickListener(listener)
    }

    fun setPlaceholder(message: String) {
        inputEditText.hint = message
    }

    // scroll to end list view
    fun scrollToEnd() = messageListView.scrollToEnd()

    // call notifyDataSetChanged
    fun reload() = adapter.apply { notifyDataSetChanged() }


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