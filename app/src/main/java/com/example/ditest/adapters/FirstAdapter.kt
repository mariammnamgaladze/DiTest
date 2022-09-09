package com.example.ditest.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ditest.databinding.ItemLayout2Binding
import com.example.ditest.model.Courses

class FirstAdapter:
    ListAdapter<Courses.NewCourse, FirstAdapter.CoursesViewHolder>(ActiveCoursesCallback()) {
    inner class CoursesViewHolder(private val binding: ItemLayout2Binding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val currentItem = getItem(adapterPosition)
            binding.apply {
                textViewTitle.text = currentItem.title
                textViewQuestion.text = currentItem.question
                textViewDuration.text = currentItem.duration
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesViewHolder {
        return CoursesViewHolder(
            ItemLayout2Binding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CoursesViewHolder, position: Int) {
        holder.bind()
    }

    class ActiveCoursesCallback: DiffUtil.ItemCallback<Courses.NewCourse>(){
        override fun areItemsTheSame(
            oldItem: Courses.NewCourse,
            newItem: Courses.NewCourse
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Courses.NewCourse,
            newItem: Courses.NewCourse
        ): Boolean {
            return oldItem == newItem
        }


    }
}