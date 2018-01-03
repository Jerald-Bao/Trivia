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
	public User userValidate(User model) {
		User user = userDao.findByUsername(model.getUsername());
		 if (user == null||!user.getPassword().equals(model.getPassword())) {
			 return null;
		 }
		 else 
			 return user;
	}
}
