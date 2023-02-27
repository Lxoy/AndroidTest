package com.example.androidtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var counter = 0
    lateinit var textViewCounter: TextView
    lateinit var buttonUp: Button
    lateinit var buttonDown: Button
    lateinit var name: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(applicationContext, "onCreate", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onCreate")

        textViewCounter = findViewById(R.id.textViewCounter)
        buttonUp = findViewById(R.id.buttonUp)
        buttonDown = findViewById(R.id.buttonDown)
        name = findViewById(R.id.name)

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
}