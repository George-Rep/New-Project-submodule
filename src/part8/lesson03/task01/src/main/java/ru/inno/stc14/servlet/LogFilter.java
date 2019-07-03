package ru.inno.stc14.servlet;

import ru.inno.stc14.dao.jdbc.singleton.Decorator.ResourceBundleInterface;
import ru.inno.stc14.dao.jdbc.singleton.ResourceBundleInstance;

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
    private ResourceBundleInterface resource = ResourceBundleInstance.getInstance().getResource();
private String logMessageOnDestroy=null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        logger.warning(resource.getResourceBundleString("LogFilter.logmessage.init"));
        logMessageOnDestroy=resource.getResourceBundleString("LogFilter.logmessage.close");
    }
    /**
     *
     * логирование веб запросов
     *
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String uri = resource.getResourceBundleString("LogFilter.requestedURI") + req.getRequestURI();
        logger.warning(uri);
        String method = resource.getResourceBundleString("LogFilter.requestedMethod") + req.getMethod();
        logger.warning(method);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy()
    {
        logger.warning(logMessageOnDestroy);
    }
}
