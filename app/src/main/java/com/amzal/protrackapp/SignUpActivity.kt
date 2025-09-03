package com.amzal.protrackapp

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.amzal.protrackapp.databinding.ActivitySignUpBinding
import java.util.Calendar

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupGenderDropdown()
        setupClickListeners()
    }

    private fun setupGenderDropdown() {
        val genderOptions = arrayOf("Male", "Female", "Other", "Prefer not to say")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, genderOptions)
        binding.actvGender.setAdapter(adapter)
    }

    private fun setupClickListeners() {


        // Date of Birth field click - show date picker
        binding.etDateOfBirth.setOnClickListener {
            showDatePickerDialog()
        }

        binding.tilDateOfBirth.setEndIconOnClickListener {
            showDatePickerDialog()
        }

        // Sign Up button click - navigate to MainActivity
        binding.btnSignUp.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        // Sign In text click - navigate back to SignInActivity
        binding.tvSignIn.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val formattedDate = String.format("%02d / %02d / %d", selectedMonth + 1, selectedDay, selectedYear)
                binding.etDateOfBirth.setText(formattedDate)
            },
            year,
            month,
            day
        )

        // Set maximum date to today (user can't select future dates)
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()

        // Set minimum date to 100 years ago (reasonable age limit)
        calendar.add(Calendar.YEAR, -100)
        datePickerDialog.datePicker.minDate = calendar.timeInMillis

        datePickerDialog.show()
    }


}