package com.colanton.federico.simpleapp.service.response;

import com.colanton.federico.networklibrary.immutables.BaseResponse;

public interface MangaEdenListResponse extends BaseResponse {

    int total();

    int start();

    MangaEdenListResponseManga[] manga();

    int end();

    int page();
}
