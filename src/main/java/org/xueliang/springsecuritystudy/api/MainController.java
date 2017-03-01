package org.xueliang.springsecuritystudy.api;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xueliang.springsecuritystudy.model.JSONResponse;

@RestController
@RequestMapping(value = "/api/auth/")
public class MainController {

    @RequestMapping(value="admin")
    public String visitAdmin() {
        JSONResponse jsonResponse = new JSONResponse();
        JSONObject json = new JSONObject();
        json.put("username", "admin");
        json.put("nickname", "liang");
        jsonResponse.addMsg("user", json);
        return jsonResponse.toString();
    }
    
    @RequestMapping(value="csrf-token")
    public String getCsrfToken(HttpServletRequest request) {
        JSONResponse jsonResponse = new JSONResponse();
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        String token = csrfToken.getToken();
        jsonResponse.addMsg("csrfToken", token);
        return jsonResponse.toString();
    }
}
