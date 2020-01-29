package com.tidaltestapp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tidaltestapp.R
import com.tidaltestapp.ui.searchArtists.SearchArtistsActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, SearchArtistsActivity::class.java)
        startActivity(intent)
        finish()
    }
}
