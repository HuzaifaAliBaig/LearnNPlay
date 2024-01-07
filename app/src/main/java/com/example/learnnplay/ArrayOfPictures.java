package com.example.learnnplay;

import java.util.ArrayList;

public class ArrayOfPictures {
    private static ArrayList<QPicture> array;
    private static QPicture pic;
   static  ArrayList<QPicture> GetArrayofPictures()
    {   array=new ArrayList<>();
        PicturesInitialize();
        return array;
    }
    private static void PicturesInitialize()
    {
        initialize("Tree","android.resource://com.example.learnnplay/"+R.drawable.tree);
        initialize("Strawberry","android.resource://com.example.learnnplay/"+R.drawable.strawberry);
        initialize("Pen","android.resource://com.example.learnnplay/"+R.drawable.pen);
        initialize("Pencil","android.resource://com.example.learnnplay/"+R.drawable.pencil);
        initialize("House","android.resource://com.example.learnnplay/"+R.drawable.house);
        initialize("Heart","android.resource://com.example.learnnplay/"+R.drawable.heart1);
        initialize("Money","android.resource://com.example.learnnplay/"+R.drawable.money);
        initialize("Apple","android.resource://com.example.learnnplay/"+R.drawable.apple);
    }
    private static void  initialize(String name,String Path){
        pic=new QPicture(name,Path);
        array.add(pic);
    }
}
