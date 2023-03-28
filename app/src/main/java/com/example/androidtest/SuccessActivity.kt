package com.example.androidtest

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class SuccessActivity : AppCompatActivity() {
    lateinit var textViewMessage: TextView
    lateinit var buttonSend: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)
        Toast.makeText(applicationContext, "onCreate", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onCreate")

        buttonSend = findViewById(R.id.buttonSend)

        textViewMessage = findViewById(R.id.textViewMessage)
        val name = intent.getStringExtra("name")

        textViewMessage.text = getString(R.string.success, name)

        buttonSend.setOnClickListener(){
            val uri: Uri = Uri.parse("sms to: 09112345678")
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            intent.putExtra("sms_body", name)
            startActivity(intent)
        }
    }
}