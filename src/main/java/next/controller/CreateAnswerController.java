package next.controller;

import core.mvc.Controller;
import next.dao.AnswerDao;
import next.model.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateAnswerController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(CreateAnswerController.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        AnswerDao answerDao = new AnswerDao();
        long answerId = answerDao.findMaxAnswerId() + 1;

        HttpSession session = req.getSession();
        String loginUser = "";

        if(session.getAttribute(UserSessionUtils.USER_SESSION_KEY) != null) {
            loginUser = session.getAttribute(UserSessionUtils.USER_SESSION_KEY).toString();
        }

        long questionId = Long.parseLong(req.getParameter("questionId"));

        Answer answer = new Answer(answerId, loginUser, req.getParameter("answer"),
                null, questionId);

        answerDao.insert(answer);
        answerDao.updateForAnswerCount(questionId, 0);

        return "redirect:/";
    }
}
