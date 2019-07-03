package ru.inno.stc14.servlet;

import ru.inno.stc14.dao.jdbc.singleton.Decorator.ResourceBundleInterface;
import ru.inno.stc14.dao.jdbc.singleton.ResourceBundleInstance;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.stream.Stream;

/**
 * фильтр для аутентификации
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
    private String[] noAuthURLs = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ResourceBundleInterface resource = ResourceBundleInstance.getInstance().getResource();
        noAuthURLs = resource.getResourceBundleString("LoginFilter.noAuthURL").split(resource.getResourceBundleString("LoginFilter.delimiter"));
    }


    /**
     * фильтр для аутентификации
     * если страница не в списке исключений, и в http сеансе не установлен атрибут USER,
     * то forward на страницу логина.
     * При этом в login.jsp через атрибут request устанавливается поле для хранения
     * url, по которому надо будет вернуться.
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);

        if (needsAuthentication(request.getRequestURI()) && (session == null || session.getAttribute("USER") == null)) {
            req.setAttribute("LOGIN_REDIRECT", request.getRequestURI());
            req.getRequestDispatcher("/login").forward(request, response);
        } else {
            chain.doFilter(req, res);
        }
    }

    /**
     * проверяется, нужна ли аутентификация для этой страницы (url) (не нужна, если страница есть в списке)
     *
     * @param url url
     */
    private boolean needsAuthentication(String url) {
        for (String validUrl : noAuthURLs) {
            if (url.contains(validUrl)) {
                return false;
            }
        }
        return true;
    }
}