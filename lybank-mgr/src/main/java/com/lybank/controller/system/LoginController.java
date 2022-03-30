package com.lybank.controller.system;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.lybank.service.system.CacheService;
import com.lybank.util.Constant;
import com.lybank.util.Result;
import com.lybank.util.code.ImageCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

/**
 * 用户登录
 *
 * @author xiaoqi 2018年10月3日
 */
@Controller
@RequestMapping("/")
public class LoginController {
    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @Autowired
    private CacheService cacheService;

    @GetMapping(value = {"/loginPage", "/login"})
    public String login(HttpServletRequest httpServlet, Model model,
                        @RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "msg", required = false) String msg) {
        if (error != null) {
            model.addAttribute("error", "用户名或密码错误");
        }
        return "login";
    }

    @ResponseBody
	@RequestMapping(value="clearCache")
	public com.lybank.util.Result clearCache() {
    	cacheService.clearAllCache();
		return new Result().ResultSuccess().setMsg("清理缓存成功");
	}
	

    @GetMapping(value = "/logOut")
    public String loginOut() {

        return "loginOut";
    }

    @RequestMapping(value = "/index")
    public ModelAndView index() {

        return new ModelAndView("index");
    }

    @RequestMapping(value = "/{page}.html")
    public String page(@PathVariable(value = "page") String page, HttpServletRequest httpServlet, HttpServletResponse httpServletResponse) {
        //httpServletResponse.setHeader("X-Frame-Options", "ALLOW-FROM");
        return page;
    }

    /**
     * 获得验证码图片
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws Exception
     */
    @GetMapping("/defaultKaptcha")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws Exception {
        HttpSession session = httpServletRequest.getSession();
	/*	ImageCode code = (ImageCode) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);  
        System.out.println("******************验证码是: " + code.getCode() + "******************");  */
        httpServletResponse.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.  
        httpServletResponse.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).  
        httpServletResponse.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.  
        httpServletResponse.setHeader("Pragma", "no-cache");
        // return a jpeg  
        httpServletResponse.setContentType("image/jpeg");
        // create the text for the image  
        String capText = defaultKaptcha.createText();
        // store the text in the session  
        // create the image with the text  
        BufferedImage bi = defaultKaptcha.createImage(capText);
        ImageCode ic = new ImageCode(capText, Constant.EXPIRED_TIME);
        session.setAttribute(Constant.KAPTCHA_SESSION_KEY, ic);
        ServletOutputStream out = httpServletResponse.getOutputStream();
        // write the data out  
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }

    }

}
