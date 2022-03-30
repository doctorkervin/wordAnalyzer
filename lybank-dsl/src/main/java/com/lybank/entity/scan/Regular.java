package com.lybank.entity.scan;

import com.lybank.entity.base.BaseModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "T_REGULAR_INFO")
public class Regular extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 规则名称
     */
    private String name;

    /**
     * 规则类型
     */
    private String type;

    /**
     * 规则code
     */
    private String code;

    /**
     * 规则值
     */
    private String value;

    //备注
    private String remark;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;//创建日期时间

    private Long createId;//创建人编号

    private Long operaterId;//操作人编号本系统登录人员操作后人员编号)

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operaterTime;//操作人时间(本系统登录人员操作后生成的时间)
}
