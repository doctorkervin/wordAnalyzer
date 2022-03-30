package com.lybank.entity.system;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 权限
 *
 * @author 阿布
 */
@Entity
@Table(name = "SYSTEM_AUTHORITY")
@Data
public class Authority implements Serializable {
    private static final long serialVersionUID = 7194502808753090077L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//编号
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "parent_id", nullable = true)
    private Authority parent;//数据库表是parent_id编号， 根据此parent_id查询SYSTEM_AUTHORITY表的ID信息
    private String name;//名称
    @Column(unique = true)
    private String code;//字段
    private String type;//类型
    @ManyToMany(mappedBy = "authorities", fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Role> roles = new ArrayList<Role>();


}
