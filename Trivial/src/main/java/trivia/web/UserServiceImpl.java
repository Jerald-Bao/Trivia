package trivia.web;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import trivia.dao.UserDao;
import trivia.domain.*;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
    private UserDao userDao;
	
	@Override
	public User userValidate(String username,String password) {
		User user = userDao.findByUsername(username);
		 if (user == null||!user.getPassword().equals(password)) {
			 return null;
		 }
		 else 
			 return user;
	}
}
