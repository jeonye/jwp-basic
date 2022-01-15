package next.controller.qna;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.mvc.Controller;
import next.dao.AnswerDao;
import next.model.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;

public class DeleteAnswerController implements Controller {

    private static final Logger log = LoggerFactory.getLogger(DeleteAnswerController.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        long answerId = Long.parseLong(req.getParameter("answerId"));
        log.debug("answerId : {}", answerId);

        AnswerDao answerDao = new AnswerDao();
        answerDao.delete(answerId);

        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(answerId);

        // 데이터 생성 후 바로 응답을 보내기 때문에 이동할 페이지가 없음
        return null;
    }
}
