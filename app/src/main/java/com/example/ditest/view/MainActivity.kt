package com.example.ditest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.ditest.R
import com.example.ditest.adapters.FirstAdapter
import com.example.ditest.adapters.SecondAdapter
import com.example.ditest.databinding.ActivityMainBinding
import com.example.ditest.model.Courses
import com.example.ditest.resources.ResponseHandler
import com.example.ditest.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by viewModels()
    private val firstAdapter by lazy{FirstAdapter()}
    private val secondAdapter by lazy{ SecondAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    observers()

    }

    private fun observers() {
        lifecycleScope.launch {
            viewModel.getResponse()
            viewModel.responseFlow.collect {
                when(it){
                    is ResponseHandler.Success -> handleSuccess(it.data)
                    is ResponseHandler.Error -> handleError(it.errorMessage)
                    else -> {}
                }
            }
        }
    }


    private fun handleError(errorMessage: Throwable) {
        Toast.makeText(this, "${errorMessage.message}", Toast.LENGTH_SHORT).show()
    }

    private fun handleSuccess(data: Courses) {
        setupRecyclers(data)
    }


    private fun setupRecyclers(data: Courses) {
        with(firstAdapter){
            binding.rvActiveCourses.adapter = this
            this.submitList(data.activeCourses)
        }
        with(secondAdapter){
            binding.rvNewCourses.adapter = this
            this.submitList(data.newCourses)
        }

    }



}