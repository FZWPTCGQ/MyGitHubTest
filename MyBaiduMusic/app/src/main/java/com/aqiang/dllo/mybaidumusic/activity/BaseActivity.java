package com.aqiang.dllo.mybaidumusic.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.aqiang.dllo.mybaidumusic.R;

/**
 * Created by dllo on 16/11/22.
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        initViews();
        initDatas();

    }
   abstract int setLayout();
     abstract void initViews();

     abstract void initDatas();
    public   <T extends View> T bindView(int id){
        return (T)findViewById(id);
    }

}
