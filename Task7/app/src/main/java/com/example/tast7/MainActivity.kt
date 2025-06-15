package com.example.tast7

import UserDatabaseHelper
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

//import android.os.Bundle
//import android.widget.*
//import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var buttonSubmit: Button
    private lateinit var textViewData: TextView
    private lateinit var dbHelper: UserDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextName = findViewById(R.id.editTextName)
        editTextEmail = findViewById(R.id.editTextEmail)
        buttonSubmit = findViewById(R.id.buttonSubmit)
        textViewData = findViewById(R.id.textViewData)

        dbHelper = UserDatabaseHelper(this)

        buttonSubmit.setOnClickListener {
            val name = editTextName.text.toString().trim()
            val email = editTextEmail.text.toString().trim()

            if (name.isNotEmpty() && email.isNotEmpty()) {
                val id = dbHelper.insertUser(name, email)
                if (id > 0) {
                    Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show()
                    editTextName.text.clear()
                    editTextEmail.text.clear()
                    showSavedUsers() // show after insert
                } else {
                    Toast.makeText(this, "Failed to save", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        showSavedUsers() // also load saved users on app start
    }

    private fun showSavedUsers() {
        val users = dbHelper.getAllUsers()
        val data = StringBuilder("Saved Users:\n\n")
        for ((name, email) in users) {
            data.append("• $name - $email\n")
        }
        textViewData.text = data.toString()
    }
}
