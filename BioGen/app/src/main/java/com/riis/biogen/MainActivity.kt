package com.riis.biogen

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioButton: RadioButton
    private lateinit var createButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        radioGroup = findViewById(R.id.radiogroup)
        createButton = findViewById(R.id.create_button)

        createButton.setOnClickListener {

        }

    }

    fun checkMajor(v: View) {
        val radioId = radioGroup.checkedRadioButtonId
        radioButton = findViewById(radioId)
        Log.d(TAG, radioButton.text.toString())

    }
}