package com.example.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {

    private lateinit var butSend: Button
    private lateinit var butQuit: Button
    private lateinit var editText: EditText
    private lateinit var textView: TextView
    private lateinit var butSendBack: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        butSend = findViewById(R.id.buttonSend2)
        butSendBack = findViewById(R.id.buttonSendBack2)
        butQuit = findViewById(R.id.quit2)
        editText = findViewById(R.id.editText2)
        textView = findViewById(R.id.textView2)

        textView.text = intent.getStringExtra(MESSAGE)

        butSend.setOnClickListener {
            sendText3()
        }

        butSendBack.setOnClickListener {
            sendText1()
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
    private fun sendText3() {
        val intent = Intent(applicationContext, MainActivity3::class.java)
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