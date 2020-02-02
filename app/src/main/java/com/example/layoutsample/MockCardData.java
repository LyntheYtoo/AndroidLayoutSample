package com.example.layoutsample;

import android.os.Parcel;
import android.os.Parcelable;

public class MockCardData implements IBoolStateController, Parcelable {
    public final int imageId;
    public final String name;
    private boolean stared;

    public MockCardData(int imageId, String name) {
        this.imageId = imageId;
        this.name = name;
        stared = false;
    }
    public MockCardData(Parcel parcel) {
        this.imageId = parcel.readInt();
        this.name = parcel.readString();
        this.stared = parcel.readBoolean();
    }

    public static final Creator<MockCardData> CREATOR = new Creator<MockCardData>() {
        @Override
        public MockCardData createFromParcel(Parcel in) {
            return new MockCardData(in);
        }

        @Override
        public MockCardData[] newArray(int size) {
            return new MockCardData[size];
        }
    };


    public void setStar(boolean star) { this.stared = star; }

    // IBoolStateController
    @Override
    public void changeState(boolean b) {
        setStar(b);
    }

    @Override
    public boolean getState() {
        return stared;
    }


    // Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageId);
        dest.writeString(name);
        dest.writeBoolean(stared);
    }
}
