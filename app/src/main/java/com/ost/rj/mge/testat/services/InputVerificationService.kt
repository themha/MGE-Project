package com.ost.rj.mge.testat.services

import android.widget.EditText

object InputVerificationService {

    fun hasInvalidInput(editText: EditText) : Boolean{
        if (editText != null) {
            return editText.text.isEmpty() || editText.error != null
        }

        return true
    }
}