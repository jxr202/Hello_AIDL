package com.xman.bean;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

/**
 * Created by Jxr35 on 2020/4/4
 */
public class IData implements Parcelable {

    public int key;

    public String value;


    public IData(int key, String value) {
        this.key = key;
        this.value = value;
    }

    protected IData(Parcel in) {
        key = in.readInt();
        value = in.readString();
    }

    public static final Creator<IData> CREATOR = new Creator<IData>() {
        @Override
        public IData createFromParcel(Parcel in) {
            return new IData(in);
        }

        @Override
        public IData[] newArray(int size) {
            return new IData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(key);
        dest.writeString(value);
    }

    @Override
    public String toString() {
        return "IData{" +
                "key=" + key +
                ", value='" + value + '\'' +
                '}';
    }
}
