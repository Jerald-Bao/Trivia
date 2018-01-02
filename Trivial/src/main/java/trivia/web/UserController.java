package trivia.web;

import java.io.IOException;

import javax.annotation.Resource;
import javax.websocket.*;  
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import trivia.dao.UserDao;
import trivia.domain.User;
import Test4Development.Ws;
/**
 * �û�������
 */
@Controller
public class UserController {
    @Resource
    private UserDao userDao;

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public void login(User model, Session session) {
        User user = userDao.findByUsername(model.getUsername());
        Ws websocket=new Ws();
        websocket.onOpen(session);
        if (user == null || !user.getPassword().equals(model.getPassword())) {
        	try {
				websocket.sendMessage("{\"result\": \"LoginFail\", \"message\" : \"账号不存在或密码错误\"}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//            ModelAndView mav = new ModelAndView();
//            mav.setViewName("index");
            return;
        } else {
        	try {
				websocket.sendMessage("{ \"result\": \"LoginSuccess\"}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return;
        }
    }
}
