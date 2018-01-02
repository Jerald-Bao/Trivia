package webSocket;

import trivia.dao.UserDao;
import trivia.domain.User;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * websocket握手拦截器
 * 拦截握手前，握手后的两个切面
 */
public class MyHandShakeInterceptor implements HandshakeInterceptor {
	private UserDao userDao;
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        
    	System.out.println("Websocket:用户[ID:" + ((ServletServerHttpRequest) serverHttpRequest).getServletRequest().getSession(false).getAttribute("user") + "]已经建立连接");
        
    	if (serverHttpRequest instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) serverHttpRequest;
            HttpSession session = servletRequest.getServletRequest().getSession(false);
            
            // 标记用户||用户登录逻辑
            User model = (User) session.getAttribute("user");
            User user = userDao.findByUsername(model.getUsername());
            if(user == null || !user.getPassword().equals(model.getPassword())){
                System.out.println("账号不存在或密码错误");
                session.setAttribute("result","LoginFail");
                session.setAttribute("message","账号不存在或密码错误");
                return false;
            }else{
                map.put("uid", model.getUserid());//为服务器创建WebSocketSession做准备
                System.out.println("用户id："+model.getUserid()+" 被加入");
                session.setAttribute("result","LoginSuccess");
            }
        }
        return true;
    }

    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

    }
}
