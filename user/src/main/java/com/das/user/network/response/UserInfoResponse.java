package com.das.user.network.response;

import com.das.god_base.network.BaseResponseResult;

public class UserInfoResponse extends BaseResponseResult {


    /**
     * data : {"fullName":"管理员","mobile":"15500000000","userName":"admin"}
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
         * fullName : 管理员
         * mobile : 15500000000
         * userName : admin
         */

        private String fullName;
        private String mobile;
        private String userName;

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
