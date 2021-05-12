package com.riis.geoquiz

import androidx.lifecycle.ViewModel

class CheatViewModel : ViewModel() {

    private var answerIsTrue = false

    val isAnswerTrue: Boolean get() = answerIsTrue

    fun setAnswerIsTrue(value: Boolean) {
        answerIsTrue = value
    }
}