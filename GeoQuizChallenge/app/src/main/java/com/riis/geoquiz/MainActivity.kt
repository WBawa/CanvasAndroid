package com.riis.geoquiz

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders

private const val TAG = "MainActivity"
private const val KEY_INDEX = "index"
private const val ANSWERS_KEY = "answers"
private const val REQUEST_CODE_CHEAT = 0

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var prevButton: ImageButton
    private lateinit var questionTextView: TextView
    private lateinit var cheatButton: Button

    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProviders.of(this).get(QuizViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "oncreate(Bundle?) called")
        setContentView(R.layout.activity_main)

        val currentIndex = savedInstanceState?.getInt(KEY_INDEX, 0) ?: -1
        quizViewModel.currentIndex = currentIndex

        val currentAnswers = savedInstanceState?.getSerializable(ANSWERS_KEY) ?: quizViewModel.answers
        quizViewModel.answers = currentAnswers as HashMap<Question, Boolean>

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        questionTextView = findViewById(R.id.question_text_view)
        prevButton = findViewById(R.id.prev_button)
        cheatButton = findViewById(R.id.cheat_button)

        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        nextQuestion()

        nextButton.setOnClickListener {
            nextQuestion()
        }

        questionTextView.setOnClickListener {
            nextQuestion()
        }

        prevButton.setOnClickListener {
            prevQuestion()
        }

        cheatButton.setOnClickListener {
            val answerIsTrue = quizViewModel.currentQuestionAnswer
            val intent = CheatActivity.newIntent(this@MainActivity, answerIsTrue)
            startActivityForResult(intent, REQUEST_CODE_CHEAT);
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != Activity.RESULT_OK ) {
            return
        }

        if (requestCode == REQUEST_CODE_CHEAT) {
            quizViewModel.isCheater = data?.getBooleanExtra(EXTRA_ANSWER_SHOWN, false) ?: false
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
        Log.d(TAG, quizViewModel.currentIndex.toString())
        Log.d(TAG, "ONRESUME: " + quizViewModel.currentAnswers.toString())
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        Log.d(TAG, "ONDESTROY: " + quizViewModel.currentAnswers.toString())
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        Log.i(TAG, "onSaveInstanceState")
        savedInstanceState.putInt(KEY_INDEX, quizViewModel.currentIndex - 1)
        savedInstanceState.putSerializable(ANSWERS_KEY, quizViewModel.currentAnswers)
    }

    private fun nextQuestion() {
        quizViewModel.moveToNext()
        updateQuestion()
    }

    private fun prevQuestion() {
        quizViewModel.moveToPrev()
        updateQuestion()
    }

    private fun updateQuestion() {
        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)

        checkIfAnswered()
    }

    private fun checkAnswer(userAnswer: Boolean) {

        val correctAnswer = quizViewModel.currentQuestionAnswer
        Log.d(TAG, quizViewModel.isCheater.toString())
        val messageResId = when {
            quizViewModel.isCheater -> R.string.judgment_toast
            userAnswer == correctAnswer -> R.string.correct_toast
            else -> R.string.incorrect_toast
        }

        if (userAnswer == quizViewModel.currentQuestionAnswer) {
            quizViewModel.addRightAnswer()
        }

        quizViewModel.currentAnswers[quizViewModel.currentQuestion] = userAnswer

        disableButtons()

        val toast = Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.TOP, 0, 300)
        toast.show()

        if (quizViewModel.currentAnswers.size == quizViewModel.bankSize) {
            val grade: Float = quizViewModel.getRightAnswers / quizViewModel.bankSize * 100
            val toast = Toast.makeText(this, "Final Grade: $grade", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.TOP, 0, 300)
            toast.show()
        }

    }

    private fun disableButtons() {
        trueButton.isClickable = false
        trueButton.isEnabled = false

        falseButton.isClickable = false
        falseButton.isEnabled = false
    }

    private fun enableButtons() {
        trueButton.isClickable = true
        trueButton.isEnabled = true

        falseButton.isClickable = true
        falseButton.isEnabled = true
    }

    private fun checkIfAnswered() {
        if (quizViewModel.currentQuestion in quizViewModel.currentAnswers.keys) {
            disableButtons()
            return
        }
        enableButtons()
        Log.d(TAG, "got here")
    }
}