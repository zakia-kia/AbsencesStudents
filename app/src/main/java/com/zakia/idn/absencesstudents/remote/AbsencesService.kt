package com.zakia.idn.absencesstudents.remote

import com.zakia.idn.absencesstudents.model.Items
import retrofit2.Call
import retrofit2.http.*
import java.sql.Date

interface AbsencesService {
    @GET("person/get")
    fun getAbsences(): Call<List<Items>>?

    @FormUrlEncoded
    @POST("person/add")
    fun addAbsences(
        @Field("name") name: String?,
        @Field("date") date: String?,
        @Field("kelas") kelas: String?,
        @Field("information") information: String?,
        @Field("subjects") subjects: String?
    ): Call<Items?>?

    @FormUrlEncoded
    @PUT("person/update")
    fun updateAbsences(
        @Field("id") id: Int,
        @Field("name") name: String?,
        @Field("date") date: String?,
        @Field("kelas") kelas: String?,
        @Field("information") information: String?,
        @Field("subjects") subjects: String?
    ): Call<Items?>?

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "person/delete", hasBody = true)
    fun deleteAbsences(@Field("id") id: Int): Call<Items?>?
}