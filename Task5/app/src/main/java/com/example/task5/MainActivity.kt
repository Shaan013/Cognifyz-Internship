package com.example.task5

import Person
import PersonAdapter
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PersonAdapter
    private lateinit var editTextName: EditText
    private lateinit var buttonAdd: Button
    private val personList = mutableListOf<Person>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        recyclerView = findViewById(R.id.recyclerView)
        editTextName = findViewById(R.id.editTextName)
        buttonAdd = findViewById(R.id.buttonAdd)

        adapter = PersonAdapter(personList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        buttonAdd.setOnClickListener {
            val name = editTextName.text.toString().trim()
            if (name.isNotEmpty()) {
                adapter.addPerson(Person(name))
                editTextName.text.clear()
            }
        }

    }
}