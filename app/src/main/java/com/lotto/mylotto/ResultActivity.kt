package com.lotto.mylotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lotto.mylotto.databinding.ActivityConstellationBinding

class ResultActivity : AppCompatActivity(){

    private lateinit var resultBinding: ActivityConstellationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        resultBinding = ActivityConstellationBinding.inflate(layoutInflater)
        val view = resultBinding.root

        setContentView(view)

    }
}