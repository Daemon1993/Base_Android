package com.das.god_base.images;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.das.god_base.R;

import com.socks.library.KLog;

import java.io.File;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DImageUtils {

    public interface ImageActionCallBack{
        void doAction(File resource);
        void onError(String e);

    }

    public interface ImageActionCallBack1{
        void doAction(Bitmap bitmap);
    }
    private static final RequestOptions sharedOptions = new RequestOptions()
                        .placeholder(R.color.base_un_click)
                        .centerCrop();;


    public static void dowload_image2local(Context context, String url ,ImageActionCallBack imageActionCallBack){
        KLog.d("dowload_image2local "+url);

        RequestManager requestManager = Glide.with(context);
        RequestBuilder<File> fileRequestBuilder = requestManager.downloadOnly();
        Target<File> preload = fileRequestBuilder.load(url)
                .listener(new RequestListener<File>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<File> target, boolean isFirstResource) {
                        imageActionCallBack.onError(e.getMessage()+"");
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(File resource, Object model, Target<File> target, DataSource dataSource, boolean isFirstResource) {

                        imageActionCallBack.doAction(resource);

                        return false;
                    }
                }).preload();


    }
    public static void showImage(Fragment fragment, String url, ImageView imageView){
        Glide.with(fragment)
                .load(url)
                .apply(sharedOptions)
                .into(imageView);
    }

    public static void getBitmap_W_H(Activity activity,String url,ImageActionCallBack1 imageActionCallBack1){
        Glide.with(activity)
                .asBitmap()
                .load(url)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        //加载成功，resource为加载到的bitmap
                        KLog.d("  "+resource.getWidth()+"  "+resource.getHeight());

                        imageActionCallBack1.doAction(resource);
                    }
                });



    }


    public static void showImage(Activity activity, String url, ImageView imageView){
        Glide.with(activity)
                .load(url)
                .apply(sharedOptions)
                .into(imageView);
    }

    public static void showImage(Activity activity, File file, ImageView imageView){
        Glide.with(activity)
                .load(file)
                .apply(sharedOptions)
                .into(imageView);
    }
}
