package com.demo.android.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.R
import com.demo.android.model.Course

class CustomRecyclerAdapter(val context : Context, var courseList: ArrayList<Course>) : RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder>() {

    override fun onBindViewHolder(title: ViewHolder, p1: Int) {
        title?.txtTitle?.text = courseList[p1].courseName
    }

    override fun onCreateViewHolder(title: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(title?.context).inflate(R.layout.recycler_view_item, title, false)
        return ViewHolder(v);
    }

    override fun getItemCount(): Int {
        return courseList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitle = itemView.findViewById<TextView>(R.id.appOSTitle_txtVw)
    }

    // To get the data to search Category
    fun filterList(filteredCourseList: ArrayList<Course>) {
        this.courseList = filteredCourseList;
        notifyDataSetChanged();
    }
}