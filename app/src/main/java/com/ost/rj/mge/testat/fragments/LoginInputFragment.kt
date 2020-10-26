package com.ost.rj.mge.testat.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.ost.rj.mge.testat.R
import com.ost.rj.mge.testat.services.EmailVerificationService
import com.ost.rj.mge.testat.services.InputVerificationService


class LoginInputFragment (
    private val onInputChanged: (email : String, password : String, inputsAreValid : Boolean) -> Unit
): Fragment() {

    private val MINIMUM_PASSWORD_LENGTH = 10

    private lateinit var editTextEmail : EditText
    private lateinit var editTextPassword : EditText

    companion object {
        fun create(onInputChanged: (email : String, password : String, inputsAreValid : Boolean) -> Unit) : LoginInputFragment {
            return LoginInputFragment(onInputChanged)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var fragment : View = inflater.inflate(R.layout.fragment_login_input, container, false)

        editTextEmail = fragment.findViewById(R.id.register_edittext_email)
        editTextEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                val email: String = editTextEmail.text.toString()
                val isValidEmail: Boolean = EmailVerificationService.isValidEmail(email)
                if (!isValidEmail) {
                    editTextEmail.error = "Email is invalid!"

                } else {
                    editTextEmail.error = null
                }

                verifyInputs()
            }

        })

        editTextPassword = fragment.findViewById(R.id.register_edittext_password)
        editTextPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val password: String = editTextPassword.text.toString()
                val isValidPassword: Boolean = password.length >= MINIMUM_PASSWORD_LENGTH
                if (!isValidPassword) {
                    editTextPassword.error = "min 10 characters"
                } else {
                    editTextPassword.error = null
                }

                verifyInputs()
            }
        })

        return fragment
    }

    private fun verifyInputs() {
        val email : String = editTextEmail.text.toString()
        val password : String = editTextPassword.text.toString()

        val emailHasError : Boolean = InputVerificationService.hasInvalidInput(editTextEmail)
        val passwordHasError : Boolean = InputVerificationService.hasInvalidInput(editTextPassword)
        val inputsAreValid : Boolean = !emailHasError && !passwordHasError

        onInputChanged(email, password, inputsAreValid)
    }
}