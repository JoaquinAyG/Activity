package com.example.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity3 : AppCompatActivity() {
    private lateinit var butSend: Button
    private lateinit var butQuit: Button
    private lateinit var editText: EditText
    private lateinit var textView: TextView
    private lateinit var butSendBack: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        butSend = findViewById(R.id.buttonSend3)
        butSendBack = findViewById(R.id.buttonSendBack3)
        butQuit = findViewById(R.id.quit3)
        editText = findViewById(R.id.editText3)
        textView = findViewById(R.id.textView3)

        textView.text = intent.getStringExtra(MESSAGE)

        butSend.setOnClickListener {
            sendText1()
        }

        butSendBack.setOnClickListener {
            sendText2()
        }

        butQuit.setOnClickListener {
            onBackPressed()
        }

    }
    private val activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result ->
        when (result.resultCode) {
            RESULT_CANCELED -> textView.text = "No data received"
            CODE_GET -> {
                val data = result.data
                val message = data?.getStringExtra(MESSAGE)
                textView.text = message
            }
            CODE_CLEAR -> {
                textView.text = ""
            }
        }
    }
    private fun sendText2() {
        val intent = Intent(applicationContext, MainActivity2::class.java)
        intent.putExtra(MESSAGE, editText.text.toString())
        activityResultLauncher.launch(intent)
    }
    private fun sendText1() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.putExtra(MESSAGE, editText.text.toString())
        setResult(CODE_GET, intent)
        onBackPressed()
    }
}