package com.onirutla.metalgearcharacter.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.onirutla.metalgearcharacter.data.metalGearCharacters

class HomeViewModel : ViewModel() {
    val characters = liveData {
        emit(metalGearCharacters)
    }
}