package com.riis.geoquiz

import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"

class QuizViewModel : ViewModel() {
    var answers = HashMap<Question, Boolean>()
    private var rightAnswers: Float = 0f
    var isCheater = false

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    var currentIndex = -1

    val currentQuestionAnswer: Boolean get() = questionBank[currentIndex].answer
    val currentQuestionText: Int get() = questionBank[currentIndex].textResId
    val bankSize: Int get() = questionBank.size
    val currentQuestion: Question get() = questionBank[currentIndex]
    val currentAnswers: HashMap<Question, Boolean> get() = answers
    val getRightAnswers: Float get() = rightAnswers

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun moveToPrev() {
        currentIndex--

        if (currentIndex < 0) {
            currentIndex += questionBank.size
        }
    }

    fun addRightAnswer() {
        rightAnswers++
    }
}