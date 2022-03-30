package com.lybank.entity.system;

import com.lybank.entity.base.BaseModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author 阿布
 * @ClassName com.lybank.entity.system.WhitelistIp
 * @Description 公司=商户白名单IP限制实体类
 * @Date 2019-02-18
 * @Version 1.0
 */
@Entity
@Table(name = "SYSTEM_WHITELIST_IP")
@Data
public class WhitelistIp extends BaseModel implements Serializable {
    private static final long serialVersionUID = 6972722063298767745L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //商户代码
    private String merchantCode;
    //ip地址
    private String ip;
    //控制字段 1启用 2停用
    private Integer isEnable;
    //备注
    private String remark;
    //1后台，2接口
    private Integer type;
    //操作状态 1新增， 2修改
    private Integer status;
    //创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private Long createId;//创建人ID
    //修改时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operaterTime;

    private Long operaterId;//操作人ID

}
