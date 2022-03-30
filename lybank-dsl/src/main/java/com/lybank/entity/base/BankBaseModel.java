package com.lybank.entity.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author ABu
 * @ClassName com.lybank.entity.base
 * @Description 银行公共实体类
 * @Date 2019/6/22 14:49
 * @Version 1.0
 */
public class BankBaseModel extends BaseModel implements Serializable {
    //MD5加密代码，用于某个类+秘钥生成的MD5字符串。传递过来用于验证参数是否被拦截修改。
    @Getter
    @Setter
    private String verificationCode;
}
