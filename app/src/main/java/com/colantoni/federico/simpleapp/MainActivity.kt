package com.colantoni.federico.simpleapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.colantoni.federico.networklibrary.builders.NetworkConnectionBuilder
import com.colantoni.federico.networklibrary.factories.converter.ConverterAdaptersEnum
import com.colantoni.federico.networklibrary.factories.converter.ConverterAdaptersFactory
import com.colantoni.federico.simpleapp.service.MangaEdenService
import com.colantoni.federico.simpleapp.service.response.gson.MangaEdenListResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**  */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        val moshiAdapter = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        val networkConnectionBuilder = NetworkConnectionBuilder()
                .buildUrlConnectionString(BASE_URL)
                .buildOkHttpClient()
                .buildCallAdapterFactory(null)
                .buildConverterAdapterFactory(ConverterAdaptersFactory().getConverterAdapterFactory(ConverterAdaptersEnum.MOSHI, moshiAdapter))
        val mangaEdenService = networkConnectionBuilder.build().initServiceInstance(MangaEdenService::class.java)
        val mangaLanguage = 1

        mangaEdenService.getAllManga(mangaLanguage).enqueue(object : Callback<MangaEdenListResponse> {
            /**  */
            override fun onResponse(call: Call<MangaEdenListResponse>, response: Response<MangaEdenListResponse>) {
                Toast.makeText(this@MainActivity, "Total manga: " + response.body()!!.total, Toast.LENGTH_LONG).show()
            }

            /**  */
            override fun onFailure(call: Call<MangaEdenListResponse>, t: Throwable) {
                Log.e("RETROFIT", t.localizedMessage, t)
            }
        })
    }

    companion object {
        /**  */
        const val BASE_URL = "http://www.mangaeden.com/api/"
    }
}
