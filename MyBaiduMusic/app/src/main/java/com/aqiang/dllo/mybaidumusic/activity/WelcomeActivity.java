package com.aqiang.dllo.mybaidumusic.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.AsyncListUtil;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.aqiang.dllo.mybaidumusic.R;
import com.aqiang.dllo.mybaidumusic.fragment.fatherfragment.MineFragment;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WelcomeActivity extends BaseActivity implements View.OnClickListener {

    private String path = "http://business.cdn.qianqian.com/qianqian/pic/bos_client_148030475690d03554476403dab6947436dee785b5.jpg";
    private ImageView mImageView;
    private TextView mBtn;
    private CountDownTimer mCountDownTimer;

    @Override
    protected int setLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initViews() {
        mImageView = (ImageView)findViewById(R.id.welcome_iv);
        mBtn = (TextView) findViewById(R.id.welcome_btn);
        mCountDownTimer = new CountDownTimer(5000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(intent);
               mCountDownTimer.cancel();
                /**
                 * 结束当前页面
                 */
                finish();
            }
        };
        mCountDownTimer.start();
    }

    @Override
    protected void initDatas() {
         new ImageTask().execute(path);
        mBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        mCountDownTimer.cancel();
        finish();
    }

    public class ImageTask extends AsyncTask<String,Void,Bitmap> {


        private Bitmap mBitmap;

        @Override
        protected Bitmap doInBackground(String... params) {
            String path = params[0];
            try {
                URL url = new URL(path);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                mBitmap = BitmapFactory.decodeStream(inputStream);
                inputStream.close();
                connection.disconnect();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return mBitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null){
               mImageView.setImageBitmap(bitmap);
            }
        }
    }
}
