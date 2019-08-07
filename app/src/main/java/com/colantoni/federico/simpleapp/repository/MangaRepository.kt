package com.colantoni.federico.simpleapp.repository

import com.colantoni.federico.networklibrary.builder.NetworkConnectionBuilder
import com.colantoni.federico.simpleapp.MainActivity
import com.colantoni.federico.simpleapp.service.MangaEdenService


class MangaRepository {
    private val client = NetworkConnectionBuilder<MangaEdenService>().usingRxAdapter(true).getWebservice(MainActivity.BASE_URL)

    suspend fun getManga(id: Int) = client.getAllManga(id)
}
