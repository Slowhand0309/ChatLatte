package com.github.slowhand.chatlatte.messages

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

class MessageListView: RecyclerView {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initialize(context, attrs)
    }

    private fun initialize(context: Context, attrs: AttributeSet?) {

    }
}