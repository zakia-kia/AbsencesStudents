package com.zakia.idn.absencesstudents.remote

object APIUtils {
    private fun APIUtils() {}

    //it depends on your Ipconfig
    val API_URL = "http://192.168.1.4/marketplace2/index.php/"

    fun getAbsencesService(): AbsencesService? {
        return RetrofitClient.getClient(API_URL)?.create(AbsencesService::class.java)!!
    }
}