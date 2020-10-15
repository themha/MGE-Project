package com.ost.rj.mge.testat.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.ost.rj.mge.testat.R
import com.ost.rj.mge.testat.activities.FeedActivity


class LoginPage : AppCompatActivity() {

    companion object {

        fun createIntent(context: Context) : Intent {
            val intent : Intent = Intent(context, LoginPage::class.java)
            return intent
        }
    }

    private val MINIMUM_PASSWORD_LENGTH = 10
    private val FULL_VISIBLE_ALPHA = 1.0f
    private val HALF_VISIBLE_ALPHA = 0.5f


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        val emailEditText: EditText = findViewById(R.id.editTextTextPersonName)
        val passwordEditText : EditText = findViewById(R.id.editTextTextPassword);
        val feedButton : Button = findViewById(R.id.feedbutton)
        feedButton.setOnClickListener {
            val feedActivityIntent : Intent = FeedActivity.createIntent(this)
            startActivity(feedActivityIntent)
        }
        fun isValidEmail(email: String?): Boolean {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }
        fun hasInvalidInput(editText: EditText): Boolean {
            return editText.text.length == 0 || editText.error != null
        }
        fun updateLoginButton() {
            val emailHasError = hasInvalidInput(emailEditText);
            val passwordHasError = hasInvalidInput(passwordEditText);
            val buttonIsEnabled = !emailHasError && !passwordHasError;
            val buttonAlpha = if (buttonIsEnabled)  FULL_VISIBLE_ALPHA else HALF_VISIBLE_ALPHA;

            feedButton.setEnabled(buttonIsEnabled);
            feedButton.setAlpha(buttonAlpha);
        }

        val textWatcher = object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val email = emailEditText.getText().toString();
                val isValidEmail = isValidEmail(email);

                if (!isValidEmail) {
                    emailEditText.setError("Invalid address");
                }  else {
                    emailEditText.setError(null);
                }
                updateLoginButton();
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        }

        emailEditText.addTextChangedListener(textWatcher);

        val passwordWatcher = object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (passwordEditText.length() < MINIMUM_PASSWORD_LENGTH) {
                    passwordEditText.setError("Password to short");
                } else {
                    passwordEditText.setError(null);
                }

                updateLoginButton();
            }
        }
        passwordEditText.addTextChangedListener(passwordWatcher);
        updateLoginButton();



    }

}