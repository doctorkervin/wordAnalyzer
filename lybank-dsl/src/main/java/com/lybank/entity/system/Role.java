package com.lybank.entity.system;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色类
 *
 * @author 阿布
 */
@Entity
@Table(name = "SYSTEM_ROLE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "role")
@Cacheable(true)
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = 4541947001173935484L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//编号
    private String name;//名字
    @Column(unique = true)
    private String code;//字段名
    private String description;//描述
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonManagedReference
    @JoinTable(name = "tb_role_authority", joinColumns = @JoinColumn(name = "tb_role_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "tb_authority_id", referencedColumnName = "id"))
    private List<Authority> authorities = new ArrayList<Authority>();

}
