package com.lotto.mylotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.lotto.mylotto.databinding.ActivityConstellationBinding
import com.lotto.mylotto.databinding.ActivityResultBinding
import java.text.SimpleDateFormat
import java.util.*

class ResultActivity : AppCompatActivity(){

    private lateinit var resultBinding: ActivityResultBinding
    private val lottoImageStartId = R.drawable.ball_01
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        resultBinding = ActivityResultBinding.inflate(layoutInflater)
        val view = resultBinding.root
        val resultLabel = resultBinding.resultLabel
        val result = intent.getIntegerArrayListExtra("result")
        val name = intent.getStringExtra("name")

        resultLabel.text = "랜덤으로 생성된\n로또번호입니다."

        if (!TextUtils.isEmpty(name)){
            resultLabel.text = "${name} 님의\n${SimpleDateFormat("yyyy년 MM월 dd일 HH시").format(Date())}\n로또 번호입니다."
        }

        result?.let {
            updateLottoBallImage(result.sortedBy { it })
        }


        setContentView(view)

    }


    private fun updateLottoBallImage(result:List<Int>){

        if (result.size < 6) return


        resultBinding.imageView3.setImageResource(lottoImageStartId+(result[0]-1))
        resultBinding.imageView4.setImageResource(lottoImageStartId+(result[1]-1))
        resultBinding.imageView5.setImageResource(lottoImageStartId+(result[2]-1))
        resultBinding.imageView6.setImageResource(lottoImageStartId+(result[3]-1))
        resultBinding.imageView7.setImageResource(lottoImageStartId+(result[4]-1))
        resultBinding.imageView8.setImageResource(lottoImageStartId+(result[5]-1))

    }

}

