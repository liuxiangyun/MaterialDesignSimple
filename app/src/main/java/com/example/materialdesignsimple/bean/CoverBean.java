package com.example.materialdesignsimple.bean;

import java.io.Serializable;

/**
 * Created by xiangyun_liu on 2017/4/14.
 */

public class CoverBean extends BaseBean implements Serializable {
    
    private String nickname;
    private String url;
    private int width;
    private int height;

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
}
