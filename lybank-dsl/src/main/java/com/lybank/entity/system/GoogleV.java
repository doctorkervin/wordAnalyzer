package com.lybank.entity.system;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 谷歌验证码
 *
 * @author 阿布
 */
@Table(name = "SYSTEM_GOOGLEV")
@Entity
public class GoogleV implements Serializable {
    private static final long serialVersionUID = 3743714510260333504L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;
    @Column(unique = true)
    @Getter
    @Setter
    private String userName;
    @Getter
    @Setter
    private String secret;
    @Getter
    @Setter
    private String qrcodeStr;
    @Getter
    @Setter
    private String status;//是否开启

}
