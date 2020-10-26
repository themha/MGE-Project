package com.ost.rj.mge.testat.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.ost.rj.mge.testat.R
import com.ost.rj.mge.testat.fragments.*
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null

    private lateinit var inputFragment : LoginInputFragment
    private lateinit var submitFragment : LoginSubmitFragment
    private lateinit var infoFragment : LoginInfoFragment
    private lateinit var registerInputFragment : RegisterInputFragment
    private lateinit var registerSubmitFragment : RegisterSubmitFragment

    private lateinit var currentEmail : String
    private lateinit var currentPassword : String

    override fun onCreate(savedInstanceState: Bundle?) {
        mAuth = FirebaseAuth.getInstance()
        updateUI(mAuth!!.currentUser)

        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        setContentView(R.layout.activity_main)

        inputFragment = LoginInputFragment.create(::onInputChanged)
        submitFragment = LoginSubmitFragment.create(::signIn)
        infoFragment = LoginInfoFragment.create()
        registerInputFragment = RegisterInputFragment.create(::onInputChanged)
        registerSubmitFragment = RegisterSubmitFragment.create(::createAccount)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.info_fragment_container, infoFragment)
            .add(R.id.input_fragment_container, inputFragment)
            .add(R.id.submit_fragment_container, submitFragment)
            .commit()
    }

    private fun onRegisterClicked(){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.input_fragment_container, registerInputFragment)
            .replace(R.id.submit_fragment_container, registerSubmitFragment)
            .commit()

        val actionBar = getSupportActionBar()
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }
    override fun onSupportNavigateUp(): Boolean {
        return showLogin()
    }

    override fun onBackPressed() {
        showLogin()
    }

    private fun showLogin(): Boolean {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.input_fragment_container, inputFragment)
            .replace(R.id.submit_fragment_container, submitFragment)
            .commit()
        val actionBar = getSupportActionBar()
        actionBar?.setDisplayHomeAsUpEnabled(false)
        return true
    }


    private fun onInputChanged(email: String, password: String, inputsAreValid : Boolean){
        currentEmail = email
        currentPassword = password
        submitFragment.updateButtonAvailability(inputsAreValid)
        registerSubmitFragment.updateButtonAvailability(inputsAreValid)

    }

    private fun signIn(login: Boolean) {

        if(login) {
            mAuth!!.signInWithEmailAndPassword(currentEmail, currentPassword)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = mAuth!!.getCurrentUser()
                        updateUI(user)
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Password or User wrong!",
                            Toast.LENGTH_SHORT
                        ).show()
                        updateUI(null)
                    }
                }
        } else {
            onRegisterClicked()
        }
    }

    private fun createAccount() {
        mAuth!!.createUserWithEmailAndPassword(currentEmail, currentPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = mAuth!!.currentUser
                    sendVerificationEmail(user)
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Could not create User!!",
                        Toast.LENGTH_SHORT
                    ).show()
                    showLogin()
                }
            }
    }


    private fun sendVerificationEmail(user: FirebaseUser?) {
    user!!.sendEmailVerification()
        .addOnCompleteListener() { task ->
            if (task.isSuccessful) {
                Toast.makeText(
                    applicationContext,
                    "Successful: Verification email sent!",
                    Toast.LENGTH_SHORT
                ).show()
                updateUI(user)
            } else {
                Toast.makeText(
                    applicationContext,
                    "Verification couldn't be sent!",
                    Toast.LENGTH_SHORT
                ).show()
                showLogin()
            }
        }
    }

    private fun buildFeedActivity(){
        val feedActivityIntent : Intent = FeedActivity.createIntent(this)
        startActivity(feedActivityIntent)
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            buildFeedActivity()
        }
    }

}