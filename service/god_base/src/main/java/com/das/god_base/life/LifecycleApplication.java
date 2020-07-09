package com.das.god_base.life;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.socks.library.KLog;

public class LifecycleApplication implements Application.ActivityLifecycleCallbacks {
    private static int resumed;
    private static int paused;
    private static int started;
    private static int stopped;


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        ActivityManager.getInstance().addActivity(activity);
//        KLog.d(" onActivityCreated  "+ActivityManager.getInstance().getAllActivityStacks().size());
    }

    @Override
    public void onActivityStarted(Activity activity) {
        ++started;
//        XLog.d("onActivityStarted");
    }

    @Override
    public void onActivityResumed(Activity activity) {

//        XLog.d("onActivityResumed");
//        KLog.d("application onActivityResumed is in foreground: "+activity.getClass().getSimpleName() +" "+ (resumed > paused) );
        ++resumed;


    }

    @Override
    public void onActivityPaused(Activity activity) {
        ++paused;
//        KLog.d("application onActivityPaused is in foreground: " + (resumed > paused) +"  "+activity.getClass().getSimpleName());


    }

    @Override
    public void onActivityStopped(Activity activity) {
        ++stopped;

//        KLog.d("application onActivityStopped is visible: " + (started > stopped)+"  "+activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityDestroyed(Activity activity) {


        ActivityManager.getInstance().removeActivity(activity);

        KLog.d(" onActivityDestroyed  "+activity.getLocalClassName());
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    public static boolean isApplicationVisible() {
        return started > stopped;
    }



}
