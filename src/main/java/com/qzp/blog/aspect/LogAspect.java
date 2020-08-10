package com.qzp.blog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author quezhipeng
 * @date 2020/7/3 10:13
 * @Desc aop
 */
@Aspect
//@Aspect注解是一个开启Aop
@Component
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.qzp.blog.controller.*.*(..))")
    public void log(){}

    @Before(value = "log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        System.out.println("request-->"+request);
        String url = request.getRequestURL().toString();
        System.out.println("url-->"+url);
        String ip = request.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        System.out.println("classMethod-->"+classMethod);
        Object[] args =joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url, ip, classMethod,args);
        logger.info("Request : {}",requestLog);
    }

    @After(value = "log()")
    public void doAfter(){
        logger.info("-----------doAfter---------------");
    }

    @AfterReturning(value = "log()",returning = "result")
    public void doAfterReturn(Object result){
        logger.info("Result : {} "+result);
    }
    // 内部类
    private class RequestLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
