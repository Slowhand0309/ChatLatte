package com.slowhand.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.slowhand.chatlatte.Sample

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sample = Sample()
        sample.hello()
    }
}
