package com.lybank.service.system;

import com.lybank.config.interceptor.UserPrincipal;
import com.lybank.entity.system.QSystemLog;
import com.lybank.entity.system.SystemLog;
import com.lybank.entity.system.User;
import com.lybank.repository.system.SystemLogRep;
import com.lybank.util.code.SystemLogModel;
import com.querydsl.core.BooleanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class SystemLogService {
    @Autowired
    private SystemLogRep systemLogRep;
    @Autowired
    private UserService userService;

    /**
     * 插入日志
     *
     * @param type 操作类型
     */
    @Async
    @Transactional
    public void insertSystemLog(String type, String msg) {
        UserPrincipal user = userService.getCurrentUser();
        SystemLog systemLog = new SystemLog();
        systemLog.setType(type);
        systemLog.setTime(new Date());
        systemLog.setLoginName(user.getUser().getLoginName());
        systemLog.setName(user.getUser().getName());
        systemLogRep.save(systemLog);
    }

    @Async
    @Transactional
    public void insertSystemLog(String loginName, String type, String msg) {
        List<User> user = userService.findByLoginName(loginName);
        String name = "";
        if (user.size() > 0) {
            name = user != null ? user.get(0).getName() : "";
        }
        SystemLog systemLog = new SystemLog();
        systemLog.setType(type);
        systemLog.setTime(new Date());
        systemLog.setLoginName(loginName);
        systemLog.setName(name);
        systemLogRep.save(systemLog);
    }

    @Async
    @Transactional
    public void insertSystemLog(String loginName, String IP, String type, String msg) {
        List<User> user = userService.findByLoginName(loginName);
        String name = "";
        if (user.size() > 0) {
            name = user != null ? user.get(0).getName() : "";
        }
        SystemLog systemLog = new SystemLog();
        systemLog.setType(type);
        systemLog.setIP(IP);
        systemLog.setTime(new Date());
        systemLog.setLoginName(loginName);
        systemLog.setName(name);
        log.info(loginName, IP, type, msg);
        systemLogRep.save(systemLog);
    }

    /**
     * 查询数据
     *
     * @param sysLog
     * @param page
     */
    public Page<SystemLog> searchSystemLog(SystemLogModel sysLog, Pageable page) {
        QSystemLog qslog = QSystemLog.systemLog;
        BooleanBuilder builder = new BooleanBuilder();
        if (!StringUtils.isEmpty(sysLog.getIP())) {
            builder.and(qslog.IP.like("%" + sysLog.getIP() + "%"));
        }
        if (!StringUtils.isEmpty(sysLog.getLoginName())) {
            builder.and(qslog.loginName.like("%" + sysLog.getLoginName() + "%"));
        }
        if (!StringUtils.isEmpty(sysLog.getStartTime())) {
            builder.and(qslog.time.after(sysLog.getStartTime()));
        }
        if (!StringUtils.isEmpty(sysLog.getEndTime())) {
            builder.and(qslog.time.before(sysLog.getEndTime()));
        }
        return systemLogRep.findAll(builder, page);

    }

}
