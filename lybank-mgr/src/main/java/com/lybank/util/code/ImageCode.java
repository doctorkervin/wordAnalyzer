package com.lybank.util.code;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 验证码信息类
 */
public class ImageCode {
    /**
     * 图片
     */
    // private @Getter @Setter BufferedImage image;
    /**
     * 随机数
     */
    @Getter
    @Setter
    private String code;
    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    public ImageCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    public ImageCode(String code, int expireIn) {
        this.code = code;
        //当前时间  加上  设置过期的时间
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public boolean isExpried() {
        //如果 过期时间 在 当前日期 之前，则验证码过期
        return LocalDateTime.now().isAfter(expireTime);
    }

}
