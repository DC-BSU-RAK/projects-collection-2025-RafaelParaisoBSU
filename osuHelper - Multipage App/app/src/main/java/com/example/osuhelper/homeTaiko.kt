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

class homeTaiko : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_taiko)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
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

// Set up text link to navigate to the About page
        val aboutLink = findViewById<TextView>(R.id.aboutLink)
        aboutLink.setOnClickListener {
            val intent = Intent(this, aboutPage::class.java)
            startActivity(intent)
        }

// Set up profile button to navigate to the user details/profile page
        val profileButton = findViewById<ImageButton>(R.id.profileButton)
        profileButton.setOnClickListener {
            val intent = Intent(this, detailsPage::class.java)
            startActivity(intent)
        }

// Initialize image buttons for Taiko beatmap links
        val link1: ImageButton = findViewById(R.id.link1)
        val link2: ImageButton = findViewById(R.id.link2)
        val link3: ImageButton = findViewById(R.id.link3)
        val link4: ImageButton = findViewById(R.id.link4)
        val link5: ImageButton = findViewById(R.id.link5)
        val link6: ImageButton = findViewById(R.id.link6)

// Set up click listeners to open osu!taiko beatmap URLs
        link1.setOnClickListener {
            openLink("https://osu.ppy.sh/beatmapsets/41823#taiko/132889")
        }

        link2.setOnClickListener {
            openLink("https://osu.ppy.sh/beatmapsets/1174505#taiko/2452378")
        }

        link3.setOnClickListener {
            openLink("https://osu.ppy.sh/beatmapsets/76623#taiko/217255")
        }

        link4.setOnClickListener {
            openLink("https://osu.ppy.sh/beatmapsets/552712#taiko/1223880")
        }

        link5.setOnClickListener {
            openLink("https://osu.ppy.sh/beatmapsets/82912#taiko/235751")
        }

        link6.setOnClickListener {
            openLink("https://osu.ppy.sh/beatmapsets/1154585#taiko/2409641")
        }
    }

    // Helper function to open a URL in the default browser
    private fun openLink(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
        startActivity(intent)
    }
}