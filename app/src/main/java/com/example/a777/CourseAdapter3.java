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

public class CourseAdapter3 extends RecyclerView.Adapter<CourseAdapter3.ViewHolder>{

    private List<ECourse2> mcourseList3;

    //接口
    private ClickInterface clickInterface;

    public CourseAdapter3(List<ECourse2> courseList){
        mcourseList3 = courseList;
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

        ImageView courseImage2;
        TextView courseName2;
        public ViewHolder(View view){
            super(view);
            courseView = view;
            courseImage2 = (ImageView)view.findViewById(R.id.course_Image2);
            courseName2 = (TextView)view.findViewById(R.id.course_name2);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_item2,parent,false);
        ViewHolder holder = new ViewHolder(view);


        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        ECourse2 course = mcourseList3.get(position);
        holder.courseImage2.setImageResource(course.getImageId());
        holder.courseName2.setText((course.getName()));
        //Button点击事件
        holder.courseImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickInterface != null) {
                    clickInterface.onImageClick(v, position);
                }
            }
        });
        //item点击事件
        holder.courseName2.setOnClickListener(new View.OnClickListener() {
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
        return mcourseList3.size();
    }




}
