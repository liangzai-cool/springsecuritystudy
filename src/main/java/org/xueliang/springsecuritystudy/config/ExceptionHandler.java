package org.xueliang.springsecuritystudy.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;
import org.xueliang.springsecuritystudy.DefaultException;
import org.xueliang.springsecuritystudy.model.JSONResponse;

@Component("exceptionHandler")
public class ExceptionHandler extends DefaultHandlerExceptionResolver {

    private static final Logger LOGGER = LogManager.getLogger(ExceptionHandler.class);
    
    @Override
    protected ModelAndView doResolveException(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex) {
        LOGGER.error("error", ex);
        JSONResponse jsonResponse = new JSONResponse(1);
        jsonResponse.addError(DefaultException.Error.server_internal_error);
        try {
            response.setCharacterEncoding("UTF-8");
            response.addHeader("Content-Type", "application/json;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.print(jsonResponse);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            LOGGER.error("write response error", e);
        }
        return null;
    }

}
