package com.lotto.mylotto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lotto.mylotto.databinding.ActivityConstellationBinding

class ConstellationActivity : AppCompatActivity() {
    private lateinit var constellationBinding: ActivityConstellationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        constellationBinding = ActivityConstellationBinding.inflate(layoutInflater)
        val view = constellationBinding.root
        val goResultButton = constellationBinding.goResultButton
        goResultButton.setOnClickListener {
            startActivity(Intent(this,ResultActivity::class.java))
        }

        setContentView(view)
    }
}