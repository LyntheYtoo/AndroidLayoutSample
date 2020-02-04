package com.example.layoutsample;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Food 카테고리에 담길 각 Food에 대한 데이터
 */
public class FoodData implements Parcelable {
    // 음식 리소스 id
    public final int imageId;
    // 음식 이름 문자열
    public final String name;
    // 별표 체크 여부
    public boolean stared;

    /**
     * 생성자에서 데이터를 초기화
     * @param imageId 해당 food 이미지 리소스id
     * @param name 해당 food 이름
     */
    public FoodData(int imageId, String name) {
        this.imageId = imageId;
        this.name = name;
        stared = false;
    }

    /**
     * 역직렬화 할때 필요한 생성자
     * @param parcel 직렬화된 데이터
     */
    public FoodData(Parcel parcel) {
        this.imageId = parcel.readInt();
        this.name = parcel.readString();
        this.stared = parcel.readByte() == 1;
    }

    /**
     * 역직렬화를 위해 시스템이 Creator 를 통해서 역직렬화 생성자 호출
     */
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


    // Parcelable Override
    /**
     * 현재 사용하지 않음
     * @return
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * 직렬화를 위한 메서드
     * @param dest 직렬화되어 데이터가 담길 곳
     * @param flags 현재 사용안함
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageId);
        dest.writeString(name);
        dest.writeByte((byte)(stared? 1:0));
    }
}
