package com.example.a777;

import android.os.Handler;
import android.os.Message;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class getJson {
    //访问的url地址

    private String url="";
    public static final int PARSESUCCWSS=0x2016;
    private Handler handler;
    public getJson(Handler handler) {
        this.handler=handler;
    }
    /**
     * 获取网络上的json
     */
    public void getJsonFromInternet () {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpURLConnection conn=(HttpURLConnection) new URL(url).openConnection();
                    conn.setConnectTimeout(3000);
                    conn.setRequestMethod("GET");

                    if (conn.getResponseCode()==200) {
                        InputStream inputStream=conn.getInputStream();
                        List<CourseJson> listCourse=parseJson(inputStream);
                        if (listCourse.size()>0) {
                            Message msg=new Message();
                            msg.what=PARSESUCCWSS;//通知UI线程Json解析完成
                            msg.obj=listCourse;//将解析出的数据传递给UI线程
                            handler.sendMessage(msg);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    /**
     * 解析json格式的输入流转换成List
     */
    protected List<CourseJson> parseJson(InputStream inputStream)throws Exception {
        List<CourseJson>listNews=new ArrayList<CourseJson>();
        byte[]jsonBytes=convertIsToByteArray(inputStream);
        String json=new String(jsonBytes);
        try {
            JSONArray jsonArray=new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jObject=jsonArray.getJSONObject(i);
                 String id  =jObject.getString("sharedUrl");
                 String name =jObject.getString("name");
                 String code = jObject.getString("code");
                 String categoryId=jObject.getString("cate");
                String description=jObject.getString("description");
                String price=jObject.getString("price");

                String openDate;

                 String level;

                CourseJson news=new CourseJson(id, name, description,code,price,categoryId);
                listNews.add(news);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listNews;
    }
    /**
     * 将输入流转化成ByteArray
     */
    private byte[] convertIsToByteArray(InputStream inputStream) {
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        byte buffer[]=new byte[1024];
        int length=0;
        try {
            while ((length=inputStream.read(buffer))!=-1) {
                baos.write(buffer, 0, length);
            }
            inputStream.close();
            baos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }
}
