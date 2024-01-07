package com.example.learnnplay;
import android.net.Uri;

public class QPicture {
    private String name;
    private Uri picpath;
    QPicture(){}
    QPicture(String name,String path){
        this.name=name;
        this.picpath= Uri.parse(path);
    }
    public void SetName(String name){
        this.name=name;
    }
    public void SetPath(String Path){
        this.picpath= Uri.parse(Path);
    }
    public String GetName(){
        return this.name;
    }
    public String GetPath(){
        return this.picpath.toString();
    }
}
