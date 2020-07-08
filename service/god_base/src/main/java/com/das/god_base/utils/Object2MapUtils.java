package com.das.god_base.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class Object2MapUtils {
    public static Map<String, String> praseObject2Map(Object object) {

        Type type = new TypeToken<HashMap<String, String>>() {
        }.getType();
        return new Gson().fromJson(new Gson().toJson(object), type);
    }
}
