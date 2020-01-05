package com.example.a777;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class welcomeActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //设置此界面为
        // 竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(1500);// 睡眠1500毫秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = hand.obtainMessage();
                hand.sendMessage(msg);
            }

        }.start();

        init();
    }

    private void init() {
        TextView tv_version = (TextView) findViewById(R.id.tv_version);
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            tv_version.setText("V" + packageInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            tv_version.setText("V");
        }

        //利用timer让此界面延迟3秒后跳转，timer有一个线程，该线程不断执行task
        Timer timer = new Timer();
        //TimerTask实现runnable接口，TimerTask类表示在一个指定时间内执行的task
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {//发送intent实现页面跳转，第一个参数为当前页面的context，第二个参数为要跳转的主页
                Intent intent = new Intent(welcomeActivity.this, LoginActivity.class);
                startActivity(intent);
                welcomeActivity.this.finish();//跳转后关闭当前欢迎页面
            }
        };
        timer.schedule(timerTask, 3000);//调度执行timerTask，第二个参数传入延迟时间（毫秒）

    }
//sharedPreference 判断用户是否第一次登录
    @SuppressLint("HandlerLeak")
    Handler hand = new Handler() {
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            if (isFristRun()) {
                // 如果是第一次启动程序则进入引导界面
                Intent intent = new Intent(welcomeActivity.this,
                        LoginActivity.class);
                startActivity(intent);
            } else {
                // 如果不是第一次启动则进入主页
                Intent intent = new Intent(welcomeActivity.this,
                        Main2Activity.class);
                startActivity(intent);
            }
            finish();
        };};
        // 判断是否是第一次启动程序 利用 SharedPreferences 将数据保存在本地
        private boolean isFristRun() {
            //实例化SharedPreferences对象（第一步）
            SharedPreferences sharedPreferences = welcomeActivity.this.getSharedPreferences(
                    "share", MODE_PRIVATE);
            //实例化SharedPreferences.Editor对象（第二步）
            boolean isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            if (!isFirstRun) {
                return false;
            } else {
                //保存数据 （第三步）
                editor.putBoolean("isFirstRun", false);
                //提交当前数据 （第四步）
                editor.commit();
                return true;
            }
        }

        public boolean onKeyDown(int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {

            }
            return true;
        }


    }

