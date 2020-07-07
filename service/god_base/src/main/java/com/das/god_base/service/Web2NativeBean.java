package com.das.god_base.service;

import android.text.TextUtils;

/**
 * H5调用原生 统一json字符串
 * type 代表类型
 * video   上传视频
 * image   上传图片
 * file   上传文件
 * type > refresh  value->id     刷新保养标准

 * preview  imageView,fileView,videoView   预览   value json
 *
 */
public class Web2NativeBean {


    private String type;
    private String value;
    private String fn;

    public String getFn() {
        return fn;
    }

    public void setFn(String fn) {
        this.fn = fn;
    }

    public Web2NativeBean(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public boolean isVideo(){
        return TextUtils.equals(type,"video");
    }
    public boolean isImage(){
        return TextUtils.equals(type,"image");
    }

    public boolean isFile(){
        return TextUtils.equals(type,"file");
    }
    public boolean isPreview() {
      return  type.contains("view");
    }

    public boolean isRefresh() {
      return  type.contains("refresh");
    }





    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
