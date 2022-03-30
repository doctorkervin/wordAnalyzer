package com.lybank.controller.system;

import com.lybank.entity.system.SystemLog;
import com.lybank.service.system.SystemLogService;
import com.lybank.util.code.SystemLogCode;
import com.lybank.util.code.SystemLogModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/systemlog")
public class SystemLogController {

    @Autowired
    private SystemLogService systemLogService;

    private String path = "system/systemLog/";

    /**
     * 用户列表
     *
     * @param pageable
     * @param m
     * @return
     */
    @RequestMapping(value = "/list")
    public ModelAndView adminListUser(
            @PageableDefault(value = 15, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable, SystemLogModel systemlog, Model m) {
        Page<SystemLog> pu = systemLogService.searchSystemLog(systemlog, pageable);
        m.addAttribute("list", pu);
        m.addAttribute("systemlog", systemlog);
        m.addAttribute("systemcode", new SystemLogCode());
        return new ModelAndView(path + "systemLog_list");
    }

}
