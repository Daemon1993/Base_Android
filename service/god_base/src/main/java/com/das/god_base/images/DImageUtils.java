package com.das.god_base.images;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.das.god_base.R;

import com.das.god_base.utils.AsyncUtls;
import com.das.god_base.utils.FileUtils;
import com.socks.library.KLog;

import java.io.File;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DImageUtils {

    public interface ImageActionCallBack{
        void doAction(File resource);
        void onError(String e);
    }
    private static final RequestOptions sharedOptions = new RequestOptions()
                        .placeholder(R.color.base_gray2)
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

    public static void showImage(Activity activity, String url, ImageView imageView){
        Glide.with(activity)
                .load(url)
                .apply(sharedOptions)
                .into(imageView);
    }
}
