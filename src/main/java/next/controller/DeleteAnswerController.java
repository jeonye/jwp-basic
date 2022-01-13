package next.controller;

import core.mvc.Controller;
import next.dao.AnswerDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteAnswerController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        long answerId = Long.parseLong(req.getParameter("answerId"));
        long questionId = Long.parseLong(req.getParameter("questionId"));
        AnswerDao answerDao = new AnswerDao();
        answerDao.delete(answerId);
        answerDao.updateForAnswerCount(questionId, 1);
        return "redirect:/";
    }
}
