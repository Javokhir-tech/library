package com.java.library.configuration;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author abdullaevdjavokhir@gmail.com
 * @created 15/01/2022 - 1:38 PM
 */
public class AuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setHeader("message", "Thuck you, witch!");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
