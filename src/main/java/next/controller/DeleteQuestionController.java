package next.controller;

import core.mvc.Controller;
import next.dao.QuestionDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteQuestionController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        long questionId = Long.parseLong(req.getParameter("questionId"));
        QuestionDao questionDao = new QuestionDao();
        questionDao.delete(questionId);
        return "redirect:/";
    }
}
