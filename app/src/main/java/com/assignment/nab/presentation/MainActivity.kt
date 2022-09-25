package com.assignment.nab.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.assignment.nab.R
import com.assignment.security.SecurityHelper

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        Log.d("sddddddddddddd", SecurityHelper.appId())
    }

}