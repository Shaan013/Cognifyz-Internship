package com.example.task4

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        val textView = findViewById<TextView>(R.id.textSecond)
        val editText = findViewById<EditText>(R.id.editName)
        val button = findViewById<Button>(R.id.buttonSubmit)

        // Handle button click
        button.setOnClickListener {
            val name = editText.text.toString()
            if (name.isNotBlank()) {
                Toast.makeText(this, "Hello, $name!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
            }
        }

    }
}