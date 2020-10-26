package com.ost.rj.mge.testat.services

import android.content.Context
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.ost.rj.mge.testat.model.IdeaRepository
import com.ost.rj.mge.testat.model.storage.IdeaDatabase

object UserAuthentication {

    private lateinit var auth: FirebaseAuth

    fun initialize(context: Context) {
        auth = FirebaseAuth.getInstance()

    }


    fun getCurrentUser() : FirebaseUser? {
        if (auth.currentUser != null) {
            return auth.currentUser
        } else {
            return null
        }

    }
}