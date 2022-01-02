package next.controller;

import core.mvc.Controller;
import next.dao.QuestionDao;
import next.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

public class UpdateQuestionController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        QuestionDao questionDao = new QuestionDao();
        Question question = new Question(Long.parseLong(req.getParameter("questionId")), req.getParameter("writer"),
                req.getParameter("title"), req.getParameter("contents"), LocalDateTime.now().toString(), 0);

        questionDao.update(question);
        return "redirect:/";
    }
}
