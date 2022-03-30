package com.lybank.config;

import com.lybank.config.interceptor.*;
import com.lybank.config.security.SecurityProvider;
import com.lybank.config.validate.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.lang.reflect.Method;
import java.util.List;

/**
 * spring安全入口初始化类
 */
@EnableWebSecurity
@ComponentScan("com.lybank.security")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
    @Autowired
    private SpringDataUserDetailsService springDataUserDetailsService;
    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private MyAuthenticationLogoutHandler myAuthenticationLogoutHandler;
    @Autowired
    private SecurityProvider securityProvider;
    @Autowired
    private ValidateCodeFilter validateCodeFilter;
    @Autowired
    private MyPermissionEvaluator myPermissionEvaluator;
    @Autowired
    private IPInterceptorConfig ipInterceptorConfig;

    /*
     * @SuppressWarnings("deprecation")
     *
     * @Bean public UserDetailsService userDetailsService() throws Exception {
     * InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
     * manager.createUser(User.withDefaultPasswordEncoder().username("user").
     * password("password").roles("USER").build()); return manager;
     *
     * User.UserBuilder builder = User.withDefaultPasswordEncoder(); UserDetails
     * user = builder.username("user").password("password").roles("USER").build();
     * UserDetails admin =
     * builder.username("admin").password("password").roles("USER",
     * "ADMIN").build(); return new InMemoryUserDetailsManager(user, admin); }
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoauthenticationProvider());
        auth.authenticationProvider(securityProvider);
    }

    /**
     * 页面资源
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 在UsernamePasswordAuthenticationFilter 过滤器前 加一个过滤器 来搞验证码
        http
                .authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/lib/**").permitAll()
                .antMatchers("/static/**").permitAll()
                .antMatchers("/googlev/**").permitAll()
//                .antMatchers("/Pay/**").permitAll()
                // .antMatchers("/welcome.html").permitAll()
                .antMatchers("/loginPage").permitAll().antMatchers("/defaultKaptcha").permitAll().anyRequest()
                .authenticated().and()
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/login")
                // ..loginProcessingUrl("/userLogin")
                .successForwardUrl("/index").permitAll().successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler).and()
                .logout()
                // .logoutUrl("/loginOut")
                // .logoutSuccessUrl("/index")
                .logoutSuccessHandler(myAuthenticationLogoutHandler)
                .invalidateHttpSession(true)

                // .addLogoutHandler(logoutHandler)
                // .deleteCookies(cookieNamesToClear)
                .permitAll()
                .and().rememberMe().tokenValiditySeconds(3600)//1209600
                .and().csrf().disable().headers().frameOptions().sameOrigin();
        // session管理
        // session失效后跳转
        http.sessionManagement().invalidSessionUrl("/loginPage?invalid");
        // 只允许一个用户登录,如果同一个账户两次登录,那么第一个账户将被踢下线,跳转到登录页面
        http.sessionManagement().maximumSessions(1).expiredUrl("/loginPage");
    }

    @Bean
    public DaoAuthenticationProvider daoauthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(springDataUserDetailsService);
        authenticationProvider.setPasswordEncoder(encoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public MethodSecurityExpressionHandler expressionHandler() {
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(myPermissionEvaluator);
        return expressionHandler;
    }


    @Bean(name = "keyGenerator")
    public KeyGenerator customKeyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object o, Method method, Object... objects) {
                StringBuilder sb = new StringBuilder();
                sb.append(o.getClass().getName());
                sb.append(":");
                sb.append(method.getName());
                sb.append(":");
                for (Object obj : objects) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {

    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {

    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {

    }

    @Override
    public void addFormatters(FormatterRegistry registry) {

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(ipInterceptorConfig);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/*").addResourceLocations("classpath:/static/");
//        registry.addResourceHandler("/lib/*").addResourceLocations("classpath:/lib/");
//        registry.addResourceHandler("/templates/*").addResourceLocations("classpath:/templates/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {

    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {

    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {

    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {

    }

    @Override
    public Validator getValidator() {
        return null;
    }

    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        return null;
    }
    /*
     * @Override public void addViewControllers(ViewControllerRegistry registry) {
     * registry.addViewController("/loginP").setViewName("login");
     * registry.setOrder(Ordered.HIGHEST_PRECEDENCE); }
     */

}
