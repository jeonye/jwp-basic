package next.controller;

import core.mvc.Controller;
import next.dao.QuestionDao;
import next.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateQuestionController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        QuestionDao questionDao = new QuestionDao();
        long questionId = questionDao.findMaxQuestionId() + 1;

        Question question = new Question(questionId, req.getParameter("writer"),
                req.getParameter("title"), req.getParameter("contents"), null, 0);

        questionDao.insert(question);
        return "redirect:/";
    }
}
