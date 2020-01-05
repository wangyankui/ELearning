package com.example.a777;



import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a777.R;

import java.util.ArrayList;
import java.util.List;

public class Fragment1 extends Fragment {

  // View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.layout_view1, null);
   //View view2 = LayoutInflater.from(getActivity()).inflate(R.layout.home_viewpager_two, null);
    private List<ECourse> courseList1 = new ArrayList<>();
    private  View view;
   // @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.fragment1, container, false);
        //解决切换fragment后重复加载item问题
        if(view==null){
            view=LayoutInflater.from(getActivity()).inflate(R.layout.layout_view3, null);
            initCourse();
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view1);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
            recyclerView.setLayoutManager(layoutManager);
            CourseAdapter adapter = new CourseAdapter(courseList1);
            recyclerView.setAdapter( adapter);

            recyclerView.post(new Runnable() {
                @Override
                public void run() {
                    adapter.setOnclick(new CourseAdapter2.ClickInterface() {
                        @Override
                        public void onImageClick(View view, int position) {
                            ECourse course1 = courseList1.get(position);

                            switch (position){
                                case 0:
                                    Intent intent = new Intent(view.getContext(),pythonActivity.class);
                                    view.getContext().startActivity(intent);
                                    Toast.makeText(view.getContext(),"you clicked view " + course1.getName() + "位置" + position,Toast.LENGTH_SHORT).show();
                                    break;
                                case 1:
                                    Intent intent2 = new Intent(view.getContext(),JavaActivity.class);
                                    view.getContext().startActivity(intent2);
                                    Toast.makeText(view.getContext(),"you clicked view " + course1.getName() + "位置" + position,Toast.LENGTH_SHORT).show();
                                    break;
                                case 2:
                                    Intent intent3 = new Intent(view.getContext(),ClanguageActivity.class);
                                    view.getContext().startActivity(intent3);
                                    Toast.makeText(view.getContext(),"you clicked view " + course1.getName() + "位置" + position,Toast.LENGTH_SHORT).show();
                                    break;
                                case 3:
                                    Intent intent4 = new Intent(view.getContext(),WebActivity.class);
                                    view.getContext().startActivity(intent4);
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
        for(int i = 0;i < 2;i++) {
            ECourse course1 = new ECourse("python", R.mipmap.python,"方军","200","$9.9");
            courseList1.add(course1);
            ECourse course2 = new ECourse("Java", R.mipmap.java,"杨观宝","4396","$22.6");
            courseList1.add(course2);
            ECourse course3 = new ECourse("C语言", R.mipmap.c,"王瑞","2146","$29.9");
            courseList1.add(course3);
            ECourse course4 = new ECourse("Web", R.mipmap.web,"邹翔","1900","$39.4");
            courseList1.add(course4);
            ECourse course5 = new ECourse("微积分", R.mipmap.gaoc,"尹军","560","$48.6");
            courseList1.add(course5);
        }
    }
}