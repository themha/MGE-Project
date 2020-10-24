package com.ost.rj.mge.testat.model

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.ost.rj.mge.testat.model.storage.IdeaDatabase
import com.ost.rj.mge.testat.services.RetrofitBuilder
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response


object IdeaRepository {
    private lateinit var database: IdeaDatabase


    fun initialize(context: Context) {
        database = Room.databaseBuilder(
            context,
            IdeaDatabase::class.java, "idea.db"
        ).allowMainThreadQueries().build()


        /*
        if(getIdeas().isEmpty()){
            Log.d("Test1234", "nothing here")
            addIdea("Jetski Verleihung", "#event #adventure #experience", "Very Cool")
            addIdea("Facebook 2.0", "#socialNetwork", "Give us your Money")
        }

         */

    }

    fun getIdeas(): LiveData<List<Idea>> {
        return database.ideaDao().getIdeas()

    }

    private fun addIdea(idea: Idea) {
        postIdeaToRemoteServer(idea)

    }

    fun addIdea(title: String, tags: String, description: String): Idea {
        val idea = Idea(title, tags, description)

        addIdea(idea)

        return idea
    }

    fun syncDatabase(){
        GlobalScope.launch {
            try {
                loadIdeasFromRemoteServerIntoLocalDatabase()
            } catch (e : Exception){
                Log.d("Test12345", e.toString())
            }
        }

    }

/*
    fun syncDatabase(handleFinish: () -> Unit){
        GlobalScope.launch {
            try {
                loadIdeasFromRemoteServerIntoLocalDatabase()



                val sync = Runnable {
                    Log.d("test1234", "test1")
                }

                Log.d("test1234", "test2")
                handleFinish()
            } catch (e: Exception) {
                Log.d("Test12345", e.toString())
            }
        }


    }
    
 */


    private fun addIdeaToLocalDatabase(idea: Idea) {
        database.ideaDao().insert(idea)
    }

    private fun loadIdeasFromRemoteServerIntoLocalDatabase() {
        GlobalScope.launch { // replace with viewModelScope
            try {

                var response = RetrofitBuilder.retrofitService.getItems()
                if (response.isSuccessful && response.body() != null){
                    eraseLocalDatabase()

                    var map : HashMap<String, Idea> = response.body()!!
                    map.forEach{ (key, idea) -> run {
                        addIdeaToLocalDatabase(idea)
                    }}
                }
                Log.d("test1234", "in thread")

            } catch (e: Exception) {
               Log.d("Test12345", e.toString())
            }

        }

    }

    private fun postIdeaToRemoteServer(idea : Idea){
        GlobalScope.launch { // replace with viewModelScope
            try {
                RetrofitBuilder.retrofitService.createIdea(idea)

            } catch (e: Exception) {
                Log.d("Test12345", e.toString())
            }
        }
    }

    private fun eraseLocalDatabase(){
        database.ideaDao().eraseDb()
    }
}