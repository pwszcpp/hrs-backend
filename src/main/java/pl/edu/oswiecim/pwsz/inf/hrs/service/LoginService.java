package pl.edu.oswiecim.pwsz.inf.hrs.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginService {
    void logIn(String username, String password);
    void logOut(HttpServletRequest request, HttpServletResponse response);
}
