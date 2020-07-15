package com.das.user.utils;

import android.content.Context;
import android.net.Uri;
import android.os.Build;

import com.socks.library.KLog;

import java.io.File;

import androidx.core.content.FileProvider;

public class FileProviderUtils {
    /**
     * 将文件转换成uri
     *
     * @return
     */
    public static Uri getUriForFile(Context mContext, File file) {
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
