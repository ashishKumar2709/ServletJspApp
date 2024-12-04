package com.ak09.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import java.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class SessionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        System.out.println("SessionFilter triggered for: " + httpRequest.getRequestURI());

        // Set cache-control headers to prevent caching
        httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        httpResponse.setHeader("Pragma", "no-cache");
        httpResponse.setDateHeader("Expires", 0);

        HttpSession session = httpRequest.getSession(false);

        String requestURI = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();
        System.out.println(requestURI.equals(contextPath + "/"));
        System.out.println(requestURI.equals(contextPath + "/index.jsp"));
        // Paths allowed without session
        boolean isIndexPage = requestURI.equals(contextPath + "/") || requestURI.equals(contextPath + "/index.jsp");
		boolean isLoginServlet = requestURI.endsWith("/login");
//        boolean isStaticResource = requestURI.startsWith(contextPath + "/resources/"); // Adjust if static files have another path
        
        if (isIndexPage ||isLoginServlet ) {
            System.out.println("SessionFilter: Allowed without session (index page or static resources)");
            chain.doFilter(request, response);
            return;
        }
        System.out.println(session.getAttribute("email"));
        // Check session for logged-in user
        if (session == null || session.getAttribute("email") == null) {
            System.out.println("SessionFilter: Redirecting to index.jsp");
            httpResponse.sendRedirect(contextPath + "/index.jsp");
        } else {
            System.out.println("SessionFilter: User authenticated, proceeding");
            chain.doFilter(request, response);
        }
    }
}