package com.example.a777;

public class ECourse {
    private String course_name;
    private int imageId;
    private String count;
    private String price;
    private String course_teacher;
    public ECourse(String course_name,int imageId,String course_teacher,String count, String price){
        this.course_name = course_name;
        this.imageId = imageId;
        this.course_teacher = course_teacher;
        this.count = count;
        this.price = price;
    }
    public String getName(){
        return course_name;
    }
    public int getImageId(){
        return imageId;
    }
    public String getCourseteacher(){return course_teacher;}
    public String getPersonCount(){ return count;}
    public String getPrice(){return price;}
}
