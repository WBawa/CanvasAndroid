package com.riis.biogen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioButton: RadioButton
    private lateinit var createButton: Button
    private lateinit var spinner: Spinner

    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var school: String
    private lateinit var yearOfGraduation: String
//     private lateinit var selectedDegree: String
    lateinit var selectedMajor: String
    private lateinit var favouriteActivities: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        radioGroup = findViewById(R.id.radiogroup)
        createButton = findViewById(R.id.create_button)
        spinner = findViewById(R.id.spinner)

        val adapter = ArrayAdapter.createFromResource(this, R.array.majors, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this


        createButton.setOnClickListener {

            firstName = (findViewById<View>(R.id.first_name) as TextView).text.toString()
            lastName = (findViewById<View>(R.id.last_name) as TextView).text.toString()
            school = (findViewById<View>(R.id.school) as TextView).text.toString()
            yearOfGraduation = (findViewById<View>(R.id.year_of_graduation) as TextView).text.toString()
//            selectedDegree = (findViewById<View>(R.id.) as TextView).text.toString()
            favouriteActivities = (findViewById<View>(R.id.favourite_activities) as TextView).text.toString()


            val intent = Intent(this, DisplayActivity::class.java).apply {
               putExtra("first_name", firstName)
                putExtra("last_name", lastName)
                putExtra("school", school)
                putExtra("year_of_graduation", yearOfGraduation)
//                putExtra("selected_degree", selectedDegree)
                putExtra("selected_major", selectedMajor)
                putExtra("favourite_activities", favouriteActivities)
            }
            startActivity(intent)
        }

    }

    fun checkMajor(v: View) {
        Log.d(TAG, "hi")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        selectedMajor = parent?.getItemAtPosition(position).toString()
        Log.d(TAG, selectedMajor)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}