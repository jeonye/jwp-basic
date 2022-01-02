package next.controller;

import core.mvc.Controller;
import next.dao.QuestionDao;
import next.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateFormQuestionController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        long questionId = Long.parseLong(req.getParameter("questionId"));
        QuestionDao questionDao = new QuestionDao();
        Question question = questionDao.findByQuestionId(questionId);

        req.setAttribute("question", question);
        return "/qna/updateForm.jsp";
    }
}
