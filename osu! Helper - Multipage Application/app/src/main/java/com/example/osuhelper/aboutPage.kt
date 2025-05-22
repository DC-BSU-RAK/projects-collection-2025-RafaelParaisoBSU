package com.example.osuhelper

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class aboutPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_about_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

// Set up "Home" text link to navigate to MainActivity (osu!standard)
        val homeLink = findViewById<TextView>(R.id.homeLink)
        homeLink.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

// Set up mania mode button to navigate to the osu!mania home screen
        val maniaButton = findViewById<ImageButton>(R.id.maniaButton)
        maniaButton.setOnClickListener {
            val intent = Intent(this, homeMania::class.java)
            startActivity(intent)
        }

// Set up standard mode button to navigate to MainActivity (osu!standard)
        val standardButton = findViewById<ImageButton>(R.id.standardButton)
        standardButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

// Set up catch mode button to navigate to the osu!catch (CTB) home screen
        val ctbButton = findViewById<ImageButton>(R.id.ctbButton)
        ctbButton.setOnClickListener {
            val intent = Intent(this, homeCTB::class.java)
            startActivity(intent)
        }

// Set up taiko mode button to navigate to the osu!taiko home screen
        val taikoButton = findViewById<ImageButton>(R.id.taikoButton)
        taikoButton.setOnClickListener {
            val intent = Intent(this, homeTaiko::class.java)
            startActivity(intent)
        }

// Set up profile button to navigate to the user profile/details page
        val profileButton = findViewById<ImageButton>(R.id.profileButton)
        profileButton.setOnClickListener {
            val intent = Intent(this, detailsPage::class.java)
            startActivity(intent)
        }

    }
}