package com.dock.lw.common.handler;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 访问日志
 */
@Component
@Aspect
public class AccessLog {

    private static final Logger LOG = LogManager.getLogger(AccessLog.class);

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.PostMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.GetMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.PutMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    private void pointcut() {}

    @Around("pointcut()")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch watch = new StopWatch(joinPoint.getTarget().getClass().getName());
        try {
            watch.start(joinPoint.getSignature().getName());
            List<Object> arguments  = new ArrayList();
            Object[] args = joinPoint.getArgs();
            if(ArrayUtils.isNotEmpty(args)) {
                for(Object arg : args) {
                    if (arg instanceof ServletRequest || arg instanceof ServletResponse || arg instanceof MultipartFile) {
                        //ServletRequest不能序列化，从入参里排除，否则报异常：java.lang.IllegalStateException: It is illegal to call this method if the current request is not in asynchronous mode (i.e. isAsyncStarted() returns false)
                        //ServletResponse不能序列化 从入参里排除，否则报异常：java.lang.IllegalStateException: getOutputStream() has already been called for this response
                        continue;
                    }
                    arguments.add(arg);
                }
            }
            LOG.info("request = {}", JSON.toJSONString(arguments));
            Object result = joinPoint.proceed();
            LOG.info("response = {}", JSON.toJSONString(result));
            return result;
        }finally {
            watch.stop();
            LOG.info(watch.prettyPrint());
        }
    }

}
