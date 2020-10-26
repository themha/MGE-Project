package com.ost.rj.mge.testat.services

import android.util.Patterns

object EmailVerificationService {

    fun isValidEmail(email: String?): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}