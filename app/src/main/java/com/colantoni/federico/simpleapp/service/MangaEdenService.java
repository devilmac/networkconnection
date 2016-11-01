package com.colantoni.federico.simpleapp.service;


import com.colantoni.federico.simpleapp.service.response.immutables.MangaEdenListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;


public interface MangaEdenService {

    String LANGUAGE = "language";

    @GET(value = "list/{" + LANGUAGE + "}/")
    Observable<MangaEdenListResponse> getAllMangaImmutablesRx(@Path(LANGUAGE) int language);

    @GET(value = "list/{" + LANGUAGE + "}/")
    Call<MangaEdenListResponse> getAllMangaImmutables(@Path((LANGUAGE)) int language);

    @GET(value = "list/{" + LANGUAGE + "}/")
    Observable<com.colantoni.federico.simpleapp.service.response.gson.MangaEdenListResponse> getAllMangaRx(@Path(LANGUAGE) int language);

    @GET(value = "list/{" + LANGUAGE + "}/")
    Call<com.colantoni.federico.simpleapp.service.response.gson.MangaEdenListResponse> getAllManga(@Path((LANGUAGE)) int language);
}
