package com.example.menuprueba.domain.logros

import com.example.menuprueba.core.Result
import com.example.menuprueba.data.model.logros.infoUsers


class LogrosRepoImplement(private val repo : IRepo) : LogrosRepo {
    override suspend fun getInfoUser(): Result<MutableList<*>> = repo.getInfoUserRepo()
}