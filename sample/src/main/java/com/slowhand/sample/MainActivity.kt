package com.slowhand.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.slowhand.chatlatte.messages.MessageListAdapter
import com.github.slowhand.chatlatte.models.Message
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = MessageListAdapter(this,
                listOf(Message(text = "no1"), Message(text = "no2"), Message(text = "no3")))
        messageListView.adapter = adapter
        messageListView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        messageListView.adapter?.notifyDataSetChanged()
    }
}
