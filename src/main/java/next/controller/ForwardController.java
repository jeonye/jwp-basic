package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForwardController implements Controller{
    private String moveUrl;

    public ForwardController(String moveUrl) {
        this.moveUrl = moveUrl;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return moveUrl;
    }
}
