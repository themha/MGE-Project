package com.ost.rj.mge.testat.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.ost.rj.mge.testat.R
import com.ost.rj.mge.testat.activities.FeedActivity

class LoginPage : AppCompatActivity() {

    companion object {

        fun createIntent(context: Context) : Intent {
            val intent : Intent = Intent(context, LoginPage::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        val feedButton : Button = findViewById(R.id.feedbutton)
        feedButton.setOnClickListener {
            val feedActivityIntent : Intent = FeedActivity.createIntent(this)
            startActivity(feedActivityIntent)
        }
    }


}