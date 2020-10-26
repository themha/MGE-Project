package com.ost.rj.mge.testat.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.ost.rj.mge.testat.R

class RegisterSubmitFragment (
    private val onButtonClicked : () -> Unit,
) : Fragment() {



    private var buttonCreateAccount: Button? = null;

    companion object {
        @JvmStatic
        fun create(onButtonClicked : () -> Unit) =  RegisterSubmitFragment(onButtonClicked)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val fragment : View = inflater.inflate(R.layout.fragment_register_submit, container, false)

        buttonCreateAccount = fragment.findViewById(R.id.buttonCreateAccount)
        buttonCreateAccount?.isEnabled = false
        buttonCreateAccount?.setOnClickListener {
            onButtonClicked()
        }
        return fragment
    }

    fun updateButtonAvailability(inputsAreValid : Boolean){
        buttonCreateAccount?.isEnabled = inputsAreValid
    }

}