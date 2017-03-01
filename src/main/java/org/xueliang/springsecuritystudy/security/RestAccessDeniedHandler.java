package org.xueliang.springsecuritystudy.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.xueliang.springsecuritystudy.DefaultException;
import org.xueliang.springsecuritystudy.model.JSONResponse;

/**
 * 拒绝访问
 * @author XueLiang
 * @date 2017年3月1日 上午11:07:10
 * @version 1.0
 */
public class RestAccessDeniedHandler implements AccessDeniedHandler {
    
    private static final Logger LOGGER = LogManager.getLogger(RestAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
        LOGGER.warn("Authentication Failed: " + accessDeniedException.getMessage());
        JSONResponse jsonResponse = new JSONResponse();
        jsonResponse.addError(DefaultException.Error.invalid_parameter.name(), accessDeniedException.getMessage());
        PrintWriter printWriter = response.getWriter();
        printWriter.write(jsonResponse.toString());
        printWriter.flush();
        printWriter.close();
    }
}
