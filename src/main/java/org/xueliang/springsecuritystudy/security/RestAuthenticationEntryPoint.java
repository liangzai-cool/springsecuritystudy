package org.xueliang.springsecuritystudy.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.xueliang.springsecuritystudy.DefaultException;
import org.xueliang.springsecuritystudy.model.JSONResponse;

/**
 * 这个入口点其实仅仅是被ExceptionTranslationFilter引用
 * 由此入口决定redirect、forward的操作
 * @author XueLiang
 * @date 2017年3月1日 上午10:20:39
 * @version 1.0
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger LOGGER = LogManager.getLogger(RestAuthenticationEntryPoint.class);
    
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        LOGGER.warn("Authentication Failed: " + authException.getMessage());
        JSONResponse jsonResponse = new JSONResponse();
        jsonResponse.addError(DefaultException.Error.invalid_parameter.name(), authException.getMessage());
        PrintWriter printWriter = response.getWriter();
        printWriter.write(jsonResponse.toString());
        printWriter.flush();
        printWriter.close();
    }
}
