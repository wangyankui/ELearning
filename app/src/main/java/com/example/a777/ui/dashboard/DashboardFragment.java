package com.example.a777.ui.dashboard;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a777.CourseAdapter;
import com.example.a777.CourseAdapter2;
import com.example.a777.CourseAdapter3;
import com.example.a777.CourseAdapter4;
import com.example.a777.ECourse;
import com.example.a777.ECourse2;
import com.example.a777.ECourse3;
import com.example.a777.LoginActivity;
import com.example.a777.R;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private List<ECourse3> courseList1 = new ArrayList<>();
    private List<ECourse2> courseList2 = new ArrayList<>();
    private  View view;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
       // View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
       // final TextView textView = root.findViewById(R.id.text_dashboard);
       // dashboardViewModel.getText().observe(this, new Observer<String>() {
        //    @Override
         //   public void onChanged(@Nullable String s) {
            //    textView.setText(s);
         //   }
       // });

        if(view==null) {
            view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_dashboard, null);

            initCourse();
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view3);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
            recyclerView.setLayoutManager(layoutManager);
            CourseAdapter4 adapter = new CourseAdapter4(courseList1);
            recyclerView.setAdapter( adapter);

            RecyclerView recyclerView2 = (RecyclerView) view.findViewById(R.id.recycler_view4);
            LinearLayoutManager layoutManager2 = new LinearLayoutManager(this.getActivity());
            layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView2.setLayoutManager(layoutManager2);
            CourseAdapter3 adapter3 = new CourseAdapter3(courseList2);
            recyclerView2.setAdapter( adapter3);

        }

        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }

        return view;
    }

    private void initCourse(){
        for(int i = 0;i < 2;i++) {
            ECourse2 course1 = new ECourse2("飞船课程1", R.drawable.fc1);

            courseList2.add(course1);
            ECourse2 course2 = new ECourse2("飞船课程2", R.drawable.fc2);

            courseList2.add(course2);
            ECourse2 course3 = new ECourse2("飞船课程3", R.drawable.fc3);

            courseList2.add(course3);
            ECourse2 course4 = new ECourse2("飞船课程4", R.drawable.fc4);

            courseList2.add(course4);
            ECourse2 course5 = new ECourse2("飞船课程5", R.drawable.fc5);

            courseList2.add(course5);
        }
        for (int i = 0;i < 2;i++){
            ECourse3 course1 = new ECourse3("课程一",R.drawable.christmas1,R.drawable.xx,"北京交通大学");
            ECourse3 course2 = new ECourse3("课程二",R.drawable.christmas2,R.drawable.xx3,"北京交通大学");
            ECourse3 course3 = new ECourse3("课程三",R.drawable.christmas3,R.drawable.xx,"北京交通大学");
            ECourse3 course4 = new ECourse3("课程四",R.drawable.christmas4,R.drawable.xx3,"北京交通大学");
            courseList1.add(course1);
            courseList1.add(course2);
            courseList1.add(course3);
            courseList1.add(course4);
        }
    }

}