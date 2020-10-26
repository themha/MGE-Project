package com.ost.rj.mge.testat.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.ost.rj.mge.testat.R

class LoginSubmitFragment (
    private val onButtonClicked : (login: Boolean) -> Unit,
) : Fragment() {



    private lateinit var buttonLogin: Button
    private lateinit var buttonRegister: Button

    companion object {
        @JvmStatic
        fun create(onButtonClicked : (login: Boolean) -> Unit) =  LoginSubmitFragment(onButtonClicked)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val fragment : View = inflater.inflate(R.layout.fragment_login_submit, container, false)

        buttonLogin = fragment.findViewById(R.id.buttonLogin)
        buttonLogin.isEnabled = false
        buttonLogin.setOnClickListener {
            onButtonClicked(true)
        }

        buttonRegister = fragment.findViewById(R.id.buttonVerifyEmail)
        buttonRegister.setOnClickListener {
            onButtonClicked(false)
        }
        return fragment
    }


    fun updateButtonAvailability(inputsAreValid : Boolean){
        buttonLogin.isEnabled = inputsAreValid
    }
}