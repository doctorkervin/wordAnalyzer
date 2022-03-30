package com.lybank.entity.system;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户类
 */
@Entity
@Table(name = "SYSTEM_USER")
@DynamicUpdate(true)
@DynamicInsert(true)
public class User implements Serializable {
    private static final long serialVersionUID = 7360390249720270545L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;//编号
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "parent_id", nullable = true)
    @Getter
    @Setter
    private User parent;//数据库表是parent_id编号， 根据此parent_id查询上级的ID信息
    @Getter
    @Setter
    private String name;//姓名
    @Getter
    @Setter
    private String pwd;//密码
    @Column(unique = true)
    @Getter
    @Setter
    private String loginName;//登录名
    @Getter
    @Setter
    private String sex;//性别
    @ManyToOne
    @JoinColumn(name = "role_id")
    @Getter
    @Setter
    private Role role;//角色,根据role_id查询SYSTEM_USER的Id信息，
    @Getter
    @Setter
    private Date operaterTime;//操作人时间(本系统登录人员操作后生成的时间)
    @Getter
    @Setter
    private Long operaterId;//操作人编号(本系统登录人员的操作用户编号,初始时间和创建时间相同就等于没操作)
    @Getter
    @Setter
    private Date createTime;//创建时间
    @Getter
    @Setter
    private Long createId;//创建人ID
    @Getter
    @Setter
    private String remark;//备注
    @Getter
    @Setter
    private Integer enabled = 1;//启用1，停用0
    @Column(columnDefinition = "tinyint(1) default 0")
    @Getter
    @Setter
    private Integer accountLocked = 0;//锁住1，不锁0
    @Column(columnDefinition = "tinyint(1) default 0")
    @Getter
    @Setter
    private Integer accountExpired = 0;//帐号过期1，否则0
    @Column(columnDefinition = "tinyint(1) default 0")
    @Getter
    @Setter
    private Integer credentialsExpired = 0;//凭证过期1，否则0


}
