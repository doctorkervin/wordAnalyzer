package com.lybank.controller.system;

import com.lybank.entity.system.GoogleV;
import com.lybank.entity.system.Role;
import com.lybank.entity.system.User;
import com.lybank.service.system.GoogleVService;
import com.lybank.service.system.RoleService;
import com.lybank.service.system.UserService;
import com.lybank.util.Result;
import com.lybank.util.code.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

/**
 * 用户控制层业务
 *
 * @author xiaoqi 2018年10月3日
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private GoogleVService googleVService;
    private String path = "system/user/";

    /*@RequestMapping(value = "/user/{id}")
    @ResponseBody
    public User getUser(@PathVariable("id") Long id) {
        return userService.findById(id);
    }*/
    @ModelAttribute
    public void commModel(Model m) {
        List<Role> roles = roleService.findAll();
        m.addAttribute("roles", roles);
    }

    @GetMapping(value = "/resetPassword")
    public ModelAndView changePassword() {

        return new ModelAndView(path + "user_password_edit");
    }

    @PostMapping(value = "/resetPassword")
    @ResponseBody
    public Result changePassword(@RequestParam("id") Long id, @RequestParam("newpassword") String newpassword, @RequestParam("oldpassword") String oldpassword) {
        return userService.updatePwd(id, newpassword, oldpassword);
    }

    @GetMapping(value = "/userInfo/{id}")
    public ModelAndView getUserInfo(@PathVariable("id") Long id) {
        User u = userService.findById(id);
        ModelAndView view = new ModelAndView(path + "user_show");
        view.addObject("user", u);
        return view;
    }

    /**
     * 新增用户页面
     *
     * @return
     */
    @GetMapping("/admin-add")
    public ModelAndView addUserPage(Model m) {
    	model(m);
        return new ModelAndView(path + "user_add");
    }

    private void model(Model model) {
        List<User> userList = userService.findByUserList(1);
        model.addAttribute("users", userList);
    }

    /**
     * 新增用户页面
     *
     * @return
     * @throws UnsupportedEncodingException
     */
    @GetMapping("/admin-edit/{id}")
    public ModelAndView editUserPage(@PathVariable("id") Long id, Model model) throws UnsupportedEncodingException {
    	model(model);
        User u = userService.findById(id);
        //查询用户的二维码
        GoogleV gv = googleVService.getGoogleVByUserName(u.getLoginName());
        String qrcodeStr = "";
        if (gv != null) qrcodeStr = gv.getQrcodeStr();
        model.addAttribute("user", u);
        model.addAttribute("qrcodeStr", qrcodeStr);
        return new ModelAndView(path + "user_edit");
    }

    @RequestMapping("/admin-update")
    @ResponseBody
    public Result updateUser(User user) {
        if (user.getId() == null) return new Result().ResultFail().setMsg("用户ID不能为空");
        userService.updateUser(user);
        return new Result().ResultSuccess();
    }


    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/admin-save")
    @ResponseBody
    public Result addUser(User user) {
        if (StringUtils.isEmpty(user.getLoginName())) {
            return new Result().ResultFail();
        }
        //查询用户名
        List<User> ul = userService.findByLoginName(user.getLoginName());
        if (ul.size() > 0) {
            return new Result().ResultFail().setMsg("账户已存在!");
        }
        User u = userService.addUser(user);
        Result r = new Result().setObject(u).ResultSuccess();
        return r;

    }

    /**
     * 删除用户
     *
     * @param ids
     * @param m
     * @return
     */
    @RequestMapping(value = "/del")
    @ResponseBody
    public Result delUser(@RequestParam(value = "ids") Long[] ids, Model m) {
        List<Long> idsList = Arrays.asList(ids);
        userService.delUser(idsList);
        return new Result().ResultSuccess();
    }

    /**
     * 用户列表
     *
     * @param pageable
     * @param m
     * @return
     */
    @RequestMapping(value = "/admin-list")
    public ModelAndView adminListUser(
            @PageableDefault(value = 15, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable, UserModel user, Model m) {
        Page<User> pu = userService.searchUser(user, pageable);
        m.addAttribute("list", pu);
        m.addAttribute("user", user);
        return new ModelAndView(path + "user_list");
    }

    /**
     * 用户列表
     *
     * @param pageable
     * @param m
     * @return
     */
    @RequestMapping(value = "/admin-search")
    public ModelAndView searchUser(
            @PageableDefault(value = 15, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable, UserModel user, Model m) {
        Page<User> pu = userService.searchUser(user, pageable);
        m.addAttribute("list", pu);
        m.addAttribute("u", user);
        return new ModelAndView(path + "user_list");
    }

    @RequestMapping(value = "/resetGC")
    @ResponseBody
    public Result reset(Long id) {
        //查询用户
        User user = userService.findById(id);
        if (user == null) return new Result().ResultFail().setMsg("用户不存在");
        GoogleV gv = new GoogleV();
        gv.setUserName(user.getLoginName());
        gv = googleVService.updateGoogleV(gv);
        return new Result().ResultSuccess().setObject(gv).setMsg("验证码更新成功！");
    }
}
