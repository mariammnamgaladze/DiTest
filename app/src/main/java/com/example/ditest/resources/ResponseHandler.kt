package com.example.ditest.resources


sealed class ResponseHandler<T>(val isLoading: Boolean) {
    class Success<T>(val successBody: T , isLoading: Boolean = false): ResponseHandler<T>(isLoading)
    class Error<T>(val errorBody: String, isLoading: Boolean = false): ResponseHandler<T>(isLoading)
    class InProcess<T>(isLoading: Boolean = true): ResponseHandler<T>(isLoading)
}
