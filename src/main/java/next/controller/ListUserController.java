package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.exception.DataAccessException;
import core.mvc.Controller;
import next.dao.UserDao;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListUserController implements Controller {

    private static final Logger log = LoggerFactory.getLogger(ListUserController.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (!UserSessionUtils.isLogined(req.getSession())) {
            return "redirect:/users/loginForm";
        }

        UserDao userDao = new UserDao();
        List<User> userList = new ArrayList<User>();

        try {
            userList = userDao.findAll();
        } catch (DataAccessException e) {
            log.error(e.getMessage());
        }

        req.setAttribute("users", userList);
        return "/user/list.jsp";
    }
}
