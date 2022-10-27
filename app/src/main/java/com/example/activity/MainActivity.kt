package com.example.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var butSend: Button
    private lateinit var butQuit: Button
    private lateinit var editText: EditText
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        butSend = findViewById(R.id.buttonSend1)
        butQuit = findViewById(R.id.quit1)
        editText = findViewById(R.id.editText1)
        textView = findViewById(R.id.textView1)

        textView.text = intent.getStringExtra(MESSAGE)

        butSend.setOnClickListener {
            sendText2()
        }

        butQuit.setOnClickListener {
            finish()
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
}