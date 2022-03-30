package com.lybank.config.validate;

import com.google.code.kaptcha.Constants;
import com.lybank.entity.system.Dict;
import com.lybank.entity.system.GoogleV;
import com.lybank.exception.GoogleCodeException;
import com.lybank.exception.ValidateCodeException;
import com.lybank.service.system.DictService;
import com.lybank.service.system.GoogleVService;
import com.lybank.util.Constant;
import com.lybank.util.code.DictCode;
import com.lybank.util.code.ImageCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;
import org.thymeleaf.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
@Slf4j
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    /**
     * 登录失败处理器
     */
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private GoogleVService googleVService;
    @Autowired
    private DictService dicService;


    /**
     * 创建一个Set 集合 存放 需要验证码的 urls
     */
    private Set<String> urls = new HashSet<>();

    /**
     * security applicaiton 配置属性
     */
    @Autowired
    private SecurityProperties securityProperties;
    /**
     * spring的一个工具类：用来判断 两字符串 是否匹配
     */
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    /**
     * 这个方法是 InitializingBean 接口下的一个方法， 在初始化配置完成后 运行此方法
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        // 将 application 配置中的 url 属性进行 切割
        String[] configUrls = StringUtils.split(Constant.VERIFY_URL, ",");
        // 添加到 Set 集合里
        urls.addAll(Arrays.asList(configUrls));
        // 因为登录请求一定要有验证码 ，所以直接 add 到set 集合中
        urls.add("/login");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        boolean action = false;
        String requestUri = request.getRequestURI();
        for (String url : urls) {
            // 如果请求的url 和 配置中的url 相匹配
            if (pathMatcher.match(url, requestUri) && request.getMethod().equalsIgnoreCase(HttpMethod.POST.name())) {
                action = true;
            }
        }

        // 拦截请求
        if (action) {
            logger.info("拦截成功" + request.getRequestURI());
            // 如果是登录请求
            try {
                HttpSession session = request.getSession();
                // 基本校验
                validate(new ServletWebRequest(request));
                // 谷歌验证码
                googleValidate(new ServletWebRequest(request));
                // 把对应 的 session信息 删掉
                session.removeAttribute(Constants.KAPTCHA_SESSION_KEY);//验证码
            } catch (ValidateCodeException exception) {
                // 返回错误信息给 失败处理器
                authenticationFailureHandler.onAuthenticationFailure(request, response, exception);
                return;
            } catch (GoogleCodeException gve) {
                // 返回错误信息给 失败处理器
                authenticationFailureHandler.onAuthenticationFailure(request, response, gve);
                return;

            }
        }

        //判断用户是否登录。
        HttpSession session = request.getSession();
        boolean is = (session.getAttribute("SPRING_SECURITY_CONTEXT") == null && !"/login".equals(requestUri)
                && !"/defaultKaptcha".equals(requestUri) && !requestUri.contains("lib")
                && !requestUri.contains("temp") && !requestUri.contains("static")) && !"/".equals(requestUri);
        if (is) {
            //没登录或登录失效跳转登录页面
//            String requestURL = request.getRequestURL().toString();
//            String url = requestURL.substring(0, requestURL.lastIndexOf(":8088") + 6) + "login";
//            String url = requestURL.substring(0, requestURL.lastIndexOf(".com")+5) + "login";
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print("<script type=\"text/javascript\">");
            response.getWriter().print("window.top.location=\"/\"");
            response.getWriter().print("</script>");
        } else {
            //用户存在进行下一步
            filterChain.doFilter(request, response);
        }

    }

    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        // 从session中取出 验证码

        HttpSession session = request.getRequest().getSession();
        ImageCode codeInSession = (ImageCode) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        // 从request 请求中 取出 验证码
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), Constant.VERIFY_CODE);
        if (StringUtils.isEmpty(codeInRequest)) {
            String t = "验证码不能为空";
            logger.info(t);
            throw new ValidateCodeException(t);

        }
        if (codeInSession == null) {
            String t = "验证码不存在";
            logger.info(t);
            throw new ValidateCodeException(t);
        }
        if (codeInSession.isExpried()) {
            String t = "验证码已过期";
            logger.info(t);
            session.removeAttribute(Constants.KAPTCHA_SESSION_KEY);
            throw new ValidateCodeException(t);
        }
        if (!StringUtils.equalsIgnoreCase(codeInSession.getCode(), codeInRequest)) {
            String t = "验证码不匹配";

            logger.info(t + "codeInSession:" + codeInSession.getCode() + ", codeInRequest:" + codeInRequest);
            throw new ValidateCodeException(t);
        }

    }

    public void googleValidate(ServletWebRequest request) throws ServletRequestBindingException {
        String googleCode = ServletRequestUtils.getStringParameter(request.getRequest(), Constant.GOOGLE_CODE);
        String userName = ServletRequestUtils.getStringParameter(request.getRequest(), Constant.USER_NAME);
        Dict dict = dicService.getLoginGoogleV();//开关设定
        if (dict != null && dict.getValue().equalsIgnoreCase(DictCode.ENABLE)) {// 开启谷歌验证
            GoogleV gv = googleVService.getGoogleVByUserName(userName);// get secret
            if (gv == null || StringUtils.isEmpty(gv.getSecret())) {
                String t = "请联系系统管理员绑定验证码!";
                logger.info(t);
                throw new GoogleCodeException(t);
            }
            if (gv != null && DictCode.ENABLE.equalsIgnoreCase(gv.getStatus())) {
                // 谷歌验证码校验
                if (StringUtils.isEmpty(googleCode)) {
                    logger.info("谷歌验证码不能为空，请输入google验证码");
                    throw new GoogleCodeException("谷歌验证码不能为空!");
                }
                // 校验
                String secret = gv.getSecret();
                Long gcode = Long.parseLong(googleCode);
                long t = System.currentTimeMillis();
                GoogleAuthenticator ga = new GoogleAuthenticator();
                ga.setWindowSize(5);
                boolean r = ga.check_code(secret, gcode, t);
                if (!r) {
                    String tt = "谷歌验证码不正确！";
                    logger.info(tt);

                    throw new GoogleCodeException(tt);
                }
            }
        }
    }

    /**
     * 失败 过滤器 getter and setter 方法
     */
    public AuthenticationFailureHandler getFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setFailureHandler(AuthenticationFailureHandler failureHandler) {
        this.authenticationFailureHandler = failureHandler;
    }

    /**
     * SecurityProperties 属性类 getter and setter 方法
     */
    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

}
