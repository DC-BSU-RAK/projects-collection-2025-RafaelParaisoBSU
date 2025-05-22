package com.example.osuhelper

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class LoginPageAct : AppCompatActivity() {

    // Declare UI elements and SharedPreferences
    private lateinit var userField: EditText
    private lateinit var passField: EditText
    private lateinit var emailField: EditText
    private lateinit var signInButton: ImageButton
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        // Initialize SharedPreferences for storing login data
        prefs = getSharedPreferences("login_prefs", MODE_PRIVATE)

        // Link UI elements with their corresponding views
        userField = findViewById(R.id.userField)
        emailField = findViewById(R.id.emailField)
        passField = findViewById(R.id.passField)
        signInButton = findViewById(R.id.signinButton)

        // Handle sign-in button click
        signInButton.setOnClickListener {
            val username = userField.text.toString()
            val email = emailField.text.toString()
            val password = passField.text.toString()

            // Check if both fields are not empty
            if (username.isNotBlank() && password.isNotBlank()) {
                // Save username, email and password to SharedPreferences
                prefs.edit()
                    .putString("username", username)
                    .putString("email", email)
                    .putString("password", password)
                    .apply()

                // Navigate to MainActivity with username passed as extra
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("USERNAME_KEY", username)
                startActivity(intent)
                finish() // Close login page so user can't go back to it
            }
        }
    }
}
