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


    /**
     * root dir
     * @param context
     * @return
     */
    public static String getDirPath(Context context){
        try {
            return context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            KLog.e("getDirPath "+e.getMessage());
            return context.getCacheDir().getAbsolutePath();
        }
    };

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

    public static void clearLocalCache(Context context) {
        File picturesFile =new File(getDirPath(context));
        deleteDir(picturesFile);

    }

    private static void deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                deleteDir(new File(dir, children[i]));
            }
        }
        dir.delete();
    }

    //查询沙盒中的指定图片
    //先指定哪个沙盒子文件夹，再指定名称
    public static long queryAllCacheFileSize(Context context) {
        //指定沙盒文件夹
        File picturesFile = new File(getDirPath(context));
        try {
            return getFolderSize(picturesFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;


    }

    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                // 如果下面还有文件
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);
                } else {
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }


    @NotNull
    private static File getDownloadImageSaveLocal(String fileName, Context context) {
        File root_dowload =new File(getDirPath(context)) ;
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
