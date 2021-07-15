package com.lotto.mylotto.repository

import com.lotto.mylotto.api.LottoRetrofitInstance

class LottoRepository {

    fun getLottoInfo(number:String) = LottoRetrofitInstance.api.getLottoInfo("getLottoNumber",number)
}