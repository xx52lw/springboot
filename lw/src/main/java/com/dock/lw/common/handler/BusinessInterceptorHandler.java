package com.dock.lw.common.handler;


import com.alibaba.fastjson.JSON;
import com.dock.lw.code.model.User;
import com.dock.lw.common.*;
import com.dock.lw.common.enums.SystemContextType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@Service
public class BusinessInterceptorHandler {


    @Autowired
    protected CacheService cacheService;

    public boolean process(HttpServletRequest req, HttpServletResponse resp, String token) throws IOException {
        String reqURI = req.getRequestURI();


//        String orgId = null;
//        if(reqURI.contains(Constant.PLATFORM.APP)) {
//            orgId = req.getHeader(Constant.REQUEST_ORGID);
//            if (StringUtils.isBlank(orgId)) {
//                orgId = req.getParameter(Constant.REQUEST_ORGID);
//            }
//            log.info("reqURI = " + reqURI + ", orgId = " + orgId);
//        }

        User user = (User) cacheService.get(Constant.Cache.TOKEN + Constant.COLON + token);

        if(StringUtils.isBlank(token)) {
            log.warn("用户未登录或token过期 token = " + token);
            printResult(resp, JSON.toJSONString(Result.fail(ResultCode.USER_NOT_LOGIN)));
            return false;
        }
        user.setToken(token);
        SystemContext.setCurrentValue(SystemContextType.CURRENT_USER, User.class, user);

        return true;
    }

    private void printResult(HttpServletResponse resp, String result) throws IOException {
        resp.setContentType("application/json; charset=UTF-8");
        resp.setHeader("Cache-Control", "no-cache"); // 取消浏览器缓存
        PrintWriter out = resp.getWriter();
        out.print(result);
        out.flush();
        out.close();
    }

    public void clean() {
        SystemContext.clearCurrentValue(SystemContextType.CURRENT_USER);
        SystemContext.clearCurrentValue(SystemContextType.CURRENT_ORG);
    }

}
