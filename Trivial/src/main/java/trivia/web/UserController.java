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
 * 用户控制器
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

        ModelAndView mav = new ModelAndView();
        User user=userService.userValidate(model);
        if (user!=null) {
            mav.setViewName("lobby");
        	mav.addObject("user",user);
        	
        	HttpSession session=request.getSession();
        	session.setAttribute("user", user);
        } else {
            mav.setViewName("login");
            mav.addObject("msg","用户名不存在或密码错误!");
        }
       return mav;
    }

}
