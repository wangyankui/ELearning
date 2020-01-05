package com.example.a777;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.transition.Explode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.animationUtils.TransitionController;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Map;

public class  LoginActivity extends AppCompatActivity {

    EditText etUsername;
    EditText etPassword;
    CardView cv;
    FloatingActionButton fab;

    private ImageView image;
    private EditText et;
    private Button submit;
    private  TextView textv;
    private String codeStr;
    private CodeUtils codeUtils;
    private SharedHelper sh;
    private Context mContext;
    private String userName,psw,spPsw;
    private TextView tv_find_psw;

    private String strname;
    private String strpasswd;
    private TextView otherway;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //getWindow().setEnterTransition(new Slide().setDuration(2000));
        getWindow().setEnterTransition(new Explode().setDuration(1000));
        getWindow().setExitTransition(new Explode().setDuration(1000));
        initView();
       getWindow().setEnterTransition(new Explode().setDuration(2000));
       getWindow().setExitTransition(new Explode().setDuration(2000));

    }



    private void initView() {

        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        //TextView tv_find_psw = (TextView) findViewById(R.id.tv_find_psw);
        cv = (CardView) findViewById(R.id.cv);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        image = (ImageView) findViewById(R.id.image);
        et = (EditText) findViewById(R.id.yzm);
        textv = (TextView) findViewById(R.id.textView6);
        submit = (Button) findViewById(R.id.bt_go);

        //   otherway = (TextView)findViewById(R.id.textView14);
        mContext = getApplicationContext();
        sh = new SharedHelper(mContext);
        codeUtils = CodeUtils.getInstance();
        Bitmap bitmap = codeUtils.createBitmap();
        image.setImageBitmap(bitmap);
/*
        tv_find_psw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到找回密码界面（此页面暂未创建）
            }
        });*/
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        textv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codeUtils = CodeUtils.getInstance();
                Bitmap bitmap = codeUtils.createBitmap();
                image.setImageBitmap(bitmap);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codeStr = et.getText().toString().trim();
                Log.e("codeStr", codeStr);
                userName=etUsername.getText().toString().trim();
                psw=etPassword.getText().toString().trim();
                String md5Psw= MD5Utils.md5(psw);//对当前用户输入的密码进行MD5加密再进行比对判断
                spPsw=readPsw(userName);//从SharedPreferences中根据用户名读取密码
                if(TextUtils.isEmpty(userName)){
                    Toast.makeText(LoginActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(psw)){
                    Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                } else if(TextUtils.isEmpty(codeStr)){
                    Toast.makeText(LoginActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
                    return;
                }
                String code = codeUtils.getCode();
                Log.e("code", code);
                if(md5Psw.equals(spPsw)&&code.equalsIgnoreCase(codeStr)){
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    //保存登录状态
                    saveLoginStatus(true, userName);
                    //登录成功后关闭此页面进入主页
                    Intent data=new Intent();
                    data.putExtra("isLogin",true);
                    setResult(RESULT_OK,data);
                    LoginActivity.this.finish();
                    startActivity(new Intent(LoginActivity.this, Main2Activity.class));
                    return;
                }else if((spPsw!=null&&!TextUtils.isEmpty(spPsw)&&!md5Psw.equals(spPsw))){
                    Toast.makeText(LoginActivity.this, "输入的用户名和密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                }else if(!code.equalsIgnoreCase(codeStr)){
                    Toast.makeText(LoginActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LoginActivity.this, "此用户名不存在", Toast.LENGTH_SHORT).show();
                }
                et.setText("");
            }
        });



    }
    private String readPsw(String userName){
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        return sp.getString(userName , "");
    }
    private void saveLoginStatus(boolean status,String userName){
        //loginInfo表示文件名
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();//获取编辑器
        editor.putBoolean("isLogin", status);//存入boolean类型的登录状态
        editor.putString("loginUserName", userName);//存入登录状态时的用户名
        editor.commit();//提交修改
    }
    /**
     * 注册成功的数据返回至此
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            //从注册界面传递过来的用户名
            String userName =data.getStringExtra("userName");
            if(!TextUtils.isEmpty(userName)){
                etUsername.setText(userName);
                //设置光标的位置
                etUsername.setSelection(userName.length());
            }
        }
    }

   /* @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.fab:
                TransitionController.getInstance().startActivity(this,new Intent(this, RegisterActivity.class),fab,R.id.fab);
                break;
            case R.id.textView6:
                codeUtils = CodeUtils.getInstance();
                Bitmap bitmap = codeUtils.createBitmap();
                image.setImageBitmap(bitmap);

                break;
            case R.id.bt_go:
                codeStr = et.getText().toString().trim();
                Log.e("codeStr", codeStr);
                if (null == codeStr || TextUtils.isEmpty(codeStr)) {
                    Toast.makeText(this, "请输入验证码", 0).show();
                    return;
                }
                String code = codeUtils.getCode();
                Log.e("code", code);
                if (code.equalsIgnoreCase(codeStr)) {
                    Toast.makeText(this, "验证码正确", 0).show();
                    Intent intent = new Intent(LoginActivity.this,Main2Activity.class);

                    strname = etUsername.getText().toString();
                    strpasswd = etPassword.getText().toString();
                    sh.save(strname,strpasswd);

                    startActivity(intent);
                } else {
                    Toast.makeText(this, "验证码错误", 0).show();
                }
                et.setText("");
                break;
            default:
                break;
        }

    }*/

    @Override
    protected void onStart() {
        super.onStart();
        Map<String,String> data = sh.read();
        etUsername.setText(data.get("username"));
        etPassword.setText(data.get("passwd"));
    }
}
