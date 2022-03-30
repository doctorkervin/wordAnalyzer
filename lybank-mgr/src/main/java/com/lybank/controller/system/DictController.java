package com.lybank.controller.system;

import com.lybank.entity.system.Dict;
import com.lybank.service.system.DictService;
import com.lybank.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/dict")
public class DictController {

    @Autowired
    private DictService dictService;
    private String path = "system/dict/";


    @RequestMapping("/list")
    public ModelAndView listDict(Model model, Dict p, @PageableDefault(value = 15, sort = {"id"}, direction = Sort.Direction.DESC) Pageable page) {
        Page<Dict> pList = dictService.listDict(p, page);
        model.addAttribute("list", pList);
        model.addAttribute("p", p);
        return new ModelAndView(path + "dict_list");
    }
    /**
     * 根据ID查询数据信息,跳转到查看详情页面
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/view")
    public ModelAndView getView(Model model, Long id) {
        ModelAndView mv = new ModelAndView(path + "dict_view");
        Dict p = dictService.getDictByIdProcess(id);
        model.addAttribute("p", p);
        return mv;
    }

    /**
     * 根据ID查询数据信息,跳转到新增页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/add")
    public ModelAndView getAdd(Model model) {
        return new ModelAndView(path + "dict_add");
    }


    /**
     * 根据ID查询数据信息,跳转到修改页面
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/edit")
    public ModelAndView getEdit(Model model, Long id) {
        ModelAndView mv = new ModelAndView(path + "dict_edit");
        Dict p = dictService.getDictByIdProcess(id);
        model.addAttribute("p", p);
        return mv;
    }
    /**
     * 添加或修改方法
     *
     * @param p
     * @return
     */
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public Result saveOrUpdate(Dict p) {
        return dictService.saveOrUpdate(p);
    }
    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("/batchDel")
    @ResponseBody
    public Result batchDel(Long[] ids) {
        return dictService.batchDel(ids);
    }

}
