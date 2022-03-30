package com.lybank.controller.scan;

import com.lybank.entity.scan.Regular;
import com.lybank.entity.scan.TaskInfo;
import com.lybank.service.scan.RegularService;
import com.lybank.service.scan.TaskInfoService;
import com.lybank.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/task/info")
public class TaskInfoController {
    private String path = "task/";
    @Autowired
    private TaskInfoService taskInfoService;
    @Autowired
    private RegularService regularService;

    /**
     * @param model 1 返回参数列表
     * @param param 2 请求参数
     * @param page  3 分页参数
     * @return org.springframework.web.servlet.ModelAndView
     * @Titel 分页查询
     * @Description 分页查询银行信息
     * @Author ABu
     * @DateTime 2019/6/21 18:25
     */
    @RequestMapping("/list")
    public ModelAndView getPageList(Model model, TaskInfo param, @PageableDefault(value = 15, sort = {"id"}, direction = Sort.Direction.DESC) Pageable page) {
        ModelAndView mv = new ModelAndView(path + "task_list");
        Page<TaskInfo> pList = taskInfoService.getPageList(param, page);
        model.addAttribute("list", pList);
        model.addAttribute("p", param);
        return mv;
    }

    @GetMapping (value = "/log")
    public ModelAndView getRegularInfo() {
        ModelAndView view = new ModelAndView(path + "logging.html");
        return view;
    }

    /**
     * 新增规则页面
     *
     * @return
     */
    @GetMapping("/add")
    public ModelAndView addUserPage(Model model) {
        List<Regular> rList = regularService.findAll();
        model.addAttribute("regularInfoList", rList);
        return new ModelAndView(path + "task_add");
    }

    /**
     * 保存规则
     *
     * @return
     */
    @PostMapping("/save")
    @ResponseBody
    public Result save(TaskInfo r) {
        return taskInfoService.save(r);
    }

    /**
     * 新增规则页面
     *
     * @return
     */
    @GetMapping("/view")
    public ModelAndView viewTaskPage(Model model, Long id) {
        ModelAndView mv = new ModelAndView(path + "task_view");
        TaskInfo task = taskInfoService.findById(id);
        model.addAttribute("p", task);
        model.addAttribute("regulars",task.getRegulars());
        return mv;
    }

    /**
     * 新增规则页面
     *
     * @return
     */
    @GetMapping("/edit")
    public ModelAndView editTaskPage(Model model, Long id) {
        ModelAndView mv = new ModelAndView(path + "task_edit");
        TaskInfo task = taskInfoService.findById(id);
        List<Regular> regulars = regularService.findAll();
        model.addAttribute("p", task);
        model.addAttribute("rList",task.getRegulars().stream().map(regular -> regular.getId()).collect(Collectors.toList()));
        model.addAttribute("regulars",regulars);
        return mv;
    }

    /**
     * 运行任务
     *
     * @return
     */
    @PostMapping("/run")
    @ResponseBody
    public Result run(@RequestParam Long id) {
        return taskInfoService.run(id);
    }

    /**
     * 运行任务
     *
     * @return
     */
    @RequestMapping(value = "/export", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void export(HttpServletResponse response,Long id) throws UnsupportedEncodingException {
        TaskInfo task = taskInfoService.findById(id);
        // 设置响应的内容类型
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        // 设置文件头
        response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode("result.txt", "UTF-8"));
        try (OutputStream os =response.getOutputStream()){
            byte[] bytes = task.getResult().getBytes("UTF-8");
            os.write(bytes);
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新任务
     *
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public Result update(TaskInfo taskInfo) {

        return taskInfoService.update(taskInfo);
    }



}
