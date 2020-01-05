package com.example.a777;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebActivity extends AppCompatActivity {

    private TextView textView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        textView1 = (TextView)findViewById(R.id.textView13);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url1 = "  ";
                    URL url = new URL(url1);
                    //得到connection对象。
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    //设置请求方式
                    connection.setRequestMethod("GET");
                    //连接
                    connection.connect();
                    //得到响应码
                    int responseCode = connection.getResponseCode();
                    if(responseCode == HttpURLConnection.HTTP_OK){
                        //得到响应流
                        InputStream inputStream = connection.getInputStream();
                        //将响应流转换成字符串
                        String result = is2String(inputStream);//将流转换为字符串。
                        Log.d("kwwl","result============="+result);

                        jiexi(result);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void click(View view) {
        finish();
    }



    public static String is2String(InputStream in) throws IOException {
        StringBuffer out = new StringBuffer();
        InputStreamReader inread = new InputStreamReader(in, "UTF-8");
        char[] b = new char[4096];
        for (int n; (n = inread.read(b)) != -1;) {
            out.append(new String(b, 0, n));
        }
        return out.toString();
    }

    private void jiexi(String s) throws JSONException {//解析显示数据
        JSONObject object = new JSONObject(s);
        CourseJson  m = new CourseJson();
        System.out.println(s);
        String name = object.getString("name");
        String code = object.getString("code");
        String categoryId = object.getString("categoryId");
        String description = object.getString("description");
        m.setCode(code);
        m.setName(name);
        m.setCategoryId(categoryId);
        m.setDescription(description);
        final String strings = m.toString();
        textView1.setText(strings);

    }
}
