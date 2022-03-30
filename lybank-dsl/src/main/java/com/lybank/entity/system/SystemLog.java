package com.lybank.entity.system;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统日志
 *
 * @author 阿布
 */
@Entity
@Table(name = "SYSTEM_LOG")
@DynamicUpdate(true)
@DynamicInsert(true)
@Data
public class SystemLog implements Serializable {
    private static final long serialVersionUID = 7357841050750850111L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//编号
    private String loginName;//帐号
    private String name;//名字
    private String IP; //ip地址
    private Date time; //时间
    private String type; //操作类型
    private String remark;//备注

}
