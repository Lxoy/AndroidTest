package com.example.androidtest

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import androidx.core.app.ActivityCompat

class SmsActivity : AppCompatActivity() {
    private lateinit var button: Button
    private lateinit var number: EditText
    private lateinit var sms: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sms)
        button = findViewById(R.id.smsButton)
        number = findViewById(R.id.numberText)
        sms = findViewById(R.id.smsText)

        ActivityCompat.requestPermissions(
            this@SmsActivity,
            arrayOf(
                Manifest.permission.SEND_SMS,
                Manifest.permission.READ_SMS
            ),
            PackageManager.PERMISSION_GRANTED
        )

        button.setOnClickListener {
            val number = number.text.toString()
            val message = sms.text.toString()
            val mySmsManager = SmsManager.getDefault()
            mySmsManager.sendTextMessage(number, null, message, null, null)
        }
    }
}