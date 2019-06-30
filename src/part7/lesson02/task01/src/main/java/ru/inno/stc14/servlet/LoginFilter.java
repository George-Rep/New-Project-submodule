package ru.inno.stc14.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 *
 * фильтр для аутентификации
 *
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
    private String noAuthURL = null;
    /**
     *
     * фильтр для аутентификации
     * если страница не в списке исключений, и в http сеансе не установлен атрибут USER,
     * то forward на страницу логина.
     * При этом в login.jsp через атрибут request устанавливается поле для хранения
     * url, по которому надо будет вернуться.
     *
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        ResourceBundle resource = (ResourceBundle) req.getServletContext().getAttribute("Properties");
        noAuthURL = resource.getString("LoginFilter.noAuthURL");

        if (needsAuthentication(request.getRequestURI()) && (session == null || session.getAttribute("USER") == null)) {
            req.setAttribute("LOGIN_REDIRECT", request.getRequestURI());
            req.getRequestDispatcher("/login").forward(request, response);
        } else {
            chain.doFilter(req, res);
        }
    }
    /**
     *
     * проверяется, нужна ли аутентификация для этой страницы (url) (не нужна, если страница есть в списке)
     * @param url url
     */
    private boolean needsAuthentication(String url) {
        String[] validNonAuthenticationUrls =
                {noAuthURL};
        for (String validUrl : validNonAuthenticationUrls) {
            if (url.endsWith(validUrl)) {
                return false;
            }
        }
        return true;
    }
}