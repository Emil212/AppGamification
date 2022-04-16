package com.example.menuprueba.presentation.logros

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.menuprueba.core.Result
import com.example.menuprueba.domain.logros.LogrosRepo
import com.example.menuprueba.domain.logros.LogrosRepoImplement
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import kotlinx.coroutines.tasks.await


class LogrosViewModel(private val repo: LogrosRepo) : ViewModel() {


    fun getInfoUsers() = liveData(Dispatchers.IO) {
        emit(Result.Loading())
        try {
            emit(Result.Success(repo.getInfoUser()))
        } catch (e: Exception) {
            emit(Result.Failure(e))
        }

    }
}

class LogrosViewModelFactory(private val repo: LogrosRepoImplement) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LogrosViewModel(repo) as T
    }
}