package com.lybank.controller.system;

import com.lybank.entity.system.Authority;
import com.lybank.service.system.AuthorityService;
import com.lybank.util.Result;
import com.lybank.util.code.AuthorityCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/authority")
public class AuthorityController {

    @Autowired
    private AuthorityService authorityService;
    private String path = "system/authority/";

    @ModelAttribute
    public void myModel(Model model) {
        List<Authority> listp = authorityService.getAuthorityListByType(AuthorityCode.MENUS);
        model.addAttribute("pCode", new AuthorityCode());
        model.addAttribute("pMenus", listp);
    }

    @RequestMapping("/list")
    public ModelAndView listAuthority(Model model, Authority p, @PageableDefault(value = 15, sort = {"id"}, direction = Sort.Direction.DESC) Pageable page) {
        Page<Authority> pList = authorityService.listAuthority(p, page);
        model.addAttribute("list", pList);
        model.addAttribute("p", p);
        return new ModelAndView(path + "authority_list");
    }

    @RequestMapping("/del")
    @ResponseBody
    public Result deleteAuthority(Model model, Long id) {
        if (StringUtils.isEmpty(id)) return new Result().ResultFail().setMsg("id 不能为空！");
        Authority p = authorityService.getAuthorityById(id);
        if (p == null) return new Result().ResultFail().setMsg("删除的对象不存在");
        List<Authority> plist = authorityService.getAuthorityListByParentId(p.getId());
        if (plist.size() > 0) return new Result().ResultFail().setMsg("请先删除子对象!");
        authorityService.deleteAuthority(p);
        return new Result().ResultSuccess();
    }

    @RequestMapping("/batDel")
    @ResponseBody
    public Result batDeleteAuthority(Model model, Long[] ids) {
        if (ids.length <= 0) return new Result().ResultFail().setMsg("ids 不能为空！");
        List<Long> idsList = Arrays.asList(ids);
        List<Authority> plist = authorityService.getAuthorityListByParentIdIn(idsList);
        if (plist.size() > 0) return new Result().ResultFail().setMsg("请先删除子对象!");
        List<Authority> ps = authorityService.delAll(idsList);
        return new Result().ResultSuccess().setObject(ps);
    }

    @RequestMapping("/edit")
    public ModelAndView getEdit(Model model, Long id) {
        Authority p = authorityService.getAuthorityById(id);
        model.addAttribute("p", p);

        return new ModelAndView(path + "authority_edit");
    }

    @RequestMapping("/add")
    public ModelAndView add(Model model, Long id) {
        //Authority p=authorityService.getAuthorityById(id);
        //model.addAttribute("p",p);
        return new ModelAndView(path + "authority_add");
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public Result add(Model model, Authority p) {
        //Authority p=authorityService.getAuthorityById(id);
        //model.addAttribute("p",p);
        Authority pp = authorityService.addOrUpdateAuthority(p);
        return new Result().ResultSuccess().setObject(pp);
    }

}
