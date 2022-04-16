package com.example.menuprueba.domain.logros

import com.example.menuprueba.core.Result
import com.example.menuprueba.data.model.logros.infoUsers
import com.example.menuprueba.data.remote.logros.LogrosDataSource


class LogrosRepoImplement(private val repo: LogrosDataSource) : LogrosRepo {
    override suspend fun getInfoUser(): Result<infoUsers> = repo.getInfoUserRepo()
}