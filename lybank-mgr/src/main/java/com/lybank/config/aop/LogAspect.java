package com.lybank.config.aop;

import com.lybank.service.system.SystemLogService;
import com.lybank.service.system.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    @Autowired
    private SystemLogService systemLogService;
    @Autowired
    private UserService userService;

    @Pointcut("execution(* com.lybank.controller..*.*(..))")
    public void executeService() {

    }

    /**
     * 后置返回通知 这里需要注意的是: 如果参数中的第一个参数为JoinPoint，则第二个参数为返回值的信息
     * 如果参数中的第一个参数不为JoinPoint，则第一个参数为returning中对应的参数 returning
     * 限定了只有目标方法返回值与通知方法相应参数类型时才能执行后置返回通知，否则不执行，对于returning对应的通知方法参数为Object类型将匹配任何目标返回值
     *
     * @param joinPoint
     * @param keys
     */
    @AfterReturning(value = "execution(* com.lybank.controller..*.*(..))", returning = "keys")
    public void doAfterReturningAdvice1(JoinPoint joinPoint, Object keys) {

        System.out.println("第一个后置返回通知的返回值：" + keys);
    }

    @AfterReturning(value = "execution(* com.lybank.controller..*.*(..))", returning = "keys", argNames = "keys")
    public void doAfterReturningAdvice2(String keys) {

        System.out.println("第二个后置返回通知的返回值：" + keys);
    }

    /**
     * 后置异常通知 定义一个名字，该名字用于匹配通知实现方法的一个参数名，当目标方法抛出异常返回后，将把目标方法抛出的异常传给通知方法； throwing
     * 限定了只有目标方法抛出的异常与通知方法相应参数异常类型时才能执行后置异常通知，否则不执行，
     * 对于throwing对应的通知方法参数为Throwable类型将匹配任何异常。
     *
     * @param joinPoint
     * @param exception
     */
    @AfterThrowing(value = "executeService()", throwing = "exception")
    public void doAfterThrowingAdvice(JoinPoint joinPoint, Throwable exception) {
        // 目标方法名：
        System.out.println(joinPoint.getSignature().getName());
        if (exception instanceof NullPointerException) {
            System.out.println("发生了空指针异常!!!!!");
        }
    }

}
