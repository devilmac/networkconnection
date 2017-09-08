package com.colantoni.federico.simpleapp


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.colantoni.federico.simpleapp.service.MangaEdenService
import com.colantoni.federico.simpleapp.service.response.gson.MangaEdenListResponse
import com.colantoni.federico.simpleapp.service.response.gson.MangaEdenListResponseManga
import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.moshi.MoshiConverterFactory


/**  */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {

        super.onStart()

        val moshiBuilder = Moshi.Builder()

        moshiBuilder.add(MangaEdenListResponse::class.java)
        moshiBuilder.add(MangaEdenListResponseManga::class.java)

        val moshiConverterFactory = MoshiConverterFactory.create(moshiBuilder.build())

        val mangaEdenServiceWithImmutables = com.colantoni.federico.networklibrary.core.NetworkConnection.NetworkConnectionBuilder(BASE_URL).converterFactory(moshiConverterFactory).rxAdapter(true).build().initializeServiceInstance(MangaEdenService::class.java)

        val mangaLanguage = 1

        mangaEdenServiceWithImmutables.getAllMangaImmutables(mangaLanguage).enqueue(object : Callback<MangaEdenListResponse> {

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
        val BASE_URL = "http://www.mangaeden.com/api/"
    }
}
