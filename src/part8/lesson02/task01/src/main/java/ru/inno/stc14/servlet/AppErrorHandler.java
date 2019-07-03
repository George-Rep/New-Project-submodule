package ru.inno.stc14.servlet;

import ru.inno.stc14.dao.jdbc.singleton.ResourceBundleInstance;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 *
 * обработчик ошибок
 *
 */
@WebServlet(
        name = "errorHandler",
        loadOnStartup = 1,
        urlPatterns = "/error"
)
public class AppErrorHandler extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processError(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processError(request, response);
    }

    /**
     * формируется описание ошибки и соответствующая страница ответа /error
     *
     */
    private void processError(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Throwable throwable = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String servletName = (String) request.getAttribute(RequestDispatcher.ERROR_SERVLET_NAME);
        ResourceBundle resource = ResourceBundleInstance.getInstance().getResource();
        if (servletName == null) {
            servletName = resource.getString("AppErrorHandler.message.null");
        }
        String requestUri = (String) request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
        if (requestUri == null) {
            requestUri = resource.getString("AppErrorHandler.message.null");
        }

        if (statusCode != 500) {
            request.setAttribute("statusCode", statusCode);
            request.setAttribute("requestUri", requestUri);
        } else {
            request.setAttribute("statusCode", statusCode);
            request.setAttribute("requestUri", requestUri);
            request.setAttribute("servletName", servletName);
            request.setAttribute("throwableName", throwable.getClass().getName());
            request.setAttribute("throwableMessage", throwable.getMessage());
        }
        request.getRequestDispatcher("/error.jsp")
                .forward(request, response);
    }
}
