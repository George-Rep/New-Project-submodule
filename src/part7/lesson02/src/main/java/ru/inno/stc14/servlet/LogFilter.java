package ru.inno.stc14.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Logger;
/**
 *
 * логирование веб запросов.
 *
 */
@WebFilter(       filterName = "logFilter",
        urlPatterns = "/*")
public class LogFilter implements Filter {
    private Logger logger = Logger.getLogger(LogFilter.class.getName());
private String logMessageOnDestroy=null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ResourceBundle resource= (ResourceBundle) filterConfig.getServletContext().getAttribute("Properties");
        logger.warning(resource.getString("LogFilter.logmessage.init"));
        logMessageOnDestroy=resource.getString("LogFilter.logmessage.close");
    }
    /**
     *
     * логирование веб запросов
     *
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        ResourceBundle resource= (ResourceBundle) req.getServletContext().getAttribute("Properties");
        String uri = resource.getString("LogFilter.requestedURI") + req.getRequestURI();
        logger.warning(uri);
        String method = resource.getString("LogFilter.requestedMethod") + req.getMethod();
        logger.warning(method);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy()
    {
        logger.warning(logMessageOnDestroy);
    }
}
