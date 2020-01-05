package com.example.a777.ui.home;

import android.os.Bundle;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.a777.Fragment1;
import com.example.a777.Fragment2;
import com.example.a777.Fragment3;
import com.example.a777.R;
import com.example.a777.ViewPagerAdapter;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    //private HomeViewModel homeViewModel;
    TabLayout tab;
    ViewPager viewpager;
    List<Fragment> fragments;
    String[] table = {"精选","计算机","理学"};
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        final  View view= inflater.inflate(R.layout.fragment_home,container,false);
        tab = view.findViewById(R.id.tabLayout);
        viewpager = view.findViewById(R.id.viewPager1);
        fragments = new ArrayList<Fragment>();
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        fragments.add(new Fragment3());
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(),fragments,table);
        viewpager.setAdapter(adapter);
        tab.setupWithViewPager(viewpager);
        SearchView searchView = view.findViewById(R.id.searchView);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        Fragment mContent;
        /** 修改显示的内容 不会重新加载 **/

        //设置搜索框直接展开显示。左侧有放大镜(在搜索框中) 右侧有叉叉 可以关闭搜索框

        /* homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        getActivity().getWindow().setEnterTransition(new Explode().setDuration(2000));
        getActivity().getWindow().setExitTransition(new Explode().setDuration(2000));
        return view;
    }

}