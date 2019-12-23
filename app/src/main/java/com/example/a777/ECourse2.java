package com.example.a777;

public class ECourse2 {
    private String course_name;
    private int imageId;

    public ECourse2(String course_name,int imageId){
        this.course_name = course_name;
        this.imageId = imageId;

    }
    public String getName(){
        return course_name;
    }
    public int getImageId(){
        return imageId;
    }

}
