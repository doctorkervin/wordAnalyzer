package com.lybank.controller.scan;

import com.lybank.entity.scan.Regular;
import com.lybank.service.scan.RegularService;
import com.lybank.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/regular/info")
public class RegularController {
    @Autowired
    private RegularService regularService;

    private String path = "regular/";

    /**
     * @param model 1 返回参数列表
     * @param param 2 请求参数
     * @param page  3 分页参数
     * @return org.springframework.web.servlet.ModelAndView
     * @Titel 分页查询
     * @Description 分页查询规则信息
     * @Author kervin
     * @DateTime 2019/6/21 18:25
     */
    @RequestMapping("/list")
    public ModelAndView getPageList(Model model, Regular param, @PageableDefault(value = 15, sort = {"id"}, direction = Sort.Direction.DESC) Pageable page) {
        ModelAndView mv = new ModelAndView(path + "regular_list");
        Page<Regular> pList = regularService.getPageList(param, page);
        model.addAttribute("list", pList);
        model.addAttribute("p", param);
        return mv;
    }

    @GetMapping(value = "/view")
    public ModelAndView getRegularInfo(Model model, Long id) {
        Regular r = regularService.findById(id);
        ModelAndView view = new ModelAndView(path + "regular_view");
        view.addObject("p", r);
        return view;
    }

    @GetMapping(value = "/edit")
    public ModelAndView getRegularEditInfo(Model model, Long id) {
        Regular r = regularService.findById(id);
        ModelAndView view = new ModelAndView(path + "regular_edit");
        view.addObject("p", r);
        return view;
    }

    /**
     * 新增规则页面
     *
     * @return
     */
    @GetMapping("/add")
    public ModelAndView addUserPage(Model m) {
        return new ModelAndView(path + "regular_add");
    }

    /**
     * 保存规则
     *
     * @return
     */
    @PostMapping("/save")
    @ResponseBody
    public Result save(Regular r) {
        return regularService.save(r);
    }

    /**
     * 修改规则
     *
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public Result update(Regular r) {
        return regularService.update(r);
    }
}
