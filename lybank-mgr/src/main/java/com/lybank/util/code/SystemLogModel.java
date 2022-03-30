package com.lybank.util.code;

import com.lybank.entity.system.SystemLog;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class SystemLogModel extends SystemLog {
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Getter
    @Setter
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Getter
    @Setter
    private Date endTime;

}
