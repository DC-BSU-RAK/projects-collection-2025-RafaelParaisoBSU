package com.example.osuhelper

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class detailsPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_details_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

// Retrieve stored data from SharedPreferences
        val prefs = getSharedPreferences("login_prefs", MODE_PRIVATE)
        val username = prefs.getString("username", "") ?: ""
        val email = prefs.getString("email", "") ?: ""

        // Display the username and email in their respective TextViews
        val usernameText = findViewById<TextView>(R.id.usernameText)
        usernameText.text = username

        val emailText = findViewById<TextView>(R.id.emailText)
        emailText.text = email

        // Set up the "About" link to navigate to AboutPage
        val aboutLink = findViewById<TextView>(R.id.aboutLink)
        aboutLink.setOnClickListener {
            val intent = Intent(this, aboutPage::class.java)
            startActivity(intent)
        }

        // Set up the "Home" link to navigate to MainActivity
        val homeLink = findViewById<TextView>(R.id.homeLink)
        homeLink.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}