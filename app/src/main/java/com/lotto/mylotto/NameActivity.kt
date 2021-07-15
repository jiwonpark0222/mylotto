package com.lotto.mylotto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.lotto.mylotto.databinding.ActivityNameBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class NameActivity : AppCompatActivity() {
    private lateinit var nameBinding: ActivityNameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        nameBinding = ActivityNameBinding.inflate(layoutInflater)
        val view = nameBinding.root

        val goButton = nameBinding.goButton
        val backButton = nameBinding.backButton
        val editText = nameBinding.editText

        goButton.setOnClickListener {
            val intent = Intent(this,ResultActivity::class.java)

            intent.putIntegerArrayListExtra("result", ArrayList(getLottoNumbersFromHash(editText.text.toString())))
            intent.putExtra("name",editText.text.toString())

            startActivity(intent)
        }
        backButton.setOnClickListener {
            finish()
        }

        setContentView(view)
    }

    private fun getLottoNumbersFromHash(name:String):MutableList<Int>{
        val list = mutableListOf<Int>()
        Log.d("NAME엑티비티","$name")
        for (number in 1..45){
            list.add(number)
        }

        val targetString = SimpleDateFormat("yyyy-MM-dd-HH",Locale.KOREA).format(Date())+name

        Log.d("NAME엑티비티","$targetString")

        list.shuffle(Random(targetString.hashCode().toLong()))

        return list.subList(0,6)

    }

}