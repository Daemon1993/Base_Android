package com.das.user.network.response;

import com.das.god_base.network.BaseResponseResult;

public class Splash2Response extends BaseResponseResult {

    /**
     * data : {"enable":true,"iamgeUrl":"/UploadFile/Image/FlyScreen/2020-07-08/91f9cc2a0cd546359f0c7ce219a9ab91.png","explain":"发鬼地方个放大国服","id":10,"remarks":null}
     * retCode : 0
     * message : ok
     */

    private DataBean data;


    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }



    public static class DataBean {
        /**
         * enable : true
         * iamgeUrl : /UploadFile/Image/FlyScreen/2020-07-08/91f9cc2a0cd546359f0c7ce219a9ab91.png
         * explain : 发鬼地方个放大国服
         * id : 10
         * remarks : null
         */

        private boolean enable;
        private String iamgeUrl;
        private String explain;
        private int id;
        private Object remarks;

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        public String getIamgeUrl() {
            return iamgeUrl;
        }

        public void setIamgeUrl(String iamgeUrl) {
            this.iamgeUrl = iamgeUrl;
        }

        public String getExplain() {
            return explain;
        }

        public void setExplain(String explain) {
            this.explain = explain;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getRemarks() {
            return remarks;
        }

        public void setRemarks(Object remarks) {
            this.remarks = remarks;
        }
    }
}
