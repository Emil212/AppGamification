package com.example.menuprueba.core

//Son los tres posibles estados que tienen las peticiones la servidor

sealed class Resource <out T>{
    class Loading<out T>:Resource<T>()
    data class Success<out T>(val data: T): Resource<T>()
    data class Failure(val exception: Exception) : Resource<Nothing>()
}