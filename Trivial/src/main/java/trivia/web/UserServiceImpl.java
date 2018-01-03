package trivia.web;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import trivia.dao.UserDao;
import trivia.domain.*;
import trivia.web.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Resource
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
