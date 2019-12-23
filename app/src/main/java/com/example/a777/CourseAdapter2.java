package com.example.a777;

import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CourseAdapter2 extends RecyclerView.Adapter<CourseAdapter2.ViewHolder>{

    private List<ECourse> mcourseList2;

    //接口
    private ClickInterface clickInterface;

    public CourseAdapter2(List<ECourse> courseList){
        mcourseList2 = courseList;
    }


    //--------------------点击事件-------------------------------------------//
    public void setOnclick(ClickInterface clickInterface) {
        this.clickInterface = clickInterface;
    }


    //回调接口
    public interface ClickInterface {
        void onImageClick(View view, int position);

        void onItemClick(View view, int position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        View courseView;

        ImageView courseImage;
        TextView courseName,courseteacher,coursecount,courseprice;
        public ViewHolder(View view){
            super(view);
            courseView = view;
            courseImage = (ImageView)view.findViewById(R.id.course_Image);
            courseName = (TextView)view.findViewById(R.id.course_name);
            coursecount = (TextView)view.findViewById(R.id.personcount);
            courseteacher = (TextView)view.findViewById(R.id.course_teacher);
            courseprice = (TextView)view.findViewById(R.id.course_price);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_item,parent,false);
        ViewHolder holder = new ViewHolder(view);


        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        ECourse course = mcourseList2.get(position);
        holder.courseImage.setImageResource(course.getImageId());
        holder.courseName.setText((course.getName()));
        holder.courseteacher.setText(course.getCourseteacher());
        holder.coursecount.setText(course.getPersonCount());
        holder.courseprice.setText(course.getPrice());

        //Button点击事件
        holder.courseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickInterface != null) {
                    clickInterface.onImageClick(v, position);
                }
            }
        });
        //item点击事件
        holder.courseName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickInterface != null) {
                    clickInterface.onItemClick(v, position);
                }
            }
        });
        //--------------------点击事件-------------------------------------------//
    }



    @Override
    public int getItemCount(){
        return mcourseList2.size();
    }




}
