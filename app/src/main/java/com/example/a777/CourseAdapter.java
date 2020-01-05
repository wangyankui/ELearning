package com.example.a777;

import android.content.Intent;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;


/*
① 创建一个继承RecyclerView.Adapter<VH>的Adapter类
② 创建一个继承RecyclerView.ViewHolder的静态内部类
③ 在Adapter中实现3个方法：
   onCreateViewHolder()
   onBindViewHolder()
   getItemCount()
*/
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder>{

    private List<ECourse> mcourseList;

    //接口
    private CourseAdapter2.ClickInterface clickInterface;

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


    //--------------------点击事件-------------------------------------------//
    public void setOnclick(CourseAdapter2.ClickInterface clickInterface) {
        this.clickInterface = clickInterface;
    }


    //回调接口
    public interface ClickInterface {
        void onImageClick(View view, int position);

        void onItemClick(View view, int position);
    }


    public CourseAdapter(List<ECourse> courseList){
        mcourseList = courseList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_item,parent,false);
        ViewHolder holder = new ViewHolder(view);


        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        //将数据和控件绑定
        ECourse course = mcourseList.get(position);
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

        //返回item总条数
        return mcourseList.size();
    }



}
