package com.dxslm.okhttp.gson;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fxn on 2017/12/8.
 */

public class JsonGson {

    /**解析单个对象*/
    public static <T> T getObject(String jsonString,Class<T> cls) {
        T t = null;
        try{
            Gson gson = new Gson();
            t = gson.fromJson(jsonString,cls);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     *  使用Gson解析类型为List<Object>的对象
     */
    public static <T> List<T> getListObjects(String jsonString,Class<T> cls) {
        List<T> list = new ArrayList();
        try{
            Type type = new TypeToken<ArrayList<JsonObject>>(){}.getType();
            ArrayList<JsonObject> jsonObjects = new Gson().fromJson(jsonString, type);
            for (JsonObject jsonObject : jsonObjects){
                list.add(new Gson().fromJson(jsonObject, cls));
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
