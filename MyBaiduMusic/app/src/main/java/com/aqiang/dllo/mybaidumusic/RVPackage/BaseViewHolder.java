package com.aqiang.dllo.mybaidumusic.RVPackage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.security.PublicKey;

/**
 * Created by dllo on 16/11/24.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {
    private static View view;
    private SparseArray<View> mSparseArray;
    private Context context;

    public BaseViewHolder(View itemView) {
        super(itemView);
        this.context = context;
        view = itemView;
        mSparseArray = new SparseArray<>();
    }
    public static BaseViewHolder createViewHolder(Context context, ViewGroup parent,int layoutId){
        view = LayoutInflater.from(context).inflate(layoutId,parent,false);
        return null;

    }
    public <T extends View> T getView(int layoutId){
        return null;
    }
}
