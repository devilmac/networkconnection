package com.colantoni.federico.simpleapp.service


import com.colantoni.federico.simpleapp.service.MangaEdenServiceConstant.LANGUAGE
import com.colantoni.federico.simpleapp.service.response.gson.MangaEdenListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


/**  */
interface MangaEdenService {

    /**  */
    @GET(value = "list/{$LANGUAGE}/")
    fun getAllManga(@Path(LANGUAGE) language: Int): Call<MangaEdenListResponse>
}
