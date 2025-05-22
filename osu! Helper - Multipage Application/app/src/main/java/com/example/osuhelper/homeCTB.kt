package com.example.osuhelper

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class homeCTB : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_ctb)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

// Set up button to open the osu!mania section
        val maniaButton = findViewById<ImageButton>(R.id.maniaButton)
        maniaButton.setOnClickListener {
            val intent = Intent(this, homeMania::class.java)
            startActivity(intent)
        }

// Set up button to return to osu!standard (MainActivity)
        val standardButton = findViewById<ImageButton>(R.id.standardButton)
        standardButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

// Set up button to open the osu!taiko section
        val taikoButton = findViewById<ImageButton>(R.id.taikoButton)
        taikoButton.setOnClickListener {
            val intent = Intent(this, homeTaiko::class.java)
            startActivity(intent)
        }

// Set up the "About" text link to navigate to the About page
        val aboutLink = findViewById<TextView>(R.id.aboutLink)
        aboutLink.setOnClickListener {
            val intent = Intent(this, aboutPage::class.java)
            startActivity(intent)
        }

// Set up the profile button to navigate to the user details/profile page
        val profileButton = findViewById<ImageButton>(R.id.profileButton)
        profileButton.setOnClickListener {
            val intent = Intent(this, detailsPage::class.java)
            startActivity(intent)
        }

// Find and assign ImageButtons for opening specific osu! beatmap links
        val link1: ImageButton = findViewById(R.id.link1)
        val link2: ImageButton = findViewById(R.id.link2)
        val link3: ImageButton = findViewById(R.id.link3)
        val link4: ImageButton = findViewById(R.id.link4)
        val link5: ImageButton = findViewById(R.id.link5)
        val link6: ImageButton = findViewById(R.id.link6)

// Set click listeners on each button to open the corresponding osu! beatmap URL
        link1.setOnClickListener {
            openLink("https://osu.ppy.sh/beatmapsets/765778#fruits/1612604")
        }

        link2.setOnClickListener {
            openLink("https://osu.ppy.sh/beatmapsets/703957#fruits/1532487")
        }

        link3.setOnClickListener {
            openLink("https://osu.ppy.sh/beatmapsets/305281#fruits/683782")
        }

        link4.setOnClickListener {
            openLink("https://osu.ppy.sh/beatmapsets/1179938#fruits/2460432")
        }

        link5.setOnClickListener {
            openLink("https://osu.ppy.sh/beatmapsets/536749#fruits/1137696")
        }

        link6.setOnClickListener {
            openLink("https://osu.ppy.sh/beatmapsets/542081#fruits/1207082")
        }
    }

    // Helper function to open a URL in the user's default browser
    private fun openLink(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
        startActivity(intent)
    }
}