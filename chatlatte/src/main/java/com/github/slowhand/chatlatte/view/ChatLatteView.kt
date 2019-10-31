package com.github.slowhand.chatlatte.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.slowhand.chatlatte.R
import com.github.slowhand.chatlatte.config.ChatLatteConfig
import com.github.slowhand.chatlatte.messages.MessageListAdapter
import com.github.slowhand.chatlatte.models.Message
import com.github.slowhand.chatlatte.models.User
import kotlinx.android.synthetic.main.chat_latte_view.view.*

class ChatLatteView: ConstraintLayout {

    /* -- public methods and properties -- */
    // config
    var config = ChatLatteConfig()
        set(value) {
            field = value
            applyConfig()
        }

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

        if (config.autoScrollEnabled) messageListView.scrollToEnd()
        if (config.autoClearTextEnabled) inputEditText.text.clear()
    }

    // send button clicked
    fun setOnClickSendButtonListener(listener: (view: View, message: String) -> Unit) {
        sendButton.setOnClickListener { listener(it, inputEditText.text.toString()) }
    }

    // gallery button clicked
    fun setOnClickGalleryListener(listener: (view: View) -> Unit) {
        galleryButton.setOnClickListener(listener)
    }

    var onMessageClickListener: (message: Message) -> Unit = {}
    var onMessageLongClickListener: (message: Message) -> Boolean = { false }

    // add custom button
    fun setOnClickCustomButtonListener(listener: (view: View) -> Unit)
        = customButton.apply {
            visibility = View.VISIBLE
            setOnClickListener(listener)
        }

    fun setCustomButtonSize(width: Int, height: Int)
        = customButton.apply {
            val params = layoutParams.apply {
                this.width = width
                this.height = height
            }
            layoutParams = params
        }

    // change custom button image via bitmao
    fun setCustomButtonImage(bitmap: Bitmap)
        = customButton.apply {
            visibility = View.VISIBLE
            setImageBitmap(bitmap)
        }

    // change custom button image via drawable
    fun setCustomButtonImage(drawable: Drawable)
        = customButton.apply {
            visibility = View.VISIBLE
            setImageDrawable(drawable)
        }

    // change custom button image via resource
    fun setCustomButtonImage(@DrawableRes resId: Int)
        = customButton.apply {
            visibility = View.VISIBLE
            setImageResource(resId)
        }

    // scroll to end list view
    fun scrollToEnd() = messageListView.scrollToEnd()

    // call notifyDataSetChanged
    fun reload() = adapter.apply { notifyDataSetChanged() }


    private val adapter: MessageListAdapter by lazy {
        MessageListAdapter(context, _messages).apply {
            listener = onEventListener
        }
    }

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initialize()
    }

    private fun initialize() {
        LayoutInflater.from(context).inflate(R.layout.chat_latte_view, this)
        applyConfig()

        messageListView.adapter = adapter
        messageListView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        adapter.notifyDataSetChanged()
    }

    private fun applyConfig() {
        inputEditText.hint = config.placeholder
        inputEditText.setHintTextColor(config.placeholderColor)

        messageListView.setBackgroundColor(config.chatBackgroundColor)
        inputLayoutContainer.setBackgroundColor(config.inputTextContainerColor)

        config.sendButtonImage?.also { sendButton.setImageBitmap(it) }
        config.galleryButtonImage?.also { galleryButton.setImageBitmap(it) }
        invalidate()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
    }

    private val onEventListener = object: MessageListAdapter.OnEventListener {
        override fun onMessageClick(message: Message) {
            onMessageClickListener(message)
        }

        override fun onMessageLongClick(message: Message): Boolean
                = onMessageLongClickListener(message)
    }
}