package org.xueliang.springsecuritystudy.security;

import java.util.Arrays;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.RequestMatcher;

public class CsrfSecurityRequestMatcher implements RequestMatcher {
    
    private final HashSet<String> allowedMethods = new HashSet<String>(
            Arrays.asList("OPTIONS", "GET", "HEAD", "POST", "PUT", "DELETE", "TRACE", "CONNECT"));
    
    @Override
    public boolean matches(HttpServletRequest request) {
        return this.allowedMethods.contains(request.getMethod());
    }

}
