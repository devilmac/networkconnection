package com.colantoni.federico.simpleapp.service.response.immutables;

import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
public interface MangaEdenListResponse {

    int total();

    int start();

    List<MangaEdenListResponseManga> manga();

    int end();

    int page();
}
