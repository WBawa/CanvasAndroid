package com.riis.zodiacapp

import androidx.lifecycle.ViewModel

class ZodiacListViewModel : ViewModel() {
    private val signRepository = SignRepository.get()
    val signListLiveData = signRepository.getSigns()
}