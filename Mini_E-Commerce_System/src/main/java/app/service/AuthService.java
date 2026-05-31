package app.service;

import app.dao.UserDAO;
import app.model.User;

public class AuthService {
    private UserDAO userDAO;
    private User loggedInUser;

    public AuthService() {
        this.userDAO = new UserDAO();
    }

    public boolean login(String username, String password) {
        User user = userDAO.authenticate(username, password);
        if (user != null) {
            this.loggedInUser = user;
            return true;
        }
        return false;
    }

    public void logout() {
        this.loggedInUser = null;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
}
