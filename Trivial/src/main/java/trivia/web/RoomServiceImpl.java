package trivia.web;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import trivia.dao.*;
import trivia.domain.*;
import trivia.webSocket.MyWebSocketHandler;

@Service
public class RoomServiceImpl implements RoomService{
	
	@Autowired
    private QuestionDao quesDao;
	
	@Autowired
    private UserDao userDao;
	
	@Override
	public returnMessage handleMessage(Message msg,int uid)
	{
		int fromId=uid;
		
		if(msg.getRequest()=="RoomList"){
			rlMessage rlmsg=new rlMessage();
			List<GameRoom> grs=new ArrayList<GameRoom>();
			for(int i=msg.getFrom()-1;i<msg.getTo();i++) {
				grs.add(MyWebSocketHandler.getRoomList().get(i));
			}
			rlmsg.setRoomList(grs);
			rlmsg.getToId().add(fromId);
			return rlmsg;
		}
		else if(msg.getRequest()=="EnterRoom"){
			erMessage ermsg=new erMessage();
	        ermsg.setResult(false);
			//从roomList找到gameRoom
	        for(GameRoom gr:MyWebSocketHandler.getRoomList()){
	        	if(gr.getRoomId()==msg.getRoomId()){
	        		 List<Player> players=gr.getPlayers();
	        		 ArrayList<Integer> remainPos=new ArrayList<Integer>();

	        		 //添加新玩家
	        		 if(gr.getGamerNum()<4)//存在空位
	                 {
		        		 //得到空余位置
		        		 for(int i=0;i<4;i++){
		        			 remainPos.add(i);
		        		 }
		        		 for(Player p:players){
		        			 int pos=p.getPosition();
		        			 remainPos.remove(pos);
		        		 }
		        		 
	        			 int userPos=remainPos.get(0);
	        			 Player user=new Player(fromId,userPos,userDao.findByUserid(fromId).getUsername());
	        			 ermsg.setUser(user);
	        			 ermsg.setPlayers(players);
	        			 
	        			 players.add(user);
	        			 gr.setPlayers(players);
	        			 gr.setGamerNum(gr.getGamerNum()+1);
	        			 for(Player p:gr.getPlayers()) {
	        				 ermsg.getToId().add(p.getPlayerId());
	        			 }
	        			 ermsg.setResult(true);
	        		 }        	
	        		 return ermsg;
	        	}
	        }
	        return ermsg;
		}
		else if(msg.getRequest()=="CreateRoom"){
			crMessage crmsg=new crMessage();
			GameRoom gr=new GameRoom();
			gr.setRoomId(MyWebSocketHandler.getRoomList().size()+1);
			Player host=new Player(fromId,1,userDao.findByUserid(fromId).getUsername());
			gr.setHost(host);
			gr.setGamerNum(gr.getGamerNum()+1);
			
			MyWebSocketHandler.getRoomList().add(gr);
			crmsg.setRoomId(gr.getRoomId());
			crmsg.setHost(host);
			crmsg.getToId().add(fromId);
			
			return crmsg;
		}
		else if(msg.getRequest()=="ExitRoom") {
			exMessage exmsg=new exMessage();
			exmsg.setResult(true);
			//寻找退出房间
			for(GameRoom gr:MyWebSocketHandler.getRoomList()) {
				if(gr.getRoomId()==msg.getRoomId()) {
					//无人则销毁房间
					if(gr.getGamerNum()==1) {
						MyWebSocketHandler.getRoomList().remove(gr);
						exmsg.setResult(false);
						break;
					}
					//仍有玩家在房间
					for(Player p:gr.getPlayers()) {
						//找到退出玩家
						if(p.getPlayerId()==fromId) {
							//移除退出玩家
							gr.getPlayers().remove(p);
							gr.setGamerNum(gr.getGamerNum()-1);	
							//如果是房主退出，变更房主为下一位玩家
							if(gr.getHost().getPlayerId()==fromId) {
								Player host=gr.getPlayers().get(0);
								gr.setHost(host);
							}	
							break;
						}
					}
					exmsg.setHost(gr.getHost());
					break;
				}
			}
			
			List<GameRoom> grs=new ArrayList<GameRoom>();
			for(int i=0;i<10;i++) {
				grs.add(MyWebSocketHandler.getRoomList().get(i));
			}
			exmsg.setRoomList(grs);
			exmsg.getToId().add(fromId);
			
			return exmsg;
		}
		
		return new returnMessage();
	}

}
