package com.qjp.log;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * 日志切面
 */
@Aspect
@Slf4j
@Component                    //注解开关                      匹配到“true”才开启           默认为true
@ConditionalOnProperty(name = {"log.aspect.enable"},havingValue = "true",matchIfMissing = true)
public class LohAspect {
    /**
     * 对Controller和Service设置切点
     */
    @Pointcut("execution(* com.qjp.*.controller.*Controller.*(..)) || execution(* com.qjp.*.service.*Service.*(..))")
    private void pointCut(){}

    /**
     * 环绕通知
     */
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        //拿到参数
        Object[] reqArgs = pjp.getArgs();
        String req = new Gson().toJson(reqArgs);
        //方法相关信息
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        //类名加方法名
        String methodName = methodSignature.getDeclaringTypeName()+"."+methodSignature.getName();
        log.info("{},req{}",methodName,req);

        Long startTime = System.currentTimeMillis();
        //执行方法                注：这里的异常一定不要catch 否则业务代码都异常都都被捕获了 将会无法发现错误
        Object responseObj = pjp.proceed();
        //出参
        String resp = new Gson().toJson(responseObj);
        Long endTime = System.currentTimeMillis();
        log.info("{},response:{},costTime:{}ms",methodName,resp,endTime-startTime);
        return responseObj;
    }

}
