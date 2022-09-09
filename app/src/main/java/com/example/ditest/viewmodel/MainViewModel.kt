package com.example.ditest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ditest.domain.MainRepository
import com.example.ditest.model.Courses
import com.example.ditest.resources.ResponseHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val mainRepo:MainRepository): ViewModel(){


    private val _responseFlow = MutableStateFlow<ResponseHandler<Courses>>(ResponseHandler.InProcess())
    val responseFlow: StateFlow<ResponseHandler<Courses>> get() = _responseFlow

        fun getResponse(){
            viewModelScope.launch {
                mainRepo.getResponseFromApi().collect {
                    when(it){
                        is ResponseHandler.Success -> _responseFlow.emit(it)
                        is ResponseHandler.Error -> _responseFlow.emit(it)
                        is ResponseHandler.InProcess -> _responseFlow.emit(it)
                    }
                }
            }
        }
}