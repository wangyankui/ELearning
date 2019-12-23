
package com.example.a777;

public class ECourse3 {
    private String course_name;
    private int imageId;
    private int course_starId;

    private String course_school;
    

    public ECourse3(String course_name,int imageId,int course_star, String courseschool){
        this.course_name = course_name;
        this.imageId = imageId;
        this.course_starId = course_star;
        this.course_school = courseschool;
    }
    public String getName(){
        return course_name;
    }
    public int getImageId(){
        return imageId;
    }
    public String getCourseschool(){return course_school;}
    public int getCoursestarId(){ return course_starId;}
    
}
