package com.kingja.qiang.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/7/20 9:49
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GsonUtil {

    public static <T> List<T> getList(String gonStr,Class<T> clazz) {
        List<T> list = new Gson().fromJson(gonStr, new TypeToken<List<T>>() {
        }.getType());
        return list;
    }
}
