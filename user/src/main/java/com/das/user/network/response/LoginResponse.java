package com.das.user.network.response;

import com.das.god_base.network.BaseResponseResult;
import com.google.gson.annotations.SerializedName;

public class LoginResponse extends BaseResponseResult {

    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data{
        /**
         * access_token : mti_l_8fkY5cpelBKnp8hq3-n-jkhTSIyZrG79ow1S1RRJxyIwqxxdjDBXbxAzfnH-SnkDjJ6oqiYbAiabqICKYD66s7i7hiySbiq-KNICvtYW8-ydY0q8MDs3V2s-tkGvklGZ3w0jZGnZZTcsGloGoJK2FXCDstj15llfNT6r8eJRJo30xLuoU1go7ZvD4HAcnzAc6y8Es2E1yMgQW9WDDjR5Ir21cNN2Nh2W--aQ8PO9viPFkjvl0R0TjEOiuteateAVWCMfumzOpYUNpdTQ_O1KRqP2UVn7BWva9B8z1ANltKyBjNRdXW0OTTIukmm4MOkJUX5hRJTGdd7IWdDTZMpC6FnyuKu9uoOmaqJEC6ZNF_fHToOiq_OdKkwtXurhjZxFzLj23AMN9iB6dGwfWvRpsWfOJ10hEB4tpnmHU
         * token_type : bearer
         * expires_in : 7199
         * refresh_token : ba8013c729814652832ffdd928434abc
         * IsNotice : False
         * UserNo : admin
         * .issued : Wed, 08 Jul 2020 05:16:34 GMT
         * .expires : Wed, 08 Jul 2020 07:16:34 GMT
         */

        private String access_token;
        private String token_type;
        private int expires_in;
        private String refresh_token;
        private String IsNotice;
        private String UserNo;
        @SerializedName(".issued")
        private String _$Issued323; // FIXME check this code
        @SerializedName(".expires")
        private String _$Expires20; // FIXME check this code

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public String getToken_type() {
            return token_type;
        }

        public void setToken_type(String token_type) {
            this.token_type = token_type;
        }

        public int getExpires_in() {
            return expires_in;
        }

        public void setExpires_in(int expires_in) {
            this.expires_in = expires_in;
        }

        public String getRefresh_token() {
            return refresh_token;
        }

        public void setRefresh_token(String refresh_token) {
            this.refresh_token = refresh_token;
        }

        public String getIsNotice() {
            return IsNotice;
        }

        public void setIsNotice(String isNotice) {
            IsNotice = isNotice;
        }

        public String getUserNo() {
            return UserNo;
        }

        public void setUserNo(String userNo) {
            UserNo = userNo;
        }

        public String get_$Issued323() {
            return _$Issued323;
        }

        public void set_$Issued323(String _$Issued323) {
            this._$Issued323 = _$Issued323;
        }

        public String get_$Expires20() {
            return _$Expires20;
        }

        public void set_$Expires20(String _$Expires20) {
            this._$Expires20 = _$Expires20;
        }
    }

}
