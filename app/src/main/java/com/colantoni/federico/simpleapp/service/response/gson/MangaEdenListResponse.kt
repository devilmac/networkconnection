package com.colantoni.federico.simpleapp.service.response.gson

import com.squareup.moshi.Json


@Json(name = "MangaEdenListResponse")
class MangaEdenListResponse {

    var total: Int = 0

    var start: Int = 0

    var manga: List<MangaEdenListResponseManga>? = null

    var end: Int = 0

    var page: Int = 0
}
