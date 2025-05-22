package com.example.osuhelper

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.net.toUri

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_standard)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Handle window insets to support edge-to-edge layout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Navigate to Mania home screen
        val maniaButton = findViewById<ImageButton>(R.id.maniaButton)
        maniaButton.setOnClickListener {
            val intent = Intent(this, homeMania::class.java)
            startActivity(intent)
        }

        // Navigate to Catch the Beat (CTB) home screen
        val ctbButton = findViewById<ImageButton>(R.id.ctbButton)
        ctbButton.setOnClickListener {
            val intent = Intent(this, homeCTB::class.java)
            startActivity(intent)
        }

        // Navigate to Taiko home screen
        val taikoButton = findViewById<ImageButton>(R.id.taikoButton)
        taikoButton.setOnClickListener {
            val intent = Intent(this, homeTaiko::class.java)
            startActivity(intent)
        }

        // Navigate to About page
        val aboutLink = findViewById<TextView>(R.id.aboutLink)
        aboutLink.setOnClickListener {
            val intent = Intent(this, aboutPage::class.java)
            startActivity(intent)
        }

        // Navigate to Profile/Details page
        val profileButton = findViewById<ImageButton>(R.id.profileButton)
        profileButton.setOnClickListener {
            val intent = Intent(this, detailsPage::class.java)
            startActivity(intent)
        }

        // Beatmap link buttons for Standard mode
        val link1: ImageButton = findViewById(R.id.link1)
        val link2: ImageButton = findViewById(R.id.link2)
        val link3: ImageButton = findViewById(R.id.link3)
        val link4: ImageButton = findViewById(R.id.link4)
        val link5: ImageButton = findViewById(R.id.link5)
        val link6: ImageButton = findViewById(R.id.link6)

        // Open beatmap links in browser
        link1.setOnClickListener {
            openLink("https://osu.ppy.sh/beatmapsets/320118#osu/714001")
        }

        link2.setOnClickListener {
            openLink("https://osu.ppy.sh/beatmapsets/163112#osu/397535")
        }

        link3.setOnClickListener {
            openLink("https://osu.ppy.sh/beatmapsets/292301#osu/657917")
        }

        link4.setOnClickListener {
            openLink("https://osu.ppy.sh/beatmapsets/39804#osu/126645")
        }

        link5.setOnClickListener {
            openLink("https://osu.ppy.sh/beatmapsets/371128#osu/814293")
        }

        link6.setOnClickListener {
            openLink("https://osu.ppy.sh/beatmapsets/24313#osu/104229")
        }
    }

    // Helper method to open external links using an Intent
    private fun openLink(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
        startActivity(intent)
    }
}