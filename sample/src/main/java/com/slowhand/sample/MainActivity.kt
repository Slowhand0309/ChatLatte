package com.slowhand.sample

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.slowhand.chatlatte.models.Message
import com.github.slowhand.chatlatte.models.MessageType
import com.github.slowhand.chatlatte.models.User
import com.github.slowhand.chatlatte.models.UserId
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user1 = User(id = UserId("1"), name = "me")
        val user2 = User(id = UserId("2"), name = "user2", icon = BitmapFactory.decodeResource(resources, R.drawable.man1))
        val user3 = User(id = UserId("3"), name = "user3", icon = BitmapFactory.decodeResource(resources, R.drawable.woman1))

        chatLatteView.self = user1
        chatLatteView.join(user2)
        chatLatteView.join(user3)

        chatLatteView.messages = listOf(
            Message(body = "Hello John, thank you for calling Provide Support. How may I help you?", ownerId = UserId("1"), isOwner = true),
            Message(body = "Let me check that I have this right…", ownerId = UserId("2"), isOwner = false),
            Message(body = "That is a good question, let me find out for you.", ownerId = UserId("3"), isOwner = false),
            Message(body = "https://cdn.pixabay.com/photo/2016/03/26/14/04/marshall-1280626_960_720.jpg", ownerId = UserId("1"), isOwner = true, type = MessageType.PICTURE))


        chatLatteView.setOnClickSendButtonListener { _, message ->
            val m = Message(body = message, ownerId = UserId("1"), isOwner = true)
            chatLatteView.sendMessage(m)
        }

        chatLatteView.onMessageClickListener = { message ->
            Toast.makeText(this@MainActivity, "Message click: ${message.body}", Toast.LENGTH_SHORT).show()
        }

        chatLatteView.onMessageLongClickListener = { message ->
            Toast.makeText(this@MainActivity, "Message long click: ${message.body}", Toast.LENGTH_SHORT).show()
            true
        }
    }
}
