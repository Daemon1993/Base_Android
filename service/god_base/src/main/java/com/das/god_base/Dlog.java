package com.das.god_base;

import com.socks.library.KLog;

public class Dlog {

    public static void init(boolean isLog) {
        KLog.init(isLog, "V1R1");
    }

    public static void d(String msg) {
        KLog.d(msg);
    }

}
