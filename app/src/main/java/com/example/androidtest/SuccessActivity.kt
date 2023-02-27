package com.example.androidtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SuccessActivity : AppCompatActivity() {
    lateinit var textViewMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)

        textViewMessage = findViewById(R.id.textViewMessage)
        var name = intent.getStringExtra("name")
        textViewMessage.text = "$name, congratulations on your 10 steps!"
    }
}