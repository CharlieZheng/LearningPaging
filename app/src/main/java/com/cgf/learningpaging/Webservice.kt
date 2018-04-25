package com.cgf.learningpaging

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface Webservice {
    @GET("/historyWeather/province")
    fun getProvinceList(
            @Query("key") key: String
    ): Observable<Response<ResApi<List<Entity>>>>

}