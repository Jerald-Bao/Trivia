package trivia.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import trivia.dao.UserDao;
import trivia.domain.User;

/**
 * �û�������
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Resource
    private UserDao userDao;

    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(User model, HttpServletRequest request) {
    	
    	HttpSession session=request.getSession();
        ModelAndView mav = new ModelAndView();
        User user=userService.userValidate(model);
        if (user!=null) {
            mav.setViewName("lobby");
        	mav.addObject("user",user);
        	
        	session.setAttribute("user", user);
        } else {
            mav.setViewName("login");
//            mav.addObject("msg","�û��������ڻ��������!");
            session.setAttribute("msg","�û��������ڻ��������!");
        }
       return mav;
    }

}
