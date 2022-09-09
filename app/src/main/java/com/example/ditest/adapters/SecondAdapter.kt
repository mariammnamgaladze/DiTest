package com.example.ditest.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ditest.Ext.setImage
import com.example.ditest.databinding.ItemLayoutBinding
import com.example.ditest.model.Courses

class SecondAdapter :
    ListAdapter<Courses.ActiveCourse, SecondAdapter.CoursesViewHolder>(ActiveCoursesCallback()) {
    inner class CoursesViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val currentItem = getItem(adapterPosition)
            binding.apply {
                icon.setImage(currentItem.image)
                textViewTime.text = currentItem.bookingTime
                pbProgress.setIndicatorColor(Color.parseColor("#${currentItem.mainColor}"))
                textViewTitle.text = currentItem.title
                root.background.setTint(Color.parseColor("#${currentItem.mainColor}"))


            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesViewHolder {
        return CoursesViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CoursesViewHolder, position: Int) {
        holder.bind()
    }

    class ActiveCoursesCallback: DiffUtil.ItemCallback<Courses.ActiveCourse>(){
        override fun areItemsTheSame(
            oldItem: Courses.ActiveCourse,
            newItem: Courses.ActiveCourse
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Courses.ActiveCourse,
            newItem: Courses.ActiveCourse
        ): Boolean {
            return oldItem == newItem
        }

    }
}