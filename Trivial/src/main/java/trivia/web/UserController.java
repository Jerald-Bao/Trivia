package trivia.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
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
    public ModelAndView login(@Param("username") String username,@Param("password") String password, HttpServletRequest request) {
    	
    	HttpSession session=request.getSession();
    	Map<String,Object> data = new HashMap<String,Object>();
    	
    	if(username==null){
    		data.put("msg","�û�������Ϊ��!");
        	return new ModelAndView("redirect:/login.jsp",data);
    	}
    	else if(password==null){
    		data.put("msg","���벻��Ϊ��!");
        	return new ModelAndView("redirect:/login.jsp",data);
    	}
    	else {
    		User user=userService.userValidate(username,password);   
            if (user==null) {
                data.put("msg","�û��������ڻ��������!");
            	return new ModelAndView("redirect:/login.jsp",data);
            } else {
              	session.setAttribute("user", user);
            	data.put("user",user);
            	return new ModelAndView("lobby",data);
            }
    	}
    }

}
