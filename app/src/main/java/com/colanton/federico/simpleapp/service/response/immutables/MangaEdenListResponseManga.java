package com.colanton.federico.simpleapp.service.response.immutables;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import org.immutables.value.Value;

@Value.Immutable
public abstract class MangaEdenListResponseManga {

    public abstract String a();

    public abstract String[] c();

    public abstract int s();

    public abstract String t();

    public abstract int h();

    public abstract String i();

    @SerializedName("ld")
    @Value.Default
    public Double ld() {

        return (double) 0L;
    }

    @SerializedName("im")
    @Value.Default
    @Nullable
    public String im() {

        return "";
    }
}
