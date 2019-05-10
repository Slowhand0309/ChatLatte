package com.slowhand.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.slowhand.chatlatte.messages.MessageListAdapter
import com.github.slowhand.chatlatte.models.Message
import com.github.slowhand.chatlatte.models.UserId
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = MessageListAdapter(this,
                listOf(Message(body = "no1", ownerId = UserId("1")),
                        Message(body = "no2", ownerId = UserId("2")),
                        Message(body = "no3", ownerId = UserId("3"))))
        messageListView.adapter = adapter
        messageListView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        messageListView.adapter?.notifyDataSetChanged()
    }
}
