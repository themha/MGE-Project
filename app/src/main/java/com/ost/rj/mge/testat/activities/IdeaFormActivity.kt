package com.ost.rj.mge.testat.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.ost.rj.mge.testat.R
import com.ost.rj.mge.testat.model.IdeaRepository
import com.ost.rj.mge.testat.services.EmailVerificationService
import com.ost.rj.mge.testat.services.InputVerificationService
import java.util.*


class IdeaFormActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null

    private lateinit var editTextTitle: EditText
    private lateinit var editTextTags: EditText
    private lateinit var editTextDescription: EditText
    private lateinit var saveButton: Button


    companion object {

        private const val REQUEST_CODE_SPEECH_INPUT_TITLE = 100
        private const val REQUEST_CODE_SPEECH_INPUT_TAGS = 101
        private const val REQUEST_CODE_SPEECH_INPUT_DESCRIPTION = 102

        fun createIntent(context: Context): Intent {
            return Intent(context, IdeaFormActivity::class.java)

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_idea_form)
        mAuth = FirebaseAuth.getInstance()

        saveButton = findViewById(R.id.idea_form_button_save)
        saveButton.isEnabled = false



        editTextTitle = findViewById(R.id.idea_form_editText_title)
        editTextTitle.setOnTouchListener { view: View, motionEvent ->
            setDrawableActions(
                motionEvent,
                editTextTitle,
                REQUEST_CODE_SPEECH_INPUT_TITLE
            )
        }
        editTextTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                verifyInputs()
            }

        })


        editTextTags = findViewById(R.id.idea_form_editText_tags)
        editTextTags.setOnTouchListener { view: View, motionEvent ->
            setDrawableActions(
                motionEvent,
                editTextTags,
                REQUEST_CODE_SPEECH_INPUT_TAGS
            )
        }
        editTextTags.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                verifyInputs()
            }

        })

        editTextDescription = findViewById(R.id.idea_form_editText_description)
        editTextDescription.setOnTouchListener { _, motionEvent ->
            setDrawableActions(
                motionEvent,
                editTextDescription,
                REQUEST_CODE_SPEECH_INPUT_DESCRIPTION
            )
        }
        editTextDescription.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                verifyInputs()
            }

        })

        saveButton = findViewById(R.id.idea_form_button_save)
        saveButton.setOnClickListener {

            //TODO Input check
            verifyInputs()

            val user = mAuth!!.currentUser
            if(user != null){
                IdeaRepository.addIdea(
                    editTextTitle.text.toString(),
                    editTextTags.text.toString(),
                    editTextDescription.text.toString(),
                    user!!.uid
                )

                finish()
            }else {

                // TODO open login screen
                Toast.makeText(this, "Please log in first to publish own ideas", Toast.LENGTH_LONG).show()
                finish()
            }

        }

        val actionBar = getSupportActionBar()
        actionBar?.setDisplayHomeAsUpEnabled(true)

    }


    private fun setDrawableActions(
        event: MotionEvent,
        element: TextView,
        requestCode: Int
    ): Boolean {
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


    private fun startSpeechToTextIntent(): Intent {
        val speechRecognizerIntent: Intent =
            Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH); // starts an activity that will prompt the user for speech and send it through a speech recognizer
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

        when (requestCode) {
            REQUEST_CODE_SPEECH_INPUT_TITLE -> {
                onActivityResultSpeechToTextHandler(editTextTitle, resultCode, data)
            }

            REQUEST_CODE_SPEECH_INPUT_TAGS -> {
                onActivityResultSpeechToTextHandler(editTextTags, resultCode, data)
            }

            REQUEST_CODE_SPEECH_INPUT_DESCRIPTION -> {
                onActivityResultSpeechToTextHandler(editTextDescription, resultCode, data)
            }
        }
    }

    private fun onActivityResultSpeechToTextHandler(
        element: TextView,
        resultCode: Int,
        data: Intent?
    ) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            element.append(result!![0])

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()

        return true
    }

    private fun verifyInputs() {
        val titleHasError : Boolean = InputVerificationService.hasInvalidInput(editTextTitle)
        val tagsHasError : Boolean = InputVerificationService.hasInvalidInput(editTextTags)
        val descriptionHasError : Boolean = InputVerificationService.hasInvalidInput(editTextDescription)
        val inputsAreValid : Boolean = !titleHasError && !tagsHasError && !descriptionHasError

        updateButtonAvailability(inputsAreValid)
    }

    private fun updateButtonAvailability(inputsAreValid : Boolean){
        saveButton.isEnabled = inputsAreValid

    }
}