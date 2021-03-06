package com.lybank.common.util;

import java.util.ResourceBundle;

/**
 * @author dingzhiwei jmdhappy@126.com
 * @version V1.0
 * @Description: 属性文件工具类
 * @date 2017-07-05
 * @Copyright: www.lypay.org
 */
public class PropertiesFileUtil {

    private ResourceBundle rb = null;

    public PropertiesFileUtil(String bundleFile) {
        rb = ResourceBundle.getBundle(bundleFile);
    }

    public static void main(String[] args) {


    }

    public String getValue(String key) {
        return rb.getString(key);
    }
}
