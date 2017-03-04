package org.xueliang.springsecuritystudy.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xueliang.springsecuritystudy.model.JSONResponse;

@RestController
@RequestMapping(value = "/api/auth/")
public class AuthApi extends BaseApi {
    
    @RequestMapping(value="csrf-token")
    public String getCsrfToken(HttpServletRequest request) {
        JSONResponse jsonResponse = new JSONResponse();
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        String token = csrfToken.getToken();
        jsonResponse.addMsg("csrfToken", token);
        return jsonResponse.toString();
    }
    
    @RequestMapping("whoami")
    public String whoami() {
        JSONResponse jsonResponse = new JSONResponse();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        jsonResponse.addMsg("username", username);
        return jsonResponse.toString();
    }
}
