package com.das.god_base;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.github.dfqin.grantor.PermissionListener;
import com.github.dfqin.grantor.PermissionsUtil;

import androidx.annotation.NonNull;


public class PermissionsUtils {


    public interface PermissonsCallback {
        void resultOK();
        default void resultFail(){

        };
    }

    /**
     * 存储权限
     *
     * @param activity
     * @param permissonsCallback
     */
    public static void requestStorage(final Activity activity, final PermissonsCallback permissonsCallback) {
        PermissionsUtil.TipInfo tip = new PermissionsUtil.TipInfo("注意:", "需要存储权限", "取消", "打开权限");
        PermissionsUtil.requestPermission(activity, new PermissionListener() {
            @Override
            public void permissionGranted(@NonNull String[] permissions) {
                permissonsCallback.resultOK();
            }

            @Override
            public void permissionDenied(@NonNull String[] permissions) {
                ToastUtils.toast("用户拒绝了存储权限");
                permissonsCallback.resultFail();
            }
        }, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, true, tip);
    }

    /**
     * 摄像头权限
     *
     * @param activity
     * @param permissonsCallback
     */
    public static void requestCAMERA(Context activity, final PermissonsCallback permissonsCallback) {
        PermissionsUtil.TipInfo tip = new PermissionsUtil.TipInfo("注意:", "需要摄像头权限", "取消", "打开权限");
        PermissionsUtil.requestPermission(activity, new PermissionListener() {
            @Override
            public void permissionGranted(@NonNull String[] permissions) {
                permissonsCallback.resultOK();
            }

            @Override
            public void permissionDenied(@NonNull String[] permissions) {
                ToastUtils.toast("用户拒绝了摄像头权限");
            }
        }, new String[]{Manifest.permission.CAMERA}, true, tip);
    }


    /**
     * 联系人权限
     *
     * @param activity
     * @param permissonsCallback
     */
    public static void requestCONTACTS(Activity activity, final PermissonsCallback permissonsCallback) {
        PermissionsUtil.TipInfo tip = new PermissionsUtil.TipInfo("注意:", "需要通讯录权限", "取消", "打开权限");
        PermissionsUtil.requestPermission(activity, new PermissionListener() {
            @Override
            public void permissionGranted(@NonNull String[] permissions) {
                permissonsCallback.resultOK();
            }

            @Override
            public void permissionDenied(@NonNull String[] permissions) {
                ToastUtils.toast("用户拒绝了通讯录权限");
            }
        }, new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.GET_ACCOUNTS}, true, tip);
    }


    /**
     * 位置权限
     *
     * @param activity
     * @param permissonsCallback
     */
    public static void requestLOCATION(Activity activity, final PermissonsCallback permissonsCallback) {
        PermissionsUtil.TipInfo tip = new PermissionsUtil.TipInfo("注意:", "需要地理位置权限", "取消", "打开权限");
        PermissionsUtil.requestPermission(activity, new PermissionListener() {
            @Override
            public void permissionGranted(@NonNull String[] permissions) {
                permissonsCallback.resultOK();
            }

            @Override
            public void permissionDenied(@NonNull String[] permissions) {
                ToastUtils.toast("用户拒绝了地理位置权限");
            }
        }, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, true, tip);
    }




    public static void requestVideoRecord(Context activity, final PermissonsCallback permissonsCallback) {
        PermissionsUtil.TipInfo tip = new PermissionsUtil.TipInfo("注意:", "需要视频录制权限", "取消", "打开权限");
        PermissionsUtil.requestPermission(activity, new PermissionListener() {
            @Override
            public void permissionGranted(@NonNull String[] permissions) {
                permissonsCallback.resultOK();
            }

            @Override
            public void permissionDenied(@NonNull String[] permissions) {
                Toast.makeText(activity,"用户拒绝了视频录制权限", Toast.LENGTH_SHORT).show();
            }
        }, new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, true, tip);
    }
    /**
     * 手机状态权限
     *
     * @param activity
     * @param permissonsCallback
     */
    public static void requestPHONE(Activity activity, final PermissonsCallback permissonsCallback) {
        PermissionsUtil.TipInfo tip = new PermissionsUtil.TipInfo("注意:", "需要读取手机状态权限", "取消", "打开权限");
        PermissionsUtil.requestPermission(activity, new PermissionListener() {
            @Override
            public void permissionGranted(@NonNull String[] permissions) {
                permissonsCallback.resultOK();
            }

            @Override
            public void permissionDenied(@NonNull String[] permissions) {
                ToastUtils.toast("用户拒绝了手机状态权限");
            }
        }, new String[]{Manifest.permission.READ_PHONE_STATE}, true, tip);
    }

    public static void requestInstanll(Activity activity, final PermissonsCallback permissonsCallback) {
        PermissionsUtil.TipInfo tip = new PermissionsUtil.TipInfo("注意:", "需要读取手机状态权限", "取消", "打开权限");
        PermissionsUtil.requestPermission(activity, new PermissionListener() {
            @Override
            public void permissionGranted(@NonNull String[] permissions) {
                permissonsCallback.resultOK();
            }

            @Override
            public void permissionDenied(@NonNull String[] permissions) {
                ToastUtils.toast("用户拒绝了手机状态权限");
            }
        }, new String[]{Manifest.permission.REQUEST_INSTALL_PACKAGES}, true, tip);
    }


    /*-------------------------------------------------------------------------------------------*/
    public static void go2CaptureActivity(final Context context, final Intent intent, final int scanninGrequestCode) {
        PermissionsUtils.requestCAMERA(context, new PermissionsUtils.PermissonsCallback() {
            @Override
            public void resultOK() {

                ((Activity) context).startActivityForResult(intent,
                        scanninGrequestCode);
            }


        });
    }

}
