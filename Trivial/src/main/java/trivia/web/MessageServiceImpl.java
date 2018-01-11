package trivia.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import trivia.dao.*;
import trivia.domain.*;
import trivia.webSocket.MyWebSocketHandler;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private QuestionDao quesDao;

	@Autowired
	private UserDao userDao;

	@Override
	public returnMessage handleMessage(Message msg, int uid) {
		returnMessage rmsg = new returnMessage();
		switch (msg.getRequest()) {
		case "User":
			rmsg = sendUser(msg, uid);
			break;
		case "RoomList":
			rmsg = roomList(msg, uid);
			break;
		case "EnterRoom":
			rmsg = enterRoom(msg, uid);
			break;
		case "CreateRoom":
			rmsg = createRoom(msg, uid);
			break;
		case "ExitRoom":
			rmsg = exitRoom(msg, uid);
			break;
		case "Roll":
			rmsg = roll(msg, uid);
			break;
		case "PlayersAnswer":
			rmsg = playersAnswer(msg, uid);
			break;
		default:
			break;
		}

		return rmsg;
	}

	public userMessage sendUser(Message msg, int fromId) {
		userMessage umsg = new userMessage();
		User user = userDao.findByUserid(fromId);
		umsg.setUser(user);
		umsg.getToId().add(fromId);
		umsg.setType("User");
		return umsg;
	}

	public rlMessage roomList(Message msg, int fromId) {
		rlMessage rlmsg = new rlMessage();
		List<GameRoom> grs = new ArrayList<GameRoom>();
		for (int i = msg.getFrom(); i < (msg.getTo() > MyWebSocketHandler.getRoomList().size()
				? MyWebSocketHandler.getRoomList().size()
				: msg.getTo()); i++) {
			grs.add(MyWebSocketHandler.getRoomList().get(i));
		}
		rlmsg.setRoomList(grs);
		rlmsg.getToId().add(fromId);
		rlmsg.setType("RoomList");
		return rlmsg;
	}

	public erMessage enterRoom(Message msg, int fromId) {
		erMessage ermsg = new erMessage();
		ermsg.setResult(false);
		ermsg.setType("EnterRoom");
		ermsg.setRoomId(msg.getRoomId());
		// 从roomList找到gameRoom
		for (GameRoom gr : MyWebSocketHandler.getRoomList()) {
			if (gr.getRoomId() == msg.getRoomId()) {
				List<Player> players = gr.getPlayers();

				// 添加新玩家
				int pos = 0;
				if (gr.getGamerNum() < 4)// 存在空位
				{
					// 得到空余位置
					boolean flag = true;
					for (int i = 0; i < 4; i++) {
						flag = true;
						for (Player p : players)
							if (p.getPosition() == i)
								flag = false;
						if (flag) {
							pos = i;
							break;
						}
					}

					int userPos = pos;
					Player user = new Player(fromId, userPos, userDao.findByUserid(fromId).getUsername());
					ermsg.setUser(user);
					ermsg.setPlayers(players);

					players.add(user);
					gr.setPlayers(players);
					gr.setGamerNum(gr.getGamerNum() + 1);
					for (Player p : gr.getPlayers()) {
						ermsg.getToId().add(p.getPlayerId());
					}
					ermsg.setResult(true);
				}
				if (gr.getGamerNum() == 4) {
					gr.setGame(new Game(gr.getPlayers()));
					gr.getGame().setPopQuestions(quesDao.findByCategory("Pop"));
					gr.getGame().setScienceQuestions(quesDao.findByCategory("Science"));
					gr.getGame().setSportsQuestions(quesDao.findByCategory("Sports"));
					gr.getGame().setRockQuestions(quesDao.findByCategory("Rock"));
					ermsg.setGamestart(true);
				}
				return ermsg;
			}
		}
		return ermsg;
	}

	public crMessage createRoom(Message msg, int fromId) {
		crMessage crmsg = new crMessage();
		crmsg.setType("CreateRoom");
		GameRoom gr = new GameRoom();
		gr.setRoomId(MyWebSocketHandler.getRoomList().size() + 1);
		Player host = new Player(fromId, 0, userDao.findByUserid(fromId).getUsername());
		gr.setHost(host);
		gr.setGamerNum(gr.getGamerNum() + 1);
		gr.getPlayers().add(host);
		MyWebSocketHandler.getRoomList().add(gr);
		crmsg.setRoomId(gr.getRoomId());
		crmsg.setHost(host);

		crmsg.getToId().add(fromId);

		return crmsg;
	}

	public exMessage exitRoom(Message msg, int fromId) {
		exMessage exmsg = new exMessage();
		exmsg.setType("ExitRoom");
		exmsg.setResult(true);
		// 寻找退出房间
		for (GameRoom gr : MyWebSocketHandler.getRoomList()) {
			if (gr.getRoomId() == msg.getRoomId()) {
				// 无人则销毁房间
				if (gr.getGamerNum() == 1) {
					MyWebSocketHandler.getRoomList().remove(gr);
					exmsg.setResult(false);
					exmsg.getToId().add(fromId);
					break;
				}
				// 仍有玩家在房间
				for (Player p : gr.getPlayers()) {
					exmsg.getToId().add(p.getPlayerId());
				}
				for (Player p : gr.getPlayers()) {
					// 找到退出玩家
					if (p.getPlayerId() == fromId) {
						// 移除退出玩家
						gr.getPlayers().remove(p);
						gr.setGamerNum(gr.getGamerNum() - 1);
						// 如果是房主退出，变更房主为下一位玩家
						if (gr.getHost().getPlayerId() == fromId) {
							Player host = gr.getPlayers().get(0);
							gr.setHost(host);
						}
						break;
					}	
				}
				exmsg.setHost(gr.getHost());
				break;
			}
		}
		exmsg.setExitUserId(fromId);
		return exmsg;
	}

	// public returnMessage startGame(Message msg,int fromId) {
	// returnMessage rmsg=new returnMessage();
	// rmsg.setResult(false);
	//
	// for(GameRoom gr:MyWebSocketHandler.getRoomList()) {
	// if(gr.getRoomId()==msg.getRoomId()) {
	// //人满开始游戏
	// if(gr.getGamerNum()==4) {
	// rmsg.setResult(true);
	// gr.setGame(new Game(gr.getPlayers()));
	// gr.getGame().setPopQuestions(quesDao.findByCategory("Pop"));
	// gr.getGame().setScienceQuestions(quesDao.findByCategory("Science"));
	// gr.getGame().setSportQuestions(quesDao.findByCategory("Sport"));
	// gr.getGame().setRockQuestions(quesDao.findByCategory("Rock"));
	// for(Player p:gr.getPlayers()) {
	// rmsg.getToId().add(p.getPlayerId());
	// }
	// }
	// else {
	// rmsg.setResult(false);
	// rmsg.getToId().add(fromId);
	// }
	// break;
	// }
	// }
	// return rmsg;
	// }

	public roMessage roll(Message msg, int fromId) {
		roMessage romsg = new roMessage();
		romsg.setType("Roll");
		int rollNum = 0;
		int categoryLocation = 0;
		int curRollPos = 0;
		int preRollPos = 0;
		String category;
		Question question = new Question();

		for (GameRoom gr : MyWebSocketHandler.getRoomList()) {
			if (gr.getRoomId() == msg.getRoomId()) {
				Game game = gr.getGame();
				rollNum=game.roll();
				for (Player p : game.getPlayers()) {
					if (p.getPlayerId() == fromId) {
						int index = p.getPosition();
						// 如果用户处于被困状态
						if (game.getLock()[index] == 1) {
							// 不等于4时脱困
							if (rollNum != 4) {
								game.getLock()[index] = 0;
							}
						} else {
							categoryLocation = game.move(p, rollNum);
							category = game.getCurrentCategory(categoryLocation);
							question = game.getQuestion(category);
							game.setCurrengQuestion(question);
						}
						curRollPos = p.getPosition();
						if (curRollPos == 3) {
							preRollPos = 0;
						} else
							preRollPos = curRollPos + 1;
					}
					romsg.getToId().add(p.getPlayerId());
				}
				gr.setGame(game);
				break;
			}
		}

		romsg.setCurRollPos(curRollPos);
		romsg.setPreRollPos(preRollPos);
		romsg.setRollNum(rollNum);
		romsg.setQuestion(question);
		return romsg;
	}

	public paMessage playersAnswer(Message msg, int fromId) {
		paMessage pamsg = new paMessage();
		pamsg.setType("PlayersAnswer");
		pamsg.setGameOver(false);
		pamsg.setResult(false);
		int winPoint = 1;
		int point = 0;
		int correctAns = 0;
		int curPos = 0;

		for (GameRoom gr : MyWebSocketHandler.getRoomList()) {
			if (gr.getRoomId() == msg.getRoomId()) {
				Game game = gr.getGame();
				correctAns = game.getCurrengQuestion().getAnswer();

				for (Player p : game.getPlayers()) {
					if (p.getPlayerId() == fromId) {
						curPos = p.getPosition();
						// 回答正确
						if (msg.getPlayersAnswer() == correctAns) {
							point = game.addPoint(p, winPoint);
							pamsg.setResult(true);
							// 如果玩家获胜
							if (point == 6) {
								pamsg.setGameOver(true);
								int[] playersId = { 0, 0, 0, 0 };
								for (Player player : game.getPlayers()) {
									int index = player.getPosition();
									playersId[index] = player.getPlayerId();

									if (player.getPlayerId() == fromId)
										userDao.winnerUpdateById(player.getPlayerId());
									else
										userDao.loserUpdateById(player.getPlayerId());
								}
								// historyDao.addRecord(playersId,fromId,game.getPoint());
								MyWebSocketHandler.getRoomList().remove(gr);
							}
						}
						// 回答错误
						else {
							int index = p.getPosition();
							game.getLock()[index] = 1;
						}
					}
					pamsg.getToId().add(p.getPlayerId());
				}
				break;
			}
		}

		pamsg.setAnsUserPos(curPos);
		pamsg.setCorrectAnswer(correctAns);
		pamsg.setPlayersAnswer(msg.getPlayersAnswer());
		pamsg.setPoint(point);
		return pamsg;
	}
}
