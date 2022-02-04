package next.controller.qna;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.model.User;

public class ShowController extends AbstractController {
    private QuestionDao questionDao;
    private AnswerDao answerDao;
    private Question question;
    private List<Answer> answers;

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse response) throws Exception {
        answerDao = new AnswerDao();
        questionDao = new QuestionDao();

        Long questionId = Long.parseLong(req.getParameter("questionId"));

        question = questionDao.findById(questionId);
        answers = answerDao.findAllByQuestionId(questionId);

        // 글쓴이와 로그인 유저가 동일한지 확인
        HttpSession session = req.getSession();
        User loginedUser = (User) session.getAttribute("user");

        boolean isOwner = false;
        if(loginedUser != null && loginedUser.getName().equals(question.getWriter())) {
            isOwner = true;
        }

        // 질문 삭제 여부 확인
        // 조건1 : 답변이 없는 경우
        boolean canDelete = false;
        if(answers.size() == 0) {
            canDelete = true;
        }

        // 조건2 : 질문자와 답변자가 같은 경우
        String answerWriter = null;
        boolean isOneAnswerWriter = false;

        for(int i=0; i<answers.size(); i++) {
            Answer answer = answers.get(i);
            if(i == 0) {
                answerWriter = answer.getWriter();
                isOneAnswerWriter = true;
            } else if(!answerWriter.equals(answer.getWriter())) {
                // 여러명이 답변을 단 경우 Stop
                isOneAnswerWriter = false;
                break;
            }
        }

        if(isOneAnswerWriter && question.getWriter().equals(answerWriter)) {
            canDelete = true;
        }

        ModelAndView mav = jspView("/qna/show.jsp");
        mav.addObject("question", question);
        mav.addObject("answers", answers);
        mav.addObject("isOwner", isOwner);
        mav.addObject("canDelete", canDelete);
        return mav;
    }
}
