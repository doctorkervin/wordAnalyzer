package com.lybank.controller.system;

import com.lybank.entity.system.WhitelistIp;
import com.lybank.service.system.WhitelistIpService;
import com.lybank.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author 阿布
 * @ClassName com.lybank.controller.system.WhitelistIpController
 * @Description 商户白名单IP限制请求类
 * @Date 2019-02-18
 * @Version 1.0
 */
@Controller
@RequestMapping("/system/whitelist/ip")
public class WhitelistIpController {
    private String path = "system/whitelistIp/";
    @Autowired
    private WhitelistIpService whitelistIpService;

    /**
     * 支付渠道分页查询
     *
     * @param model
     * @param param
     * @param page
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView getPageList(Model model, WhitelistIp param, @PageableDefault(value = 15, sort = {"id"}, direction = Sort.Direction.DESC) Pageable page) {
        ModelAndView mv = new ModelAndView(path + "whitelistIp_list");
        Page<WhitelistIp> pList = whitelistIpService.getPageList(param, page);
        model.addAttribute("list", pList);
        model.addAttribute("p", param);
        return mv;
    }


    /**
     * 根据ID查询数据,跳转查看详情页面
     *
     * @param model
     * @param id
     * @return
     */
//    @RequestMapping("/view")
//    public ModelAndView getView(Model model, Long id) {
//        ModelAndView mv = new ModelAndView(path + "whitelistIp_view");
//        WhitelistIp p = whitelistIpService.getWhitelistIpByIdProcess(id);
//        model.addAttribute("p", p);
//        return mv;
//    }

    /**
     * 根据ID查询数据,跳转到修改页面
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/edit")
    public ModelAndView getEdit(Model model, Long id) {
        ModelAndView mv = new ModelAndView(path + "whitelistIp_edit");
        WhitelistIp p = whitelistIpService.getWhitelistIpByIdProcess(id);
        model.addAttribute("p", p);
        model(model);
        return mv;
    }

    /**
     * 根据ID查询数据,跳转到新增页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/add")
    public ModelAndView getAdd(Model model) {
        model(model);
        return new ModelAndView(path + "whitelistIp_add");
    }

    private void model(Model model) {
        model.addAttribute("merchantInfoList", null);
    }

    /**
     * 添加或修改方法
     *
     * @param p
     * @return
     */
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public Result saveOrUpdate(WhitelistIp p) {
        return whitelistIpService.saveOrUpdate(p);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("/batchDel")
    @ResponseBody
    public Result batchDel(@RequestParam(value = "ids[]", required=false)List<Long> ids) {
        return whitelistIpService.batchDel(ids);
    }
}
