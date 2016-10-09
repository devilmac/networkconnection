package com.colantoni.federico.simpleapp.service.response.gson;

import java.util.List;


public class MangaEdenListResponse {

    private int total;

    private int start;

    private List<MangaEdenListResponseManga> manga;

    private int end;

    private int page;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public List<MangaEdenListResponseManga> getManga() {
        return manga;
    }

    public void setManga(List<MangaEdenListResponseManga> manga) {
        this.manga = manga;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
