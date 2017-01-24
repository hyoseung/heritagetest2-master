package com.example.owl.heritage;

import android.net.Uri;

/**
 * Created by hyoseung on 2016-09-26.
 */
public class Course_item {
    private int num;
    private String name;
    private String uri;

    public Course_item(int num, String name, String uri){
        this.num = num;
        this.name = name;
        this.uri = uri;
    }

    int getNum() { return num; }
    String getName() { return name; }
    String getUri() { return uri; }

    void setNum(int num) { this.num = num; }
    void setName(String name) { this.name = name; }
    void setUri(String uri) { this.uri = uri; }

}
