package com.colanton.federico.simpleapp.service.response;

import android.os.Parcel;
import android.os.Parcelable;

public class MangaEdenListResponseManga implements Parcelable {

    public static final Creator<MangaEdenListResponseManga> CREATOR = new Creator<MangaEdenListResponseManga>() {
        @Override
        public MangaEdenListResponseManga createFromParcel(Parcel source) {
            MangaEdenListResponseManga var = new MangaEdenListResponseManga();
            var.a = source.readString();
            var.c = source.createStringArray();
            var.s = source.readInt();
            var.im = source.readString();
            var.t = source.readString();
            var.h = source.readInt();
            var.ld = source.readDouble();
            var.i = source.readString();
            return var;
        }

        @Override
        public MangaEdenListResponseManga[] newArray(int size) {
            return new MangaEdenListResponseManga[size];
        }
    };
    private String a;
    private String[] c;
    private int s;
    private String im;
    private String t;
    private int h;
    private double ld;
    private String i;

    public String getA() {
        return this.a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String[] getC() {
        return this.c;
    }

    public void setC(String[] c) {
        this.c = c;
    }

    public int getS() {
        return this.s;
    }

    public void setS(int s) {
        this.s = s;
    }

    public String getIm() {
        return this.im;
    }

    public void setIm(String im) {
        this.im = im;
    }

    public String getT() {
        return this.t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public int getH() {
        return this.h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public double getLd() {
        return this.ld;
    }

    public void setLd(double ld) {
        this.ld = ld;
    }

    public String getI() {
        return this.i;
    }

    public void setI(String i) {
        this.i = i;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.a);
        dest.writeStringArray(this.c);
        dest.writeInt(this.s);
        dest.writeString(this.im);
        dest.writeString(this.t);
        dest.writeInt(this.h);
        dest.writeDouble(this.ld);
        dest.writeString(this.i);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
