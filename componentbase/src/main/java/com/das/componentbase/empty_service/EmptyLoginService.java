package com.das.componentbase.empty_service;

import com.das.componentbase.service.ILoginService;

import com.socks.library.KLog;

public class EmptyLoginService implements ILoginService {
    @Override
    public void initAction() {
        KLog.d("EmptyLoginService initAction");
    }
}
