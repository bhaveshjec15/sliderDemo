package com.example.demo.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.*
import android.view.View
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demo.adapter.CustomRecyclerAdapter
import com.example.demo.databinding.ActivityMainBinding
import com.example.demo.model.Course
import com.example.demo.utils.Helper
import com.example.demo.adapter.ViewPagerAdapter

import androidx.core.content.ContextCompat
import com.example.demo.R

import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {

    lateinit var adapter: CustomRecyclerAdapter
    lateinit var mainBinding : ActivityMainBinding
    lateinit var viewPagerAdapter: ViewPagerAdapter
    var dotscount = 0
    lateinit var tempList: ArrayList<Course>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // set adapter on list
        setAdapter()
        setAdapterForList(Helper.getKotlinList())

        // click on edittext view
        onClick()
        setDotsIndicator()

        dotscount = viewPagerAdapter.getCount()
        mainBinding.sampleRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
    }

    fun setDotsIndicator() {
        dotscount = viewPagerAdapter.count
        var dots = arrayOfNulls<ImageView>(dotscount)

        for (i in 0 until dotscount) {
            dots[i] = ImageView(this)
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(5, 0, 5, 0)
            dots[i]!!.layoutParams= params
            dots[i]!!.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.non_active_dot
                )
            )
            mainBinding.SliderDots!!.addView(dots[i], params)
        }

        dots[0]!!.setImageDrawable(ContextCompat.getDrawable(applicationContext, com.example.demo.R.drawable.active_dot))

        mainBinding.viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                for (i in 0 until dotscount) {
                    dots[i]!!.setImageDrawable(ContextCompat.getDrawable(applicationContext,R.drawable.non_active_dot))
                }
                dots[position]!!.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.active_dot
                    )
                )
               showListForPos(position)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    fun showListForPos(position: Int) {
        if(position==0){
            setAdapterForList(Helper.getKotlinList())
        }else if(position==1) {
            setAdapterForList(Helper.getAndroidList())
        }else{
            setAdapterForList(Helper.getJavaList())
        }
    }
    fun onClick() {
        mainBinding.sampleEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                filter(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }

    fun setAdapter() {   // set layout manager
        // set image slider data
        viewPagerAdapter = ViewPagerAdapter(this)
        mainBinding.viewPager!!.setAdapter(viewPagerAdapter);
        viewPagerAdapter.notifyDataSetChanged()

    }

    fun setAdapterForList(courseList: ArrayList<Course>){
        tempList=courseList
        // set list data
        adapter = CustomRecyclerAdapter(this, courseList)
        mainBinding.sampleRecyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    fun filter(text: String) {
        val filteredCourseAry: ArrayList<Course> = ArrayList()
        val courseAry : ArrayList<Course> = tempList
        for (eachCourse in courseAry) {
            if (eachCourse.courseName!!.toLowerCase().contains(text.toLowerCase())) {
                filteredCourseAry.add(eachCourse)
            }
        }
        //calling a method of the adapter class and passing the filtered list
        adapter.filterList(filteredCourseAry);
    }
}