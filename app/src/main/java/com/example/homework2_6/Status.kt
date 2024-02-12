package com.example.homework2_6

import android.widget.ImageView

class Status (private val imgCircleStatus: ImageView) {

    fun setStatusImage(status: String) {
        when (status) {
            "Alive" -> imgCircleStatus.setBackgroundResource(R.drawable.alive)
            "Dead" -> imgCircleStatus.setBackgroundResource(R.drawable.death)
            else -> imgCircleStatus.setBackgroundResource(R.drawable.unknown)
        }
    }
}