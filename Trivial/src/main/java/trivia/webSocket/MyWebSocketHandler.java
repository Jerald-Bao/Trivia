package trivia.webSocket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.List;

import trivia.domain.*;
import trivia.web.GameService;
import trivia.dao.UserDao;

@Component
public class MyWebSocketHandler implements WebSocketHandler{

    @Autowired
    private GameService gameService;
    @Autowired
    private UserDao userDao;
    private static List<gameRoom> roomList;
	private static List<User> onlineUser;

    //��MyWebSocketHandler�౻����ʱ�ͻᴴ����Map���������
    public static final Map<Integer, WebSocketSession> userSocketSessionMap;

    static {
        userSocketSessionMap = new HashMap<Integer, WebSocketSession>();
    }

    //����ʵ�����Ӻ�
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        int uid = (Integer) webSocketSession.getAttributes().get("uid");
        if (userSocketSessionMap.get(uid) == null) {
            userSocketSessionMap.put(uid, webSocketSession);
            onlineUser.add(userDao.findByUserid(uid));
        }
    }

    //������Ϸ��Ϣ�����߼�
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {

        if(webSocketMessage.getPayloadLength()==0)return;

        //�õ�Socketͨ���е����ݲ�ת��ΪMessage����
        Message msg=new Gson().fromJson(webSocketMessage.getPayload().toString(),Message.class);

        Timestamp now = new Timestamp(System.currentTimeMillis());
        msg.setMessageDate(now);
        //����Ϣ���������ݿ�
//        youandmeService.addMessage(msg.getFromId(),msg.getFromName(),msg.getToId(),msg.getMessageText(),msg.getMessageDate());
        //��roomId��message���ݸ���̨����õ�returnMsg
        returnMessage rmsg=new returnMessage();
        for(gameRoom gr:roomList)
        {
        	if(gr.getRoomId()==msg.getRoomId())
        	{
        		 rmsg=gameService.handleMessage(msg,gr);
        	}
        }
        
        //����Socket��Ϣ��room��user
        for(int i:msg.getToId())
        {
        	sendMessageToUser(i, new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(rmsg)));
        }
        
    }

    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {

    }

    /**
     * �ڴ�ˢ��ҳ����൱�ڶϿ�WebSocket����,ԭ���ھ�̬����userSocketSessionMap�е�
     * WebSocketSession���ɹر�״̬(close)������ˢ�º�ĵڶ������ӷ�����������
     * ��WebSocketSession(open״̬)�ֲ�����뵽userSocketSessionMap��,�����������޷�������Ϣ
     * ���Ӧ���ڹر����������������ȥ��userSocketSessionMap�е�ǰ����close״̬��WebSocketSession��
     * ���´�����WebSocketSession(open״̬)���Լ��뵽userSocketSessionMap��
     * @param webSocketSession
     * @param closeStatus
     * @throws Exception
     */
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {

        System.out.println("WebSocket:"+webSocketSession.getAttributes().get("uid")+"close connection");
        Iterator<Map.Entry<Integer,WebSocketSession>> iterator = userSocketSessionMap.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<Integer,WebSocketSession> entry = iterator.next();
            int uid = (Integer) webSocketSession.getAttributes().get("uid");
            if(entry.getValue().getAttributes().get("uid")==webSocketSession.getAttributes().get("uid")){
                userSocketSessionMap.remove(webSocketSession.getAttributes().get("uid"));
                onlineUser.remove(userDao.findByUserid(uid));
                System.out.println("WebSocket in staticMap:" + webSocketSession.getAttributes().get("uid") + "removed");
            }
        }
    }

    public boolean supportsPartialMessages() {
        return false;
    }

    //������Ϣ��ʵ��
    public void sendMessageToUser(int uid, TextMessage message)
            throws IOException {
        WebSocketSession session = userSocketSessionMap.get(uid);
        if (session != null && session.isOpen()) {
            session.sendMessage(message);
        }
    }
}
