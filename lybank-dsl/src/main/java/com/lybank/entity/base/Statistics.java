package com.lybank.entity.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author 阿布
 * @ClassName com.lybank.entity.base.Statistics
 * @Description 统计实体类
 * @Date 2019-01-30
 * @Version 1.0
 */
public class Statistics implements Serializable {
    private static final long serialVersionUID = -2015666403915522933L;
    @Setter
    @Getter
    private String lineStr;
    @Setter
    @Getter
    private String circleStr;
}