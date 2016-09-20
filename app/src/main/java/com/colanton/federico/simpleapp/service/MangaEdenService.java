package com.colanton.federico.simpleapp.service;


import com.colanton.federico.simpleapp.service.response.MangaEdenListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface MangaEdenService {

    String LANGUAGE = "language";

    @GET(value = "list/{" + LANGUAGE + "}/")
    Observable<MangaEdenListResponse> getAllMangaRx(@Path(LANGUAGE) int language);

    @GET(value = "list/{" + LANGUAGE + "}/")
    Call<MangaEdenListResponse> getAllManga(@Path((LANGUAGE)) int language);
}
