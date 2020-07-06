package com.das.god_base.log;

import com.socks.library.KLog;

public class DLog {
    public static void d(String str){
        KLog.d(str);
    }

    public static void e(String str){
        KLog.e(str);
    }

    public static void init(String tag) {
        KLog.init(true,tag);
    }
}
