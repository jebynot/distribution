package com.vsc.web.filters;

import com.vsc.context.Context;
import com.vsc.context.ContextPlaceHolder;
import org.apache.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

@WebFilter(servletNames =  {"spring-mvc-dispatcher", "CXFServlet"})
public class ContextFilter implements Filter {
    private static final Logger logger = Logger.getLogger(ContextFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


        Context context = new Context(((HttpServletRequest)request).getSession().getId(), "ICE");
        ContextPlaceHolder.setContext(context);

        try {
            chain.doFilter(request, response);
        } finally {
            ContextPlaceHolder.removeContext();
        }

    }

    @Override
    public void destroy() {

    }
}
