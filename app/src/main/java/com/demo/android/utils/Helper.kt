package com.demo.android.utils

import com.demo.android.model.Course


object Helper{

    fun <ArrayList> getKotlinList(): ArrayList {
        var courseList = ArrayList<Course>()
        for (i in 1..15) {
            courseList.add(Course("Kotlin Tag "+i))
        }
        return courseList as ArrayList
    }

    fun <ArrayList> getAndroidList(): ArrayList {
        var courseList = ArrayList<Course>()
        for (i in 1..15) {
            courseList.add(Course("Android Tag "+i))
        }
        return courseList as ArrayList
    }

    fun <ArrayList> getJavaList(): ArrayList {
        var courseList = ArrayList<Course>()
        for (i in 1..15) {
            courseList.add(Course("Java Tag "+i))
        }
        return courseList as ArrayList
    }
}