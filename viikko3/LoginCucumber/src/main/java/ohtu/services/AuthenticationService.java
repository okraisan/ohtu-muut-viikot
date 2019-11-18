package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import ohtu.data_access.UserDao;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        // validity check of username and password

        // Username must match this regex
        if (!username.matches("[a-z]{3,}")) {
          return true;
        }
        
        // Password must be 8 characters or more
        if (password.length() < 8) {
          return true;
        }
        
        // Password must have one non-letter
        for (int i = 0; i < password.length(); i++) {
          if (!Character.isLetter(password.charAt(i))) {
            return false;
          }
        }
        
        return true;
    }
}
