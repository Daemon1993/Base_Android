package com.das.componentbase;

import com.das.componentbase.empty_service.EmptyHomeService;
import com.das.componentbase.empty_service.EmptyUserService;
import com.das.componentbase.service.IHomeService;
import com.das.componentbase.service.IUserService;

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
    private IUserService iUserService;

    public void setHomeService(IHomeService iHomeService) {
        this.iHomeService = iHomeService;
    }

    public IHomeService getHomeService() {
        if(iHomeService==null){
            iHomeService=new EmptyHomeService();
        }
        return iHomeService;
    }


    public IUserService getUserService() {
        if(iUserService==null){
            return new EmptyUserService();
        }
        return iUserService;
    }

    public void setUserService(IUserService iLoginService) {
        this.iUserService = iLoginService;
    }
}
