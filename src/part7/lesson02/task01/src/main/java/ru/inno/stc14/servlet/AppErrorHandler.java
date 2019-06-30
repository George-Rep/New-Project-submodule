package ru.inno.stc14.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processError(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processError(request, response);
    }

    /**
     * формируется описание ошибки и соответствующая страница ответа /error
     *
     */
    private void processError(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Throwable throwable = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String servletName = (String) request.getAttribute(RequestDispatcher.ERROR_SERVLET_NAME);
        ResourceBundle resource= (ResourceBundle) getServletContext().getAttribute("Properties");
        if (servletName == null) {
            servletName = resource.getString("AppErrorHandler.message.null");
        }
        String requestUri = (String) request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
        if (requestUri == null) {
            requestUri = resource.getString("AppErrorHandler.message.null");
        }

        // Set response content type
        response.setContentType(resource.getString("AppErrorHandler.contentType"));

        try(PrintWriter out = response.getWriter()) {
            out.write(resource.getString("AppErrorHandler.errorPage1"));
            if (statusCode != Integer.parseInt(resource.getString("AppErrorHandler.statusCode"))) {
                out.write(resource.getString("AppErrorHandler.errorPage2"));
                out.write(resource.getString("AppErrorHandler.errorPage3.1") + statusCode
                        + resource.getString("AppErrorHandler.errorPage3.2"));
                out.write(resource.getString("AppErrorHandler.errorPage4") + requestUri);
            } else {
                out.write(resource.getString("AppErrorHandler.errorPage5"));
                out.write(resource.getString("AppErrorHandler.errorPage6.1") + servletName
                        + resource.getString("AppErrorHandler.errorPage6.2"));
                out.write(resource.getString("AppErrorHandler.errorPage7.1") + throwable.getClass().getName()
                        + resource.getString("AppErrorHandler.errorPage7.2"));
                out.write(resource.getString("AppErrorHandler.errorPage8.1") + requestUri
                        + resource.getString("AppErrorHandler.errorPage8.2"));
                out.write(resource.getString("AppErrorHandler.errorPage9.1") + throwable.getMessage()
                        + resource.getString("AppErrorHandler.errorPage9.2"));
                out.write(resource.getString("AppErrorHandler.errorPage10"));
            }

            out.write(resource.getString("AppErrorHandler.errorPage11"));
            out.write(resource.getString("AppErrorHandler.errorPage12"));
            out.write(resource.getString("AppErrorHandler.errorPage13"));
        }
    }
}
