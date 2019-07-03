package ru.inno.stc14.dao.jdbc.command;

import javax.servlet.ServletException;
import java.io.IOException;
/**
 * Command
 */
public interface Command {
    /**
     * метод Command
     * @throws IOException
     * @throws ServletException
     */
     void loginAction() throws IOException, ServletException;
}
