package com.example.androidtest

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.*
import android.net.Uri

class MainActivity : AppCompatActivity() {

    private var counter = 0
    lateinit var textViewCounter: TextView
    lateinit var buttonUp: Button
    lateinit var buttonDown: Button
    lateinit var name: EditText
    lateinit var highscore: Button
    lateinit var emailButton: Button
    lateinit var smsButton: Button
    lateinit var downloadButton: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(applicationContext, "onCreate", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onCreate")

        textViewCounter = findViewById(R.id.textViewCounter)
        buttonUp = findViewById(R.id.buttonUp)
        buttonDown = findViewById(R.id.buttonDown)
        name = findViewById(R.id.name)
        highscore = findViewById(R.id.highscore)
        emailButton = findViewById(R.id.emailButton)
        smsButton = findViewById(R.id.sms)
        downloadButton = findViewById(R.id.downloadButton)


        highscore.setOnClickListener{
            val intent = Intent(this, HighActivity::class.java)
            startActivity(intent)
        }

        emailButton.setOnClickListener{
            val intent = Intent(this, EmailActivity::class.java)
            startActivity(intent)
        }

        smsButton.setOnClickListener{
            val intent = Intent(this, SmsActivity::class.java)
            startActivity(intent)
        }

        downloadButton.setOnClickListener{
            val url = "https://media.licdn.com/dms/image/C4D03AQFxBQleAIEHDg/profile-displayphoto-shrink_800_800/0/1622750170430?e=2147483647&v=beta&t=RG0KFjAzSJRXJ_WL4fdJG2G1rCH2ok7OMEwTojh7zag"
            val request = DownloadManager.Request(Uri.parse(url))
                .setTitle("Skola")
                .setDescription("Skola")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
            val downloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            downloadManager.enqueue(request)
        }


        buttonUp.setOnClickListener() {
            counter++
            textViewCounter.text = counter.toString()

            if(counter >= 10) {
                counter = 0
                val intent = Intent(this,SuccessActivity::class.java)
                intent.putExtra("name", name.text.toString())
                startActivity(intent)
            }
        }

        buttonDown.setOnClickListener() {
            if(counter > 0)
                counter --
            textViewCounter.text = counter.toString()
        }

        registerForContextMenu(textViewCounter)
        textViewCounter.setOnClickListener{ v -> openContextMenu(v)}
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(applicationContext, "onStart", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(applicationContext, "onResume", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onResume")
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(applicationContext, "onStop", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onStop")
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(applicationContext, "onPause", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onPause")
    }

    override fun onRestart() {
        super.onRestart()
        Toast.makeText(applicationContext, "onRestart", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(applicationContext, "onDestroy", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onDestroy")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.restore_counter -> {
                counter = 0
                textViewCounter.text = counter.toString()
                true
            }
            R.id.english -> {
                changeLanguage(this, "en")
                recreate()
            }
            R.id.croatian -> {
                changeLanguage(this, "hr")
                recreate()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val meniInflater = menuInflater
        menuInflater.inflate(R.menu.menu_float, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.restore_counter){
            counter = 0
            textViewCounter.text = counter.toString()
        }
        return super.onContextItemSelected(item)
    }

    @Suppress("DEPRECATION")
    fun changeLanguage(context: Context, language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val res = context.resources
        val config = Configuration(res.configuration)
        config.setLocale(locale)
        context.createConfigurationContext(config)
        res.updateConfiguration(config, res.displayMetrics)
    }
}