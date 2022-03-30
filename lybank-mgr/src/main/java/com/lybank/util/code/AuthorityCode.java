package com.lybank.util.code;

import java.util.HashMap;
import java.util.Map;

public class AuthorityCode {

    public final static String BUTTON = "button";
    public final static String MENUS = "menus";
    public static Map<String, String> typeMap;

    static {
        typeMap = new HashMap<String, String>();
        typeMap.put(BUTTON, "按钮");
        typeMap.put(MENUS, "菜单");
    }

    public static String getStatus(String code) {
        return typeMap.get(code);
    }

}
