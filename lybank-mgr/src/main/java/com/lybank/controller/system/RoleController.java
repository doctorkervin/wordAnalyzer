package com.lybank.controller.system;

import com.lybank.entity.system.Authority;
import com.lybank.entity.system.Role;
import com.lybank.service.system.AuthorityService;
import com.lybank.service.system.RoleService;
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
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthorityService authorityService;
    private String path = "system/role/";

    @ModelAttribute
    public void myModel(Model model) {
        //model.addAttribute("pCode", new RoleCode());
        //model.addAttribute("pService", authorityService);
        List<Authority> list = authorityService.getAuthoritysListByTypeAndParentId(AuthorityCode.MENUS, null);
        List<Authority> alist = authorityService.getAuthoritysListByTypeAndParentIdIsNotNull(AuthorityCode.MENUS);
        List<Authority> blist = authorityService.getAuthorityListByType(AuthorityCode.BUTTON);
		/*Map<String,Authority> maps=new HashMap<String,Authority>();
		for(Authority a:alist) {
			maps.put(a.getParent().i, value)
		}*/
        model.addAttribute("pMenus", list);
        model.addAttribute("aMenus", alist);
        model.addAttribute("button", blist);
    }
	
	/*@RequestMapping("/getPermission")
	@ResponseBody
	public Result getPermissionsByTypeAndType(Permission p) {a
		List<Permission> plist=permissionService.getPermissionListByTypeAndParentId(p.getType(),p.getParent().getId());
		return new Result().ResultSuccess().setObject(plist);
	}*/

    @RequestMapping("/list")
    public ModelAndView listRole(Model model, Role p, @PageableDefault(value = 15, sort = {"id"}, direction = Sort.Direction.DESC) Pageable page) {
        Page<Role> pList = roleService.listRole(p, page);
        model.addAttribute("list", pList);
        model.addAttribute("p", p);
        return new ModelAndView(path + "role_list");
    }

    @RequestMapping("/del")
    @ResponseBody
    public Result deleteRole(Model model, Long id) {
        if (StringUtils.isEmpty(id)) return new Result().ResultFail().setMsg("id 不能为空！");
        Role p = roleService.getRoleById(id);
        roleService.deleteRole(p);
        return new Result().ResultSuccess();
    }

    @RequestMapping("/batDel")
    @ResponseBody
    public Result batDeleteRole(Model model, Long[] ids) {
        if (ids.length <= 0) return new Result().ResultFail().setMsg("ids 不能为空！");
        List<Long> idsList = Arrays.asList(ids);
        List<Role> ps = roleService.delAll(idsList);
        return new Result().ResultSuccess().setObject(ps);
    }

    @RequestMapping("/edit")
    public ModelAndView getEdit(Model model, Long id) {
        Role p = roleService.getRoleById(id);
        model.addAttribute("p", p);
        model.addAttribute("aList", p.getAuthorities());
        return new ModelAndView(path + "role_edit");
    }

    @RequestMapping("/add")
    public ModelAndView add(Model model, Long id) {
        //查询顶级菜单
        //List<Authority> authorities=
        //Role p=roleService.getRoleById(id);
        //model.addAttribute("p",p);
        return new ModelAndView(path + "role_add");
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public Result add(Model model, Role p, Long[] authorityIds) {
        //查询顶级菜单
        List<Authority> authorities = null;
        if (authorityIds != null && authorityIds.length > 0) {
            List<Long> auLongs = Arrays.asList(authorityIds);
            authorities = authorityService.findAllById(auLongs);
        }
        //Role p=roleService.getRoleById(id);
        //model.addAttribute("p",p);
        Role pp = roleService.addOrUpdateRoleWithAuthorities(p, authorities);
        return new Result().ResultSuccess().setObject(pp);
    }

}
