package com.colanton.federico.simpleapp.service.response;

import android.os.Parcel;
import android.os.Parcelable;

public class MangaEdenListResponse implements Parcelable {

    public static final Creator<MangaEdenListResponse> CREATOR = new Creator<MangaEdenListResponse>() {
        @Override
        public MangaEdenListResponse createFromParcel(Parcel source) {
            MangaEdenListResponse var = new MangaEdenListResponse();
            var.total = source.readInt();
            var.start = source.readInt();
            var.manga = source.createTypedArray(MangaEdenListResponseManga.CREATOR);
            var.end = source.readInt();
            var.page = source.readInt();
            return var;
        }

        @Override
        public MangaEdenListResponse[] newArray(int size) {
            return new MangaEdenListResponse[size];
        }
    };
    private int total;
    private int start;
    private MangaEdenListResponseManga[] manga;
    private int end;
    private int page;

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStart() {
        return this.start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public MangaEdenListResponseManga[] getManga() {
        return this.manga;
    }

    public void setManga(MangaEdenListResponseManga[] manga) {
        this.manga = manga;
    }

    public int getEnd() {
        return this.end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.total);
        dest.writeInt(this.start);
        dest.writeTypedArray(this.manga, flags);
        dest.writeInt(this.end);
        dest.writeInt(this.page);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
