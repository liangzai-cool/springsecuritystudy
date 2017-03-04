package org.xueliang.springsecuritystudy.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.xueliang.springsecuritystudy.DefaultException;
import org.xueliang.springsecuritystudy.model.JSONResponse;

public class RestAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private static final Logger LOGGER = LogManager.getLogger(RestAuthenticationFailureHandler.class);
    
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        LOGGER.info("auth failure!");
        JSONResponse jsonResponse = new JSONResponse();
        jsonResponse.addError(DefaultException.Error.invalid_parameter.name(), exception.getMessage());
        PrintWriter printWriter = response.getWriter();
        printWriter.write(jsonResponse.toString());
        printWriter.flush();
        printWriter.close();
    }
}
