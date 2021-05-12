package com.riis.geoquiz

import androidx.annotation.StringRes
import java.io.Serializable

data class Question(@StringRes val textResId: Int, val answer: Boolean) : Serializable