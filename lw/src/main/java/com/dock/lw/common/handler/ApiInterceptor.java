package com.dock.lw.common.handler;

import com.dock.lw.common.Constant;
import com.dock.lw.config.FilterUrlConf;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <功能简述>
 *
 * @author cris
 */

@Slf4j
@Component
public class ApiInterceptor implements HandlerInterceptor {


//    拦截器执行顺序： preHandle -> controller -> postHandle -> afterCompletion，同时需要注意的是，只有 preHandle 方法返回 true，后面的方法才会继续执行。

    @Autowired
    private FilterUrlConf filterUrlConf;

    @Autowired
    private BusinessInterceptorHandler businessInterceptorHandler;

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        String token = req.getHeader(Constant.REQUEST_TOKEN);
        if (StringUtils.isBlank(token)) {
            token = req.getParameter(Constant.REQUEST_TOKEN);
        }
        String reqURI = req.getRequestURI();
        log.info("reqURI = " + reqURI + ", reqMethod = " + req.getMethod() + ", token = " + token);

        /**
         * https://my.oschina.net/u/257088/blog/1808714/
         * 跨域的第一次OPTIONS请求
         */
        if(req.getMethod().equals(HttpMethod.OPTIONS.name())) {
            return false;
        }
        if(isWhiteReq(reqURI)) {
            return true;
        }

        return businessInterceptorHandler.process(req, resp, token);
    }

    /*
     * 判断是否是白名单
     */
    private boolean isWhiteReq(String requestUrl) {
        List<String> urls = filterUrlConf.getUrls();
        if (CollectionUtils.isEmpty(urls)) {
            return true;
        } else {
            for (String urlTemp : urls) {
                if (requestUrl.indexOf(urlTemp) != -1 ||
                        requestUrl.indexOf(urlTemp.toLowerCase()) != -1) {
                    return true;
                }
            }
        }

        return false;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        businessInterceptorHandler.clean();
    }


}
