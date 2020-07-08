package com.das.user.network.request;

public class UpdatePwd {
    public  String OldLoginPwd;
    public  String NewLoginPwd;
    public  String LoginName;

    public UpdatePwd(String oldLoginPwd, String newLoginPwd, String loginName) {
        OldLoginPwd = oldLoginPwd;
        NewLoginPwd = newLoginPwd;
        LoginName = loginName;
    }

    public String getOldLoginPwd() {
        return OldLoginPwd;
    }

    public void setOldLoginPwd(String oldLoginPwd) {
        OldLoginPwd = oldLoginPwd;
    }

    public String getNewLoginPwd() {
        return NewLoginPwd;
    }

    public void setNewLoginPwd(String newLoginPwd) {
        NewLoginPwd = newLoginPwd;
    }

    public String getLoginName() {
        return LoginName;
    }

    public void setLoginName(String loginName) {
        LoginName = loginName;
    }
}
