package com.lotto.mylotto.api

import com.lotto.mylotto.db.LottoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LottoAPI {

    @GET("common.do")
    fun getLottoInfo(
        @Query("method") method:String,
        @Query("drwNo") number:String
    ): Call<LottoResponse>
}