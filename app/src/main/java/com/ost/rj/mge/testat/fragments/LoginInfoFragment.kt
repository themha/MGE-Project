package com.ost.rj.mge.testat.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ost.rj.mge.testat.R

class LoginInfoFragment : Fragment() {

    companion object {
        @JvmStatic
        fun create() = LoginInfoFragment()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_info, container, false)
    }

}