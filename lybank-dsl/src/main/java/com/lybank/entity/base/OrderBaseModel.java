package com.lybank.entity.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author ABu
 * @ClassName com.lybank.entity.base
 * @Description 订单公共类
 * @Date 2019/6/19 9:45
 * @Version 1.0
 */
public class OrderBaseModel extends BaseModel implements Serializable {
    //MD5加密代码，用于某个类+秘钥生成的MD5字符串。传递过来用于验证参数是否被拦截修改。
//    @Getter
//    @Setter
//    private String mdFiveCode;     //MD5加密代码，用于某个类+秘钥生成的MD5字符串。传递过来用于验证参数是否被拦截修改。


}
