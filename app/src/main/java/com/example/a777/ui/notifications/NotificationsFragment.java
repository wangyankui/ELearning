package com.example.a777.ui.notifications;


import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.a777.LoginActivity;
import com.example.a777.Main2Activity;
import com.example.a777.MainActivity;
import com.example.a777.R;


public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
     /*   final TextView textView = root.findViewById(R.id.text_notifications);
        notificationsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/


        return root;
    }

    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        Button button3 = (Button)getActivity().findViewById(R.id.button3);

        button3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), LoginActivity.class);

                //overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
               // ActivityOptionsCompat compat = ActivityOptionsCompat.makeCustomAnimation(getActivity(),
               //         R.transition.activity_slide, R.transition.activity_fade);
               // ActivityCompat.startActivity(getActivity(),
                  //      new Intent(getActivity(), MainActivity.class), compat.toBundle());

                //overridePendingTransition(R.anim.leftin, R.anim.leftout);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                getActivity().getWindow().setEnterTransition(new Explode().setDuration(1000));
                getActivity().getWindow().setExitTransition(new Explode().setDuration(1000));
            }
        });


    }



}