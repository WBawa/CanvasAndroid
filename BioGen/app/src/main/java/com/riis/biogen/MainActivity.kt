package com.riis.biogen

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioButton: RadioButton
    private lateinit var createButton: Button
    private lateinit var spinner: Spinner


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        radioGroup = findViewById(R.id.radiogroup)
        createButton = findViewById(R.id.create_button)
        spinner = findViewById(R.id.spinner)

        val adapter = ArrayAdapter.createFromResource(this, R.array.majors, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        createButton.setOnClickListener {

        }

    }

    fun checkMajor(v: View) {
        Log.d(TAG, "hi")

    }
}