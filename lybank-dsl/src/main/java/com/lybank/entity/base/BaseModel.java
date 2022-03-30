package com.lybank.entity.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author 阿布
 * @ClassName com.lybank.entity.base.BaseModel
 * @Description 公用实体类
 * @Date 2019-01-28
 * @Version 1.0
 */
public class BaseModel implements Serializable {
    /**
     * 开始时间
     */
    @Getter
    @Setter
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //@Transient//次注解用于不在启动时生成数据库表字段
    private Date startDate;
    /**
     * 结束时间
     */
    @Getter
    @Setter
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //@Transient//次注解用于不在启动时生成数据库表字段
    private Date endDate;
    /**
     * 编号集合
     */
    @Getter
    @Setter
    private List<Long> idList;
    /**
     * 公用模糊搜索变量
     */
    @Getter
    @Setter
    private String fuzzyValue;
//    /**
//     * 商户集合
//     */
//    @Getter
//    @Setter
//    private List<MchInfo> mchInfoList;
//
//    /**
//     * 第三方集合
//     */
//    @Getter
//    @Setter
//    private List<ChannelMchInfo> channelMchInfoList;


}
