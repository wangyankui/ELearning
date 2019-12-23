package com.example.a777;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class pythonActivity extends AppCompatActivity {



    private TextView textView;
    protected String courseResult;



    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_python);
        textView = (TextView)findViewById(R.id.textView12);

        //过渡动画
        getWindow().setEnterTransition(new Fade().setDuration(2000));
        getWindow().setExitTransition(new Fade().setDuration(2000));

        final VideoView videoView = (VideoView)this.findViewById(R.id.videoView);
        //加载指定的视频文件
        String path = "http://techslides.com/demos/sample-videos/small.mp4";
        String uri = "http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4";
        Toast.makeText(this,path,Toast.LENGTH_SHORT).show();
       //videoView.setVideoURI(Uri.parse(path));
        //videoView.setVideoURI(Uri.parse("http://f3.3g.56.com/15/15/JGfMspPbHtzoqpzseFTPGUsKCEqMXFTW_smooth.3gp"));
       videoView.setVideoURI(Uri.parse(uri));
        //创建MediaController对象
        MediaController mediaController = new MediaController(this);

        //VideoView与MediaController建立关联
        videoView.setMediaController(mediaController);
        videoView.setBackgroundColor(Color.TRANSPARENT);

        //让VideoView获取焦点
        videoView.requestFocus();
        videoView.start();
        //textView.setText(new String("hhh"));


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url1 = "http://169.254.5.82:8080/elearn/courses/001";
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
        textView.append(strings);

    }

}
