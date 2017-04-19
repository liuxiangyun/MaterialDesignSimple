package com.example.materialdesignsimple.bean;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by xiangyun_liu on 2017/4/14.
 * <p>
 * Parcelable 比 Serializable 效率高，建议使用Parcelable
 */

public class CoverBean extends BaseBean implements Parcelable {

    private String nickname;
    private String url;
    private int width;
    private int height;

    public CoverBean() {

    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nickname);
        dest.writeString(url);
        dest.writeInt(width);
        dest.writeInt(height);
    }

    protected CoverBean(Parcel in) {
        nickname = in.readString();
        url = in.readString();
        width = in.readInt();
        height = in.readInt();
    }

    public static final Creator<CoverBean> CREATOR = new Creator<CoverBean>() {
        @Override
        public CoverBean createFromParcel(Parcel in) {
            return new CoverBean(in);
        }

        @Override
        public CoverBean[] newArray(int size) {
            return new CoverBean[size];
        }
    };
}
