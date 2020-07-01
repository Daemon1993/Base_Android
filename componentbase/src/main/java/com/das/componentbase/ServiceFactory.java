package com.das.componentbase;

import com.das.componentbase.empty_service.EmptyHomeService;
import com.das.componentbase.service.IHomeService;

public class ServiceFactory {

    private ServiceFactory() {
    }
    private static class Inner {
        private static ServiceFactory serviceFactory = new ServiceFactory();
    }
    /**
     * 通过静态内部类方式实现 ServiceFactory 的单例
     */
    public static ServiceFactory getInstance() {
        return Inner.serviceFactory;
    }

    private IHomeService iHomeService;

    public void setHomeService(IHomeService iHomeService) {
        this.iHomeService = iHomeService;
    }

    public IHomeService getHomeService() {
        if(iHomeService==null){
            iHomeService=new EmptyHomeService();
        }
        return iHomeService;
    }
}
