package com.example.ordering.security;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthFilter extends GenericFilter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain fil)
            throws IOException, ServletException {
        String token = ((HttpServletRequest) req).getHeader("Authorization");


        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }


        if (token == null || !isValidToken(token)) {
            HttpServletResponse response = (HttpServletResponse) res;
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
            return;
        }


        fil.doFilter(req, res);



    }
}
