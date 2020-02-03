package com.example.layoutsample;

import android.os.Parcel;
import android.os.Parcelable;

public class FoodData implements Parcelable {
    // 음식 리소스 id
    public final int imageId;
    // 음식 이름 문자열
    public final String name;
    // 별표 체크 여부
    public boolean stared;

    public FoodData(int imageId, String name) {
        this.imageId = imageId;
        this.name = name;
        stared = false;
    }

    public FoodData(Parcel parcel) {
        this.imageId = parcel.readInt();
        this.name = parcel.readString();
        this.stared = parcel.readByte() == 1;
    }

    public static final Creator<FoodData> CREATOR = new Creator<FoodData>() {
        @Override
        public FoodData createFromParcel(Parcel in) {
            return new FoodData(in);
        }

        @Override
        public FoodData[] newArray(int size) {
            return new FoodData[size];
        }
    };


    // Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageId);
        dest.writeString(name);
        dest.writeByte((byte)(stared? 1:0));
    }
}
