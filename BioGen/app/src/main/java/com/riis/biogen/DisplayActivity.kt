package com.riis.biogen

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

private const val TAG = "DisplayActivity"

class DisplayActivity : AppCompatActivity() {

    private lateinit var message: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        message = findViewById(R.id.display)

        val firstName = intent.getStringExtra("first_name")
        val lastName = intent.getStringExtra("last_name")
        val school = intent.getStringExtra("school")
        val yearOfGraduation = intent.getStringExtra("year_of_graduation")
//      val selectedDegree =
        val selectedMajor = intent.getStringExtra("selected_major")
        val favouriteActivities = intent.getStringExtra("favourite_activities")

        message.apply {
            "$firstName $lastName graduated in $yearOfGraduation with a (PLACEHOLDER) with a concentration in $selectedMajor from $school. Their favourite activities are $favouriteActivities.".also { text = it }
        }

    }
}