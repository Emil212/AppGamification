package com.example.menuprueba.core

//Son los tres posibles estados que tienen las peticiones la servidor

sealed class Result <out T>{
    class Loading<out T>:Result<T>()
    data class Success<out T>(val data: T): Result<T>()
    data class Failure(val exception: Exception) : Result<Nothing>()
}