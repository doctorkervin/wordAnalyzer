package com.lybank.entity.system;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 字典表
 *
 * @author 阿布
 */
@Entity
@Table(name = "SYSTEM_DICT")
@DynamicInsert(true)
@DynamicUpdate(true)
@Data
public class Dict implements Serializable {
    private static final long serialVersionUID = -964606352304099715L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//编号
    @Column(unique = true, name = "d_key")
    private String key;//唯一编码
    @Column(name = "d_value")
    private String value;//值


}
