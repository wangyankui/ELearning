package com.example.a777.ui.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a777.CourseAdapter;
import com.example.a777.CourseAdapter3;
import com.example.a777.CourseAdapter4;
import com.example.a777.ECourse2;
import com.example.a777.ECourse3;
import com.example.a777.R;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private List<ECourse3> courseList1 = new ArrayList<>();
    private List<ECourse2> courseList2 = new ArrayList<>();

    private Context context;
    private  View view;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        if(view==null) {
            view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_dashboard, null);

            initCourse();
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view3);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
            recyclerView.setLayoutManager(layoutManager);
            CourseAdapter4 adapter = new CourseAdapter4(courseList1);

            recyclerView.setAdapter( adapter);


        }

        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }

        return view;
    }

    private void initCourse(){

        for (int i = 0;i < 2;i++){
            ECourse3 course1 = new ECourse3("Java",R.mipmap.java,R.drawable.xx,"北京大学");
            ECourse3 course2 = new ECourse3("计算机网络",R.mipmap.network,R.drawable.xx3,"东南大学");
            ECourse3 course3 = new ECourse3("微积分",R.mipmap.gaoc,R.drawable.xx,"北京交通大学");
            ECourse3 course4 = new ECourse3("web开发",R.mipmap.web,R.drawable.xx3,"北京林业大学");
            courseList1.add(course1);
            courseList1.add(course2);
            courseList1.add(course3);
            courseList1.add(course4);
        }
    }

}