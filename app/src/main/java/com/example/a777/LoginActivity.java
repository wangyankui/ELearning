package com.example.a777;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.transition.Explode;
import android.transition.Slide;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.animationUtils.TransitionController;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

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
        cv = (CardView) findViewById(R.id.cv);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        image = (ImageView) findViewById(R.id.image);
        et = (EditText) findViewById(R.id.yzm);
        textv = (TextView) findViewById(R.id.textView6);
        submit = (Button) findViewById(R.id.bt_go);

        otherway = (TextView)findViewById(R.id.textView14);
        mContext = getApplicationContext();
        sh = new SharedHelper(mContext);
        codeUtils = CodeUtils.getInstance();
        Bitmap bitmap = codeUtils.createBitmap();
        image.setImageBitmap(bitmap);

        fab.setOnClickListener(this);
        textv.setOnClickListener(this);
        submit.setOnClickListener(this);



    }

    @SuppressLint("WrongConstant")
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

    }

    @Override
    protected void onStart() {
        super.onStart();
        Map<String,String> data = sh.read();
        etUsername.setText(data.get("username"));
        etPassword.setText(data.get("passwd"));
    }
}
