package com.lybank.entity.scan;

import com.lybank.entity.base.BaseModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "T_TASK_INFO")
public class TaskInfo extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String scanner;//测试人员
    private Integer status;//任务状态 0 初始化 1运行中 2 结束
    private String remark; //任务描述
    private String fileType;//文件类型   2自定义路径，1上传json文件  3密钥文件扫描
    private String filePath; //文件路径
    private String regularIds; //规则id与，拼接的字符串

    @Lob
    private String result;//任务结果
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;//创建日期时间

    private Long createId;//创建人编号

    private Long operaterId;//操作人编号本系统登录人员操作后人员编号)

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operaterTime;//操作人时间(本系统登录人员操作后生成的时间)
    @Transient
    private List<Regular> regulars = new ArrayList<Regular>(); //规则集合
}
