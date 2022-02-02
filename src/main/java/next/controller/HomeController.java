package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import next.dao.QuestionDao;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomeController extends AbstractController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);
    private QuestionDao questionDao = new QuestionDao();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 로그인 정보 조회
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // 로그인 여부 설정
        boolean isLogined = false;
        if(user != null && !"".equals(user.getUserId())) {
            isLogined = true;
        }
        log.debug("isLogined : {}", isLogined);

        return jspView("home.jsp").addObject("questions", questionDao.findAll())
                .addObject("isLogined", isLogined);
    }
}
