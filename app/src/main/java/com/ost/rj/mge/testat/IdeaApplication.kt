package com.ost.rj.mge.testat

import android.app.Application
import com.ost.rj.mge.testat.model.IdeaRepository

class IdeaApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        IdeaRepository.initialize(applicationContext)
    }
}