package trivia.web;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import trivia.dao.*;
import trivia.domain.*;
import trivia.webSocket.MyWebSocketHandler;

@Service
public class MessageServiceImpl implements MessageService{
	
	@Autowired
    private QuestionDao quesDao;
	
	@Autowired
    private UserDao userDao;
	
	@Override
	public returnMessage handleMessage(Message msg,int uid)
	{
		returnMessage rmsg = new returnMessage();
		switch(msg.getRequest()) {
		case "RoomList":
			rmsg=roomList(msg,uid);
			break;
		case "EnterRoom":
			rmsg=enterRoom(msg,uid);
			break;
		case "CreateRoom":
			rmsg=createRoom(msg,uid);
			break;
		case "ExitRoom":
			rmsg=exitRoom(msg,uid);
			break;
		case "GameStart":
			rmsg=startGame(msg,uid);
			break;
		case "Roll":
			rmsg=roll(msg,uid);
			break;
		case "PlayersAnswer":
			rmsg=playersAnswer(msg,uid);
			break;
	    default:
		    break;
		}
		
		return rmsg;
	}

	public rlMessage roomList(Message msg,int fromId) {
		rlMessage rlmsg=new rlMessage();
		List<GameRoom> grs=new ArrayList<GameRoom>();
		for(int i=msg.getFrom()-1;i<msg.getTo();i++) {
			grs.add(MyWebSocketHandler.getRoomList().get(i));
		}
		rlmsg.setRoomList(grs);
		rlmsg.getToId().add(fromId);
		return rlmsg;
	}
	
	public erMessage enterRoom(Message msg,int fromId) {
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

	public crMessage createRoom(Message msg,int fromId) {
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

	public exMessage exitRoom(Message msg,int fromId) {
		exMessage exmsg=new exMessage();
		exmsg.setResult(true);
		//寻找退出房间
		for(GameRoom gr:MyWebSocketHandler.getRoomList()) {
			if(gr.getRoomId()==msg.getRoomId()) {
				//无人则销毁房间
				if(gr.getGamerNum()==1) {
					MyWebSocketHandler.getRoomList().remove(gr);
					exmsg.setResult(false);
					exmsg.getToId().add(fromId);
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
					}
					exmsg.getToId().add(p.getPlayerId());
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
		
		return exmsg;
	}

	public returnMessage startGame(Message msg,int fromId) {
		returnMessage rmsg=new returnMessage();
		rmsg.setResult(false);
		
		for(GameRoom gr:MyWebSocketHandler.getRoomList()) {
			if(gr.getRoomId()==msg.getRoomId()) {
				//人满开始游戏
				if(gr.getGamerNum()==4) {
					rmsg.setResult(true);
					gr.setGame(new Game(gr.getPlayers()));
					gr.getGame().setPopQuestions(quesDao.findByCategory("Pop"));
					gr.getGame().setScienceQuestions(quesDao.findByCategory("Science"));
					gr.getGame().setSportQuestions(quesDao.findByCategory("Sport"));
					gr.getGame().setRockQuestions(quesDao.findByCategory("Rock"));
					for(Player p:gr.getPlayers()) {
						rmsg.getToId().add(p.getPlayerId());
					}
				}
				else {
					rmsg.setResult(false);
					rmsg.getToId().add(fromId);
				}
				break;
			}
		}		
		return rmsg;
	}

	public roMessage roll(Message msg,int fromId) {
		roMessage romsg=new roMessage();
		int rollNum=0;
		int location=0;
		String category;
		Question question=new Question();
		
		for(GameRoom gr:MyWebSocketHandler.getRoomList()) {
			if(gr.getRoomId()==msg.getRoomId()) {
				Game game=gr.getGame();
				rollNum=game.roll();
				for(Player p:game.getPlayers()) {
					if(p.getPlayerId()==fromId) {
						location=game.move(p,rollNum);
					}
					romsg.getToId().add(p.getPlayerId());
				}
				category=game.getCurrentCategory(location);
				question=game.getQuestion(category);
				game.setCurrengQuestion(question);
				gr.setGame(game);				
				break;
			}
		}
		
		romsg.setRollUserId(fromId);
		romsg.setRollNum(rollNum);
		romsg.setLocation(location);
		romsg.setQuestion(question);
		return romsg;	
	}
	
	public paMessage playersAnswer(Message msg,int fromId) {
		paMessage pamsg=new paMessage();
		pamsg.setGameOver(false);
		pamsg.setResult(false);
		int winPoint=1;
		int point=0;
		
		for(GameRoom gr:MyWebSocketHandler.getRoomList()) {
			if(gr.getRoomId()==msg.getRoomId()) {
				Game game=gr.getGame();
				//回答正确
				if(msg.getPlayerAnswer()==game.getCurrengQuestion().getAnswer()) {
					for(Player p:game.getPlayers()) {
						if(p.getPlayerId()==fromId) {
							point=game.addPoint(p,winPoint);
						}
						pamsg.getToId().add(p.getPlayerId());
					}	
					pamsg.setResult(true);
					//如果玩家获胜
					if(point==6) {
						pamsg.setGameOver(true);
						int[] playersId= {0,0,0,0};
						for(Player p:game.getPlayers()) {							
							int index=p.getPosition()-1;
							playersId[index]=p.getPlayerId();
							
							if(p.getPlayerId()==fromId) 
								userDao.winnerUpdateById(p.getPlayerId());
							else
								userDao.loserUpdateById(p.getPlayerId());
						}
//						historyDao.addRecord(playersId,fromId,game.getPoint());
						gr.setGame(null);
					}
				}
				break;
			}
		}
		
		pamsg.setAnsUserId(fromId);
		pamsg.setPoint(point);
		return pamsg;
	}
}
