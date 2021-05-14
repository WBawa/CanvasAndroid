package com.riis.biogen

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

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
        val selectedDegree = intent.getStringExtra("selected_degree")
        val selectedMajor = intent.getStringExtra("selected_major")
        val favouriteActivities = intent.getStringExtra("favourite_activities")

        message.apply {
            "$firstName $lastName graduated in $yearOfGraduation with a $selectedDegree with a concentration in $selectedMajor from $school. Their favourite activities are $favouriteActivities.".also {
                text = it
            }
        }

    }
}