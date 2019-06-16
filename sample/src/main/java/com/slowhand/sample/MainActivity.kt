package com.slowhand.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.slowhand.chatlatte.models.Message
import com.github.slowhand.chatlatte.models.UserId
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chatLatteView.messages = listOf(
            Message(body = "no1", ownerId = UserId("1"), isOwner = true),
            Message(body = "no2", ownerId = UserId("2"), isOwner = false),
            Message(body = "no3", ownerId = UserId("3"), isOwner = true))
    }
}
