package com.example.menuprueba.domain.logros

import com.example.menuprueba.core.Result
import com.example.menuprueba.data.model.logros.infoUsers


interface IRepo {
    suspend fun getInfoUserRepo(): Result<MutableList<*>>
}