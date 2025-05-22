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

class homeMania : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_mania)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

// Set up button to navigate to osu!standard (MainActivity)
        val standardButton = findViewById<ImageButton>(R.id.standardButton)
        standardButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

// Set up button to navigate to osu!catch (CTB) section
        val ctbButton = findViewById<ImageButton>(R.id.ctbButton)
        ctbButton.setOnClickListener {
            val intent = Intent(this, homeCTB::class.java)
            startActivity(intent)
        }

// Set up button to navigate to osu!taiko section
        val taikoButton = findViewById<ImageButton>(R.id.taikoButton)
        taikoButton.setOnClickListener {
            val intent = Intent(this, homeTaiko::class.java)
            startActivity(intent)
        }

// Set up text link to open the About page
        val aboutLink = findViewById<TextView>(R.id.aboutLink)
        aboutLink.setOnClickListener {
            val intent = Intent(this, aboutPage::class.java)
            startActivity(intent)
        }

// Set up profile button to open the user details page
        val profileButton = findViewById<ImageButton>(R.id.profileButton)
        profileButton.setOnClickListener {
            val intent = Intent(this, detailsPage::class.java)
            startActivity(intent)
        }

// Initialize beatmap link buttons for osu!mania maps
        val link1: ImageButton = findViewById(R.id.link1)
        val link2: ImageButton = findViewById(R.id.link2)
        val link3: ImageButton = findViewById(R.id.link3)
        val link4: ImageButton = findViewById(R.id.link4)
        val link5: ImageButton = findViewById(R.id.link5)
        val link6: ImageButton = findViewById(R.id.link6)

// Set beatmap links to open in browser when clicked
        link1.setOnClickListener {
            openLink("https://osu.ppy.sh/beatmapsets/241526#mania/557819")
        }

        link2.setOnClickListener {
            openLink("https://osu.ppy.sh/beatmapsets/476691#mania/1018238")
        }

        link3.setOnClickListener {
            openLink("https://osu.ppy.sh/beatmapsets/173612#mania/502132")
        }

        link4.setOnClickListener {
            openLink("https://osu.ppy.sh/beatmapsets/409025#mania/888023")
        }

        link5.setOnClickListener {
            openLink("https://osu.ppy.sh/beatmapsets/310793#mania/694475")
        }

        link6.setOnClickListener {
            openLink("https://osu.ppy.sh/beatmapsets/1430064#mania/2944886")
        }
    }

    // Helper function to open a URL in the browser
    private fun openLink(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
        startActivity(intent)
    }
}