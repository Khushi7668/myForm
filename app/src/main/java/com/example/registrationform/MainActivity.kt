package com.example.registrationform

import android.os.Bundle
import android.widget.*
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity(){

    lateinit var firstName: EditText
    lateinit var lastName: EditText
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var age: EditText
    lateinit var bio: EditText

    lateinit var genderGroup: RadioGroup
    lateinit var spinner: Spinner

    lateinit var acrylic: CheckBox
    lateinit var oil: CheckBox
    lateinit var water: CheckBox

    lateinit var submitBtn: Button
    lateinit var resetBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Bind Views
        firstName = findViewById(R.id.firstName)
        lastName = findViewById(R.id.lastName)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        age = findViewById(R.id.age)
        bio = findViewById(R.id.bio)

        genderGroup = findViewById(R.id.genderGroup)
        spinner = findViewById(R.id.spinner)

        acrylic = findViewById(R.id.acrylic)
        oil = findViewById(R.id.oil)
        water = findViewById(R.id.water)

        submitBtn = findViewById(R.id.submitBtn)
        resetBtn = findViewById(R.id.resetBtn)

        // Spinner Data
        val occupations = arrayOf("Student", "Teacher", "Developer", "Other")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, occupations)
        spinner.adapter = adapter

        // Submit Button
        submitBtn.setOnClickListener {

            val selectedGenderId = genderGroup.checkedRadioButtonId
            val gender = if (selectedGenderId != -1) {
                findViewById<RadioButton>(selectedGenderId).text.toString()
            } else "Not Selected"

            val prefs = mutableListOf<String>()
            if (acrylic.isChecked) prefs.add("Acrylic")
            if (oil.isChecked) prefs.add("Oil")
            if (water.isChecked) prefs.add("Water")

            val data = """
                Name: ${firstName.text} ${lastName.text}
                Email: ${email.text}
                Gender: $gender
                Age: ${age.text}
                Preferences: $prefs
                Occupation: ${spinner.selectedItem}
                Bio: ${bio.text}
            """.trimIndent()

            Toast.makeText(this, data, Toast.LENGTH_LONG).show()
        }

        // Reset Button
        resetBtn.setOnClickListener {
            firstName.text.clear()
            lastName.text.clear()
            email.text.clear()
            password.text.clear()
            age.text.clear()
            bio.text.clear()
            genderGroup.clearCheck()

            acrylic.isChecked = false
            oil.isChecked = false
            water.isChecked = false

            spinner.setSelection(0)
        }
    }
}