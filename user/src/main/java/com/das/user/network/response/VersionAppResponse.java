package com.das.user.network.response;

import com.das.god_base.network.BaseResponseResult;

public class VersionAppResponse extends BaseResponseResult {

    /**
     * data : {"mapPath":"/UploadFile/AndroidApp/2020-07-08/ab760b46e92d4dffad7b9de7ae10ffff.apk","versionName":"再来一个新的吧","major":1,"minor":0,"revision":55,"createTime":"2020-07-08T16:49:58.657","descriptive":"发生的范德萨发多少房东发多少房东发送到发送到房东电风扇发多少神盾水电费发递四方速递风格和风格覆盖发广告 覆盖地方好大声电风扇色核桃仁神盾规范更好地个地方大声国服还是刚发的规范化的是和很反感个","id":"ee1af228-1d3b-40df-aff2-82989aa002b4","isDeleted":false}
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
         * mapPath : /UploadFile/AndroidApp/2020-07-08/ab760b46e92d4dffad7b9de7ae10ffff.apk
         * versionName : 再来一个新的吧
         * major : 1
         * minor : 0
         * revision : 55
         * createTime : 2020-07-08T16:49:58.657
         * descriptive : 发生的范德萨发多少房东发多少房东发送到发送到房东电风扇发多少神盾水电费发递四方速递风格和风格覆盖发广告 覆盖地方好大声电风扇色核桃仁神盾规范更好地个地方大声国服还是刚发的规范化的是和很反感个
         * id : ee1af228-1d3b-40df-aff2-82989aa002b4
         * isDeleted : false
         */

        private String mapPath;
        private String versionName;
        private int major;
        private int minor;
        private int revision;
        private String createTime;
        private String descriptive;
        private String id;
        private boolean isDeleted;

        public String getMapPath() {
            return mapPath;
        }

        public void setMapPath(String mapPath) {
            this.mapPath = mapPath;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        public int getMajor() {
            return major;
        }

        public void setMajor(int major) {
            this.major = major;
        }

        public int getMinor() {
            return minor;
        }

        public void setMinor(int minor) {
            this.minor = minor;
        }

        public int getRevision() {
            return revision;
        }

        public void setRevision(int revision) {
            this.revision = revision;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDescriptive() {
            return descriptive;
        }

        public void setDescriptive(String descriptive) {
            this.descriptive = descriptive;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(boolean isDeleted) {
            this.isDeleted = isDeleted;
        }
    }
}
