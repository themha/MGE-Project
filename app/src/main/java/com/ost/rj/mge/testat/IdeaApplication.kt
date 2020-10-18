package com.ost.rj.mge.testat

import android.app.Application
import androidx.room.Room
import com.ost.rj.mge.testat.model.IdeaRepository
import com.ost.rj.mge.testat.model.storage.IdeaDatabase
import java.io.Console

class IdeaApplication : Application() {

    companion object {
        var ideaRepository: IdeaRepository? = null
    }
    override fun onCreate() {
        System.out.println("Create Repo")
        super.onCreate()
        ideaRepository?.initialize(applicationContext)
    }
}