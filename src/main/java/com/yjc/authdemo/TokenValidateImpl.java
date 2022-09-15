package com.yjc.authdemo;

import com.yjc.authdemo.po.MyException;
import com.yjc.authdemo.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
@Aspect
@Order(1)
public class TokenValidateImpl {
    @Autowired
    HttpServletRequest request;


    @Pointcut("@annotation(com.yjc.authdemo.TokenValidate)")
    public void pointMethod() {
    }

    @Around("pointMethod()")
    public Object aroundTokenValidate(ProceedingJoinPoint joinPoint) throws MyException {
        String name = joinPoint.getSignature().getName();
        log.info("目标方法:{}的token验证器", name);
        String token = request.getHeader("token");
        if (!StringUtils.isEmpty(token)) {
            log.info("获取到请求头信息：{}", token);
            JwtUtil.verify(token);
            try {
                Object proceed = joinPoint.proceed();
                log.info("方法执行完毕,返回值为：{}", proceed);
                return proceed;
            } catch (Throwable throwable) {
                log.info("方法执行出现异常:{}", throwable.getMessage());
                return null;
            }
        } else {
            return null;
        }
    }

}
