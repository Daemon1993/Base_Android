package com.das.user.out_service;

import com.das.componentbase.service.ILoginService;

import com.socks.library.KLog;

public class LoginServie implements ILoginService {
    @Override
    public void initAction() {
        KLog.d("下载过渡页配置数据 NOSQL  缓存过渡页本地 SP记录缓存图片Path 下次读取显示");

    }

}
