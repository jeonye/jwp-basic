package next.controller;

import core.db.DataBase;
import next.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateFormUserController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String userId = req.getParameter("userId");
        User user = null;

        if(!"".equals(userId) && userId != null) {
            user = DataBase.findUserById(userId);

        } else {
            HttpSession session = req.getSession();
            Object value = session.getAttribute("user");
            if(value != null) {
                user = (User) value;
            }
        }

        req.setAttribute("user", user);
        return "/user/updateForm.jsp";
    }
}
