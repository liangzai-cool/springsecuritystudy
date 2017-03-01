package org.xueliang.springsecuritystudy.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.xueliang.springsecuritystudy.model.JSONResponse;

public class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    
    private static final Logger LOGGER = LogManager.getLogger(RestAuthenticationSuccessHandler.class);
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        LOGGER.info("auth success!");
        JSONResponse jsonResponse = new JSONResponse();
        jsonResponse.addMsg("result", "SUCCESS");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(jsonResponse.toString());
        printWriter.flush();
        printWriter.close();
        clearAuthenticationAttributes(request);
    }
}
