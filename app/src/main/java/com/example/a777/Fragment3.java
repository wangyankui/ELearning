package com.example.a777;


import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a777.R;

import java.util.ArrayList;
import java.util.List;

public class Fragment3 extends Fragment {


    private List<ECourse> courseList1 = new ArrayList<>();
    private  View view;
    // @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.fragment1, container, false);
        //解决切换fragment后重复加载item问题
        if(view==null){
            view=LayoutInflater.from(getActivity()).inflate(R.layout.layout_view2, null);
            initCourse();
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view2);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
            recyclerView.setLayoutManager(layoutManager);
            CourseAdapter2 adapter2 = new CourseAdapter2(courseList1);
            recyclerView.setAdapter( adapter2);
            recyclerView.post(new Runnable() {
                @Override
                public void run() {
                    adapter2.setOnclick(new CourseAdapter2.ClickInterface() {
                        @Override
                        public void onImageClick(View view, int position) {
                            ECourse course1 = courseList1.get(position);

                            switch (position){
                                case 0:
                                    Intent intent = new Intent(view.getContext(),JSActivity.class);
                                    //view.getContext().startActivity(intent);
                                    view.getContext().startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                                    Toast.makeText(view.getContext(),"you clicked view " + course1.getName() + "位置" + position,Toast.LENGTH_SHORT).show();

                                    break;
                                case 1:
                                    Intent intent2 = new Intent(view.getContext(),CalculusActivity.class);
                                    //view.getContext().startActivity(intent);
                                    view.getContext().startActivity(intent2, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                                    Toast.makeText(view.getContext(),"you clicked view " + course1.getName() + "位置" + position,Toast.LENGTH_SHORT).show();

                                    break;
                                case 2:
                                    Intent intent3 = new Intent(view.getContext(),network.class);
                                    //view.getContext().startActivity(intent);
                                    view.getContext().startActivity(intent3, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                                    Toast.makeText(view.getContext(),"you clicked view " + course1.getName() + "位置" + position,Toast.LENGTH_SHORT).show();

                                    break;


                            }
                            getActivity().getWindow().setEnterTransition(new Fade().setDuration(2000));
                            getActivity().getWindow().setExitTransition(new Fade().setDuration(2000));
                        }

                        @Override
                        public void onItemClick(View view, int position) {

                        }
                    });
                }
            });
        }
        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }



        //view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_view3, null);
        /*
        initCourse();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        CourseAdapter adapter = new CourseAdapter(courseList1);
        recyclerView.setAdapter( adapter);
        */

        return view;
    }

    private void initCourse(){

        ECourse course2 = new ECourse("微积分",R.mipmap.gaoc,"王大海","1604","￥8.6");
        courseList1.add(course2);
        ECourse course3 = new ECourse("计算机网络",R.mipmap.network,"叶鹏","1200","￥4.02");
        courseList1.add(course3);
    }
}