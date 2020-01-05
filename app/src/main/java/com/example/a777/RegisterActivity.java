package com.example.a777;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.animationUtils.TransitionController;
import com.example.animationUtils.ViewAnimationCompatUtils;
import com.example.listener.TransitionCustomListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/*
public class RegisterActivity extends AppCompatActivity {

    FloatingActionButton fab;
    CardView cvAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        cvAdd = (CardView) findViewById(R.id.cv_add);

        cvAdd.setVisibility(View.INVISIBLE);
        TransitionController.getInstance().setEnterListener(new TransitionCustomListener() {
            @Override
            public void onTransitionStart(Animator animator) {
                cvAdd.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onTransitionEnd(Animator animator) {
                animateRevealShow();
            }

            @Override
            public void onTransitionCancel(Animator animator) {

            }
        });
        TransitionController.getInstance().show(this,getIntent());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateRevealClose();
            }
        });
    }

    public void animateRevealShow() {
        Animator mAnimator = ViewAnimationCompatUtils.createCircularReveal(cvAdd, cvAdd.getWidth()/2,0, fab.getWidth() / 2, cvAdd.getHeight());
        mAnimator.setDuration(300);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                cvAdd.setVisibility(View.VISIBLE);
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    public void animateRevealClose() {
        Animator mAnimator = ViewAnimationCompatUtils.createCircularReveal(cvAdd,cvAdd.getWidth()/2,0, cvAdd.getHeight(), fab.getWidth() / 2);
        mAnimator.setDuration(300);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                cvAdd.setVisibility(View.INVISIBLE);
                super.onAnimationEnd(animation);
                fab.setImageResource(R.drawable.plus);
//                RegisterActivity.super.onBackPressed();
                TransitionController.getInstance().exitActivity(RegisterActivity.this);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }
    @Override
    public void onBackPressed() {
        animateRevealClose();
    }

}
*/
public class RegisterActivity extends AppCompatActivity {

    //提取全局变量:Ctrl+Alt+F

    //标题
    private TextView tv_main_title;
    //返回按钮
    private TextView tv_back;
    //注册按钮
    private Button btn_register;
    //账号、密码、再次输入的密码的控件
    private EditText et_user_name,et_psw,et_psw_again;
    //账号、密码、再次输入的密码的控件的获取值
    private String userName,psw,pswAgain;
    //标题布局
    private RelativeLayout rl_title_bar;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    private void init() {
        //从main_title_bar.xml页面布局中获取对应的UI控件
      tv_main_title = (TextView) findViewById(R.id.tv_main_title);
       tv_main_title.setText(" 注册 ");
       tv_back = (TextView) findViewById(R.id.tv_back);
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.TRANSPARENT);
        //从activity_register.xml页面布局中获得对应的UI控件
        btn_register=(Button) findViewById(R.id.bt_go);
        et_user_name=(EditText) findViewById(R.id.et_username);
        et_psw=(EditText) findViewById(R.id.et_password);
        et_psw_again=(EditText) findViewById(R.id.et_repeatpassword);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.this.finish();
            }
        });
       tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.this.finish();
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入在相应控件中的字符串
                getEditString();
                //判断输入框内容
                if(TextUtils.isEmpty(userName)){
                    Toast.makeText(RegisterActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(psw)){
                    Toast.makeText(RegisterActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(pswAgain)){
                    Toast.makeText(RegisterActivity.this, "请再次输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }else if(!psw.equals(pswAgain)){
                    Toast.makeText(RegisterActivity.this, "输入两次的密码不一样", Toast.LENGTH_SHORT).show();
                    return;
                }else if(isExistUserName(userName)){
                    Toast.makeText(RegisterActivity.this, "此账户名已经存在", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    //把账号、密码和账号标识保存到sp里面
                    saveRegisterInfo(userName, psw);
                    //注册成功后把账号传递到LoginActivity.java中
                    Intent data =new Intent();
                    data.putExtra("userName", userName);
                    setResult(RESULT_OK, data);
                    //RESULT_OK为Activity系统常量，状态码为-1，表示此页面下的内容操作成功将data返回到上一页面，如果是用back返回过去的则不存在用setResult传递data值
                    RegisterActivity.this.finish();
                }
            }
        });
    }

    /**
     * 获取控件中的字符串
     */
    private void getEditString(){
        userName=et_user_name.getText().toString().trim();
        psw=et_psw.getText().toString().trim();
        pswAgain=et_psw_again.getText().toString().trim();
    }

    /**
     *从SharedPreferences中读取输入的用户名，判断SharedPreferences中是否有此用户名
     */
    private boolean isExistUserName(String userName){
        boolean has_userName=false;
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        String spPsw=sp.getString(userName, "");//传入用户名获取密码
        if(!TextUtils.isEmpty(spPsw)) {//如果密码不为空则确实保存过这个用户名
            has_userName=true;
        }
        return has_userName;
    }

    /**
     * 保存账号和密码到SharedPreferences中
     */
    private void saveRegisterInfo(String userName,String psw){
        String md5Psw= MD5Utils.md5(psw);//把密码用MD5加密
        //loginInfo表示文件名
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();//获取编辑器
        //以用户名为key，密码为value保存在SharedPreferences中
        editor.putString(userName, md5Psw);
        editor.commit();//提交修改
    }
}
