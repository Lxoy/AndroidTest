package com.example.androidtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage


class EmailActivity : AppCompatActivity() {
    private lateinit var button: Button
    private lateinit var sendTo: EditText
    private lateinit var subject: EditText
    private lateinit var body: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email)
        sendTo = findViewById(R.id.sendToText)
        subject = findViewById(R.id.emailSubjectText)
        body = findViewById(R.id.emailBodyText)
        button = findViewById(R.id.button)


        // attach setOnClickListener to button with Intent object define in it
        button.setOnClickListener {
            val username = "tt0492913@gmail.com"
            val password = "ipjjeetwugigblxq"
            val emailSend = sendTo.text.toString()
            val emailSubject = subject.text.toString()
            val emailBody = body.text.toString()
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val properties = Properties()
                    properties["mail.smtp.host"] = "smtp.gmail.com"
                    properties["mail.smtp.port"] = "587"
                    properties["mail.smtp.auth"] = "true"
                    properties["mail.smtp.starttls.enable"] = "true"
                    val session = Session.getDefaultInstance(properties, object : Authenticator() {
                        override fun getPasswordAuthentication(): PasswordAuthentication {
                            return PasswordAuthentication(username, password)
                        }
                    })

                    val message = MimeMessage(session)
                    message.setFrom(InternetAddress(username))
                    message.setRecipient(Message.RecipientType.TO, InternetAddress(emailSend))
                    message.subject = emailSubject
                    message.setText(emailBody)
                    Transport.send(message)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}