package com.lybank.util.code;

import com.lybank.entity.system.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UserModel extends User {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Getter
    @Setter
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Getter
    @Setter
    private Date endTime;


}
