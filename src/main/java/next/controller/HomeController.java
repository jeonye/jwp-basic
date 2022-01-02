package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.Controller;
import next.dao.QuestionDao;
import next.dao.UserDao;

public class HomeController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 사용자 정보 조회
        UserDao userDao = new UserDao();
        req.setAttribute("users", userDao.findAll());

        // 게시판 일람 조회
        QuestionDao questionDao = new QuestionDao();
        req.setAttribute("questions", questionDao.findAll());

        return "home.jsp";
    }
}
