package com.lotto.mylotto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.lotto.mylotto.databinding.ActivityConstellationBinding
import com.lotto.mylotto.databinding.ActivityMainBinding
import com.lotto.mylotto.db.LottoResponse
import com.lotto.mylotto.repository.LottoRepository
import retrofit2.Call
import retrofit2.Response
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import javax.security.auth.callback.Callback
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private val lottoImageStartId = R.drawable.ball_01
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root

        val randomCard = mainBinding.randomCard
        val constellationCard = mainBinding.constellationCard
        val nameCard = mainBinding.nameCard

        getLastSaturday()

        randomCard.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)

            intent.putIntegerArrayListExtra("result", ArrayList(getShuffleLottoNumbers()))

            startActivity(intent)
        }

        constellationCard.setOnClickListener {
            startActivity(Intent(this, ConstellationActivity::class.java))
        }

        nameCard.setOnClickListener {
            startActivity(Intent(this, NameActivity::class.java))
        }

        setContentView(view)
    }

    /*
    * 여러 개의 무작위 수를 추출해야 하고 중복이 없어야 한다면 랜덤보다 shuffle을 쓰는 것이 유리합니다. 랜덤으로
    * 추출하는 것은 추출되는 수가 많아질수록 중복될 확률도 높아지기 때문
    * */
    private fun getRandomLottoNumbers():MutableList<Int>{
        val lottoNumbers = mutableListOf<Int>()

        for (i in 1..6){
            var number = 0
            do {
                number = getRandomLottoNumber()
            }while (lottoNumbers.contains(number))

            lottoNumbers.add(number)
        }

        return lottoNumbers
    }

    private fun getRandomLottoNumber():Int{
        return Random().nextInt(45)+1
    }

    private fun getShuffleLottoNumbers():MutableList<Int>{

        val list = mutableListOf<Int>()

        for (number in 1..45){
            list.add(number)
        }
        list.shuffle()

        return list.subList(0,6)

    }

    private fun getLastSaturday(){

        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")

        val c = Calendar.getInstance()
        val firstLottoDate = "2002-12-07"
        val startDate = simpleDateFormat.parse(firstLottoDate).time
        val getLastSaturDay = simpleDateFormat.format(c.time).toString()
        val endDate = simpleDateFormat.parse(getLastSaturDay).time
        var getLottoCount:Long = 1

        val lottoRepository = LottoRepository()




        c.time = Date()
        c.add(Calendar.DAY_OF_WEEK,-1)
        c.firstDayOfWeek = Calendar.SATURDAY
        c.set(Calendar.DAY_OF_WEEK,Calendar.SATURDAY)
        getLottoCount += ((endDate - startDate) / (24 * 60 * 60 * 1000) / 7)
        Log.d("MAIN엑티비티getLastSaturday","${getLottoCount}")

        lottoRepository.getLottoInfo(getLottoCount.toString()).enqueue(object: retrofit2.Callback<LottoResponse>{
            override fun onResponse(call: Call<LottoResponse>, response: Response<LottoResponse>) {
                val returnValue = response.body()?.returnValue
                val lottoInfo = response.body()
                if (lottoInfo?.returnValue.equals("success")){
                    Log.d("MAIN엑티비티getLastSaturday","$lottoInfo")
                    val dec = DecimalFormat("#,###")
                    mainBinding.lottoAmount.text = "${lottoInfo?.drwNo}회 1등 당첨금액"
                    mainBinding.lottoDate.text = "(${lottoInfo?.drwNoDate})"
                    mainBinding.lottoAmount.text = "${dec.format(lottoInfo?.firstWinamnt)}원"
                    mainBinding.lottoNo1.setImageResource(lottoImageStartId+(lottoInfo?.drwtNo1?.toInt()!!-1))
                    mainBinding.lottoNo2.setImageResource(lottoImageStartId+(lottoInfo?.drwtNo2?.toInt()!!-1))
                    mainBinding.lottoNo3.setImageResource(lottoImageStartId+(lottoInfo?.drwtNo3?.toInt()!!-1))
                    mainBinding.lottoNo4.setImageResource(lottoImageStartId+(lottoInfo?.drwtNo4?.toInt()!!-1))
                    mainBinding.lottoNo5.setImageResource(lottoImageStartId+(lottoInfo?.drwtNo5?.toInt()!!-1))
                    mainBinding.lottoNo6.setImageResource(lottoImageStartId+(lottoInfo?.drwtNo6?.toInt()!!-1))
                    mainBinding.lottoBonusNo.setImageResource(lottoImageStartId+(lottoInfo?.bnusNo?.toInt()!!-1))
                }


            }

            override fun onFailure(call: Call<LottoResponse>, t: Throwable) {
                Log.d("MAIN엑티비티getLastSaturday","$t")
            }


        })


    }

}




