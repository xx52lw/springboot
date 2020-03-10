package com.dock.lw.common;

import com.dock.lw.code.model.User;
import com.dock.lw.common.enums.SystemContextType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Slf4j
public abstract class BaseController {

    @Autowired
    private HttpServletRequest request;

    /**
     * 获取当前用户信息
     * @return
     */
    public User getCurrentUser() {
        return SystemContext.getCurrentValue(SystemContextType.CURRENT_ORG.CURRENT_USER, User.class);
    }

    /**
     * 获取当前组织ID
     * @return
     */
    public Long getCurrentOrgId() {
        String orgId = SystemContext.getCurrentValue(SystemContextType.CURRENT_ORG, String.class);
        if(StringUtils.isBlank(orgId)) {
            return null;
        }
        return Long.parseLong(orgId);
    }

    /**
     * 文件下载
     *
     * @param file
     * @param fileName
     * @param response
     * @throws IOException
     */
    public void download(File file, String fileName, HttpServletResponse response) throws IOException {
        OutputStream os = null;
        InputStream is = null;
        try {
            if (file == null) {
                log.warn("文件不存在");
                return;
            }
            User user = getCurrentUser();
            log.info((user != null ? "userId = " + user.getId() + " " : StringUtils.EMPTY) + "文件下载 file = " + file.getAbsoluteFile());

            String userAgent = request.getHeader("User-Agent").toLowerCase();
            // Java下载文件时IE浏览器出现文件名乱码
            // 针对IE或者以IE为内核的浏览器：
            if (userAgent.contains("msie") || userAgent.contains("trident") ) {
                fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
            } else {
                // 非IE浏览器的处理：
                fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
            }
            response.reset();
            response.setContentType("application/octet-stream");
            os = response.getOutputStream();
            response.addHeader("Content-Disposition",
                    "attachment;filename=" + fileName);
            is = new FileInputStream(file);
            byte[] data = new byte[1024];
            int len = 0;
            while ((len = is.read(data)) > 0) {
                os.write(data, 0, len);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.close();
            }
        }
    }

}
