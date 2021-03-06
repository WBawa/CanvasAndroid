package com.riis.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders

private const val EXTRA_ANSWER_IS_TRUE = "com.riis.geoquiz.answer_is_true"
const val EXTRA_ANSWER_SHOWN = "com.riis.geoquiz.answer_shown"
private const val TAG = "CheatActivity"

class CheatActivity : AppCompatActivity() {

    private lateinit var answerTextView: TextView
    private lateinit var showAnswerButton: Button



    private val cheatViewModel: CheatViewModel by lazy {
        ViewModelProviders.of(this).get(CheatViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        val value = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)
        cheatViewModel.answerIsTrue = value

        answerTextView = findViewById(R.id.answer_text_view)
        showAnswerButton = findViewById(R.id.show_answer_button)

        if (cheatViewModel.buttonPressed) {
            showAnswer()
        }

        showAnswerButton.setOnClickListener {
            showAnswer()
            cheatViewModel.buttonPressed = true
        }
    }

    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean) : Intent {
            return Intent(packageContext, CheatActivity::class.java).apply{
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }

    private fun setAnswerShownResult(isAnswerShown: Boolean) {
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        }
        setResult(Activity.RESULT_OK, data)
    }

    private fun showAnswer() {
        val answerText = when {
            cheatViewModel.answerIsTrue -> R.string.true_button
            else -> R.string.false_button
        }

        answerTextView.setText(answerText)
        setAnswerShownResult(true)
    }
}