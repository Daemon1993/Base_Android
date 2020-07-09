package com.das.user.utils;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.view.View;
import android.widget.RemoteViews;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.DownloadListener;
import com.androidnetworking.interfaces.DownloadProgressListener;
import com.das.god_base.utils.FileUtils;
import com.das.user.R;
import com.socks.library.KLog;

import java.io.File;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

/**
 * 下载工具类（开发中一般用于APK应用升级）
 */
public class LinDownloadAPk {

    private static RemoteViews mNotifiviews;

    private static PendingIntent nullIntent;
    private static Context mContext;
    private static String last_path;

    /**
     * 判断8.0 安装权限
     */
    public static void installApk(Context context, String apkPath) {
        mContext = context;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { //系统 Android O及以上版本
            //是否需要处理未知应用来源权限。 true为用户信任安装包安装 false 则需要获取授权
            boolean canRequestPackageInstalls = mContext.getPackageManager().canRequestPackageInstalls();
            if (canRequestPackageInstalls) {
                installAppIntent(context, apkPath);
            } else {

                startInstallPermissionSettingActivity();
            }
        } else {  //直接安装流程
            installAppIntent(context, apkPath);
        }


    }

    /**
     * 开启安装APK权限(适配8.0)
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void startInstallPermissionSettingActivity() {
        Uri packageURI = Uri.parse("package:" + mContext.getPackageName());
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
        mContext.startActivity(intent);
    }


    /**
     * 调往系统APK安装界面（适配7.0）
     *
     * @return
     */
    public static void installAppIntent(Context context, String filePath) {
        //apk文件的本地路径
        File apkfile = new File(filePath);
        if (!apkfile.exists()) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri contentUri = getUriForFile(apkfile);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        }
        intent.setDataAndType(contentUri, "application/vnd.android.package-archive");

        context.startActivity(intent);
    }

    /**
     * 将文件转换成uri
     *
     * @return
     */
    public static Uri getUriForFile(File file) {
        KLog.e(mContext.getPackageName());
        Uri fileUri = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            fileUri = FileProvider.getUriForFile(mContext, mContext.getPackageName() + ".fileprovider", file);
        } else {
            fileUri = Uri.fromFile(file);
        }
        return fileUri;
    }
}