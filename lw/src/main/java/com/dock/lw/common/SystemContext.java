package com.dock.lw.common;


import com.dock.lw.common.enums.SystemContextType;

import java.util.HashMap;
import java.util.Map;

public class SystemContext {

    private static ThreadLocal<Map<String, Object>> holder = new ThreadLocal<>();

    public static Map<String, Object> getMap() {
        return holder.get();
    }

    public static void setMap(Map<String, Object> map) {
        holder.set(map);
    }

    public Object getValue(String key) {
        return holder.get().get(key);
    }

    /**
     * 获取当前信息
     * @return
     */
    public static <T> T getCurrentValue(SystemContextType type, Class<T> clazz) {
        Map<String, Object> map = getMap();
        if(map == null) {
            return null;
        }
        return (T) map.get(type.value());
    }

    /**
     * 设置当前信息
     * @param t
     * @return
     */
    public static <T> void setCurrentValue(SystemContextType type, Class<T> clazz, T t) {
        Map<String, Object> map = getMap();
        if(map == null) {
            map = new HashMap<>();
        }
        map.put(type.value(), t);

        setMap(map);
    }

    /**
     * 清空当前用户信息
     */
    public static void clearCurrentValue(SystemContextType type) {
        Map<String, Object> map = getMap();
        if(map != null) {
            map.remove(type.value());
        }
    }



}
