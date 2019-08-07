package com.colantoni.federico.simpleapp.repository

import com.colantoni.federico.networklibrary.core.NetworkConnection
import com.colantoni.federico.simpleapp.MainActivity
import com.colantoni.federico.simpleapp.service.MangaEdenService


class MangaRepository {
    var client = NetworkConnection(urlString = MainActivity.BASE_URL, useRxAdapter = true).initServiceInstance(MangaEdenService::class.java)

    suspend fun getManga(id: Int) = client.getAllManga(id)
}
