package com.ost.rj.mge.testat.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.ost.rj.mge.testat.R

class MainActivity : AppCompatActivity() {

    //private lateinit var feedTestButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val feedTestButton : Button = findViewById<Button>(R.id.button_test_to_feed)
        feedTestButton.setOnClickListener {
            buildFeedActivity()
        }
    }

    private fun buildFeedActivity(){
        val feedActivityIntent : Intent = FeedActivity.createIntent(this)
        startActivity(feedActivityIntent)
    }


}