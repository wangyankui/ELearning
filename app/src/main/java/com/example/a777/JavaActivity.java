package com.example.a777;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Fade;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;


public class JavaActivity extends AppCompatActivity {


    private Button buttonplaystop;
    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private final Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);

        //过渡动画
        getWindow().setEnterTransition(new Fade().setDuration(2000));
        getWindow().setExitTransition(new Fade().setDuration(2000));
        initView();


    }

    public void click(View view) {
        finish();
    }

    private void initView(){
        buttonplaystop = (Button)findViewById(R.id.button17);
        buttonplaystop.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                buttonClick();
            }
        });
        /**设置音频的位置**/
         mediaPlayer = MediaPlayer.create(this,R.raw.zjlaj);
         seekBar = (SeekBar)findViewById(R.id.seekBar1);
         seekBar.setMax(mediaPlayer.getDuration());

         seekBar.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 seekChange(v);  /*****调整进度条时调用的方法*****/
                 return false;
            }
         });


    }

    public void startPlayProgressUpdater(){

        seekBar.setProgress(mediaPlayer.getCurrentPosition());
        if(mediaPlayer.isPlaying()){
            Runnable notification = new Runnable() {
                @Override
                public void run() {
                    startPlayProgressUpdater();
                }
            };
            handler.postDelayed(notification,1000);
        }
        else{
            mediaPlayer.pause();
            buttonplaystop.setText((getString(R.string.play_str)));
        }
    }

    public void seekChange(View v){

        if(mediaPlayer.isPlaying()){
            SeekBar sb = (SeekBar)v;
            mediaPlayer.seekTo(sb.getProgress());
        }
    }

    public void buttonClick(){

        if(buttonplaystop.getText()==getString(R.string.play_str)){
            buttonplaystop.setText(R.string.pause_str);
            try{
                mediaPlayer.start();
                startPlayProgressUpdater();
            }catch (IllegalStateException e){
                mediaPlayer.pause();
            }
        }
        else{
            buttonplaystop.setText(getString(R.string.play_str));
            mediaPlayer.pause();
        }

    }

    /*
    public boolean onCreateOptionMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
    */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.string.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
