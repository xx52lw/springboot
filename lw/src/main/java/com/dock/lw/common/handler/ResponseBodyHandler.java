package com.dock.lw.common.handler;

import com.dock.lw.common.Result;
import com.dock.lw.common.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice(basePackages = "com.dock.lw.code.web")
public class ResponseBodyHandler implements ResponseBodyAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ResponseBodyHandler.class);

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        String methodName = returnType.getMethod().getName();
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if(body instanceof Result) {
            return body;
        }
        return new Result(ResultCode.SUCCESS, body);
    }

}
