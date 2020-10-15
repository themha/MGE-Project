package com.ost.rj.mge.testat.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ost.rj.mge.testat.R
import java.util.*


class IdeaFormActivity : AppCompatActivity() {
    private lateinit var title : EditText
    private lateinit var tags : EditText
    private lateinit var description : EditText


    companion object {

        private const val REQUEST_CODE_SPEECH_INPUT_TITLE = 100
        private const val REQUEST_CODE_SPEECH_INPUT_TAGS = 101
        private const val REQUEST_CODE_SPEECH_INPUT_DESCRIPTION = 102

        fun createIntent(context: Context) : Intent {
            val intent : Intent = Intent(context, IdeaFormActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_idea_form)

        title = findViewById(R.id.idea_form_editText_title)
        title.setOnTouchListener {view : View, motionEvent -> setDrawableActions(motionEvent, title, REQUEST_CODE_SPEECH_INPUT_TITLE) }

        tags = findViewById(R.id.idea_form_editText_tags)
        tags.setOnTouchListener {view : View, motionEvent -> setDrawableActions(motionEvent, tags, REQUEST_CODE_SPEECH_INPUT_TAGS) }

        description = findViewById(R.id.idea_form_editText_description)
        description.setOnTouchListener {_, motionEvent -> setDrawableActions(motionEvent, description, REQUEST_CODE_SPEECH_INPUT_DESCRIPTION) }
    }


    private fun setDrawableActions(event : MotionEvent, element : TextView, requestCode : Int) : Boolean {
        val DRAWABLE_LEFT = 0
        val DRAWABLE_TOP = 1
        val DRAWABLE_RIGHT = 2
        val DRAWABLE_BOTTOM = 3

        if (event.action == MotionEvent.ACTION_DOWN) {
            if (event.getRawX() >= element.right - element.compoundDrawables[DRAWABLE_RIGHT].bounds.width()) {
                val intent = startSpeechToTextIntent()
                startActivityForResult(intent, requestCode)
                return true;
            }
        }
        return false;
    }


    private fun startSpeechToTextIntent() : Intent {
        val speechRecognizerIntent : Intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH); // starts an activity that will prompt the user for speech and send it through a speech recognizer
        speechRecognizerIntent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Let's start speaking")
        return speechRecognizerIntent
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){
            REQUEST_CODE_SPEECH_INPUT_TITLE -> {
                onActivityResultSpeechToTextHandler(title, resultCode, data)
            }

            REQUEST_CODE_SPEECH_INPUT_TAGS -> {
                onActivityResultSpeechToTextHandler(tags, resultCode, data)
            }

            REQUEST_CODE_SPEECH_INPUT_DESCRIPTION -> {
                onActivityResultSpeechToTextHandler(description, resultCode, data)
            }
        }
    }

    private fun onActivityResultSpeechToTextHandler(element: TextView, resultCode : Int, data: Intent?){
        if (resultCode == Activity.RESULT_OK && data != null){
            val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            element.append(result!![0])

        }
    }
}