package com.das.god_base.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;


import com.socks.library.KLog;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileUtils {





    public static String save_download_image(Context context, Bitmap bitmap, String fileName) {

        File imageFile = getDownloadImageSaveLocal(fileName, context);
        try {
            //图片沙盒文件夹
            FileOutputStream fileOutputStream = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            return "";
        }
        return imageFile.getAbsolutePath();
    }

    //查询沙盒中的指定图片
//先指定哪个沙盒子文件夹，再指定名称
    public File[] queryAllPathByLocalType(Context context, String environmentType) {

        try {
            //指定沙盒文件夹
            File picturesFile = context.getApplicationContext().getExternalFilesDir(environmentType);


            if ( picturesFile.exists() && picturesFile.isDirectory()) {
                return picturesFile.listFiles();
            }
        } catch (Exception e) {
        }
        return null;
    }




    @NotNull
    private static File getDownloadImageSaveLocal(String fileName, Context context) {
        File root_dowload = context.getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        File imageFileDirctory = new File(root_dowload + "/images");
        if (!imageFileDirctory.exists()) {
            imageFileDirctory.mkdir();
        }
        return new File(imageFileDirctory, fileName);
    }

    public static String copyNio(Context context, File from, String filename) {
        FileChannel input = null;
        FileChannel output = null;

        File imageFile = getDownloadImageSaveLocal(filename, context);


        try {
            input = new FileInputStream(from).getChannel();
            output = new FileOutputStream(imageFile).getChannel();
            output.transferFrom(input, 0, input.size());
        } catch (Exception e) {
            KLog.e("error occur while copy" + e.getMessage());
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return imageFile.getAbsolutePath();
    }

}
