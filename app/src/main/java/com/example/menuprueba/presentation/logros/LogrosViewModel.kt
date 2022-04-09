package com.example.menuprueba.presentation.logros

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.menuprueba.core.Result
import com.example.menuprueba.domain.logros.LogrosRepo
import kotlinx.coroutines.Dispatchers

class LogrosViewModel (repo: LogrosRepo): ViewModel() {

    val getInfoUser = liveData (Dispatchers.IO) {
        emit(Result.Loading())
        try {
            val info = repo.getInfoUser()
            emit(info)
        }catch (e: Exception){
            emit(Result.Failure(e))
        }
    }

}