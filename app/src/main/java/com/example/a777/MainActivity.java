package com.example.a777;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.annotation.SuppressLint;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private ImageView image;
    private EditText et;
    private Button submit;
    private  TextView textv;
    private String codeStr;
    private CodeUtils codeUtils;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = (Button)findViewById(R.id.button2);

        /*button1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);


            }
        });
        */
        initView();
        getWindow().setEnterTransition(new Slide().setDuration(2000));
        getWindow().setExitTransition(new Slide().setDuration(2000));
    }


    /*@Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityCompat.finishAfterTransition(this);
    }
    */
    private void initView() {
        image = (ImageView) findViewById(R.id.image);
        et = (EditText) findViewById(R.id.editText3);
        textv = (TextView) findViewById(R.id.textView4);
        submit = (Button) findViewById(R.id.button2);

        codeUtils = CodeUtils.getInstance();
        Bitmap bitmap = codeUtils.createBitmap();
        image.setImageBitmap(bitmap);

        textv.setOnClickListener(this);
        submit.setOnClickListener(this);

    }

    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.textView4:
                codeUtils = CodeUtils.getInstance();
                Bitmap bitmap = codeUtils.createBitmap();
                image.setImageBitmap(bitmap);

                break;
            case R.id.button2:
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
                    Intent intent = new Intent(MainActivity.this,Main2Activity.class);
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

}
