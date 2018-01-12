package trivia.web;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import trivia.BaseTest;
import trivia.domain.*;
public class ServiceTest extends BaseTest{
	
	private User users[];
	private Message msg[];
	@Autowired
	private MessageService ms;
	
	@Autowired
	private UserService us;
	
    @Before
    public void prepare(){
        System.out.println(" before ：所有的测试方法之前都先执行这个方法");
        users=new User[5];
		users[0]=new User(1,"player0","psword","","male",3,2);
		users[1]=new User(2,"player1","psword","","male",3,2);
		users[2]=new User(3,"player2","psword","","male",3,2);
		users[3]=new User(4,"player3","psword","","male",3,2);
		users[4]=new User(5,"player4","psword","","male",3,2);
		
		msg= new Message[15];
		for (int i=0;i<15;i++)
			msg[i]=new Message();

		msg[0].setRequest("CreateRoom");
		msg[1].setRequest("EnterRoom");
		msg[2].setRequest("EnterRoom");
		msg[3].setRequest("EnterRoom");
		msg[4].setRequest("User");
		msg[5].setRequest("RoomList");
		msg[5].setFrom(0);
		msg[5].setTo(2);
		msg[6].setRequest("Roll");
		msg[6].setRoomId(0);
		msg[7].setRequest("PlayersAnswer");
		msg[7].setPlayersAnswer(1);
		msg[7].setRoomId(0);
		msg[8].setRequest("ExitRoom");
		msg[8].setRoomId(1);
		msg[9].setRequest("Default");
		msg[10].setRequest("EnterRoom");
		msg[10].setRoomId(3);
		msg[11].setRequest("ExitRoom");
		msg[11].setRoomId(3);
		msg[12].setRequest("EnterRoom");
		msg[12].setRoomId(2);
		msg[13].setRequest("ExitRoom");
		msg[13].setRoomId(2);
    }
    
    @Test
	public void testMessageService() throws Exception {
    	
    	//host建房
    	crMessage result1=(crMessage) ms.handleMessage(msg[0], users[0].getUserid());
    	assertEquals("CreateRoom",result1.getType());
    	msg[1].setRoomId(result1.getRoomId());
    	msg[2].setRoomId(result1.getRoomId());
    	msg[3].setRoomId(result1.getRoomId());
    	
		//第二个玩家进入
		erMessage result2=(erMessage) ms.handleMessage(msg[1], users[1].getUserid());
		assertEquals("EnterRoom",result2.getType());
		assertEquals(result1.getRoomId(),result2.getRoomId());
		assertEquals(1,findPosition(result2.getPlayers(),users[1].getUserid()));
		assertEquals(false,result2.isGamestart());
		
		//第三个玩家进入
		erMessage result3=(erMessage) ms.handleMessage(msg[2], users[2].getUserid());
		assertEquals("EnterRoom",result3.getType());
		assertEquals(result1.getRoomId(),result3.getRoomId());
		assertEquals(2,findPosition(result3.getPlayers(),users[2].getUserid()));
		assertEquals(false,result3.isGamestart());
		
		//第四个玩家进入
		erMessage result4=(erMessage) ms.handleMessage(msg[3], users[3].getUserid());
		assertEquals("EnterRoom",result4.getType());
		assertEquals(result1.getRoomId(),result4.getRoomId());
		assertEquals(3,findPosition(result4.getPlayers(),users[3].getUserid()));
		assertEquals(true,result4.isGamestart());
		
		//第五个玩家进入失败
		erMessage result5=(erMessage) ms.handleMessage(msg[3], users[4].getUserid());
		assertEquals("EnterRoom",result5.getType());
		assertEquals(false,result5.isResult());
		
		//请求玩家信息
		userMessage umsg=(userMessage)ms.handleMessage(msg[4], users[0].getUserid());
		assertEquals("User",umsg.getType());
		assertEquals("1",umsg.getUser().getUsername());
		
		//请求房间列表
		rlMessage rlmsg=(rlMessage)ms.handleMessage(msg[5], users[0].getUserid());
		assertEquals("RoomList",rlmsg.getType());
		assertEquals(result1.getRoomId(),rlmsg.getRoomList().get(0).getRoomId());
		
		//roll roomid does not exit
		roMessage romsg=(roMessage)ms.handleMessage(msg[6], users[0].getUserid());
		assertEquals("Roll",romsg.getType());
		assertEquals(0,romsg.getRollNum());
		//循环roll和answer
		//roll roomid=1
		msg[6].setRoomId(1);
		roMessage romsg2=(roMessage)ms.handleMessage(msg[6], users[0].getUserid());
		assertEquals("Roll",romsg2.getType());
		assertEquals(0,romsg2.getCurRollPos());
		assertEquals(1,romsg2.getPreRollPos());
		//answer roomid does not exit
		paMessage pamsg=(paMessage)ms.handleMessage(msg[7], users[0].getUserid());
		assertEquals("PlayersAnswer",pamsg.getType());
		//answer roomid=1
		msg[7].setRoomId(1);
		paMessage pamsg2=(paMessage)ms.handleMessage(msg[7], users[0].getUserid());
		assertEquals("PlayersAnswer",pamsg2.getType());
		assertEquals(0,pamsg2.getAnsUserPos());
		if(pamsg2.getCorrectAnswer()!=pamsg2.getPlayersAnswer()) {
			assertEquals(false,pamsg2.isResult());
			assertEquals(0,pamsg2.getPoint());
		}
		else {
			assertEquals(true,pamsg2.isResult());
			assertEquals(1,pamsg2.getPoint());		
		}
		assertEquals(false,pamsg2.isGameOver());
		int i=1;
		while(!pamsg2.isGameOver()) {
			romsg2=(roMessage)ms.handleMessage(msg[6], users[i].getUserid());
			pamsg2=(paMessage)ms.handleMessage(msg[7], users[i].getUserid());
			//TODO
			if(pamsg2.getPoint()==6)
				assertEquals(true,pamsg2.isGameOver());
			i++;
			if(i==4)i=0;
		}
		
		//host建房
    	crMessage crmsg=(crMessage) ms.handleMessage(msg[0], users[0].getUserid());
    	assertEquals("CreateRoom",crmsg.getType());
    	msg[1].setRoomId(crmsg.getRoomId());
    	msg[2].setRoomId(crmsg.getRoomId());
    	msg[3].setRoomId(crmsg.getRoomId());
    	
		//第二个玩家进入
		erMessage ermsg=(erMessage) ms.handleMessage(msg[1], users[1].getUserid());
		assertEquals("EnterRoom",ermsg.getType());
		assertEquals(crmsg.getRoomId(),ermsg.getRoomId());
		assertEquals(1,findPosition(ermsg.getPlayers(),users[1].getUserid()));
		assertEquals(false,ermsg.isGamestart());
		
		//第一个玩家退出
		exMessage exmsg=(exMessage) ms.handleMessage(msg[8], users[0].getUserid());
		assertEquals("ExitRoom",exmsg.getType());
		assertEquals(1,exmsg.getExitUserId());
		assertEquals(2,exmsg.getHost().getPlayerId());
		
		//第二个玩家退出，房间销毁
		exMessage exmsg2=(exMessage) ms.handleMessage(msg[8], users[1].getUserid());
		assertEquals("ExitRoom",exmsg2.getType());
		assertEquals(2,exmsg2.getExitUserId());
		
		//Branch填充
		//default test
		returnMessage remsg=ms.handleMessage(msg[9], 0);
		assertEquals(null,remsg);
		//to<=roomsize test
		crMessage crmsg2=(crMessage) ms.handleMessage(msg[0], 1);
		crMessage crmsg3=(crMessage) ms.handleMessage(msg[0], 2);
		rlMessage rlmsg2=(rlMessage)ms.handleMessage(msg[5], 1);
		assertEquals(crmsg2.getRoomId(),rlmsg2.getRoomList().get(0).getRoomId());
		assertEquals(crmsg3.getRoomId(),rlmsg2.getRoomList().get(1).getRoomId());
		//Enter roomid=3
		erMessage ermsg2=(erMessage) ms.handleMessage(msg[10], users[2].getUserid());
		assertEquals(false,ermsg2.isResult());
		//Exit roomid=3
		exMessage exmsg3=(exMessage) ms.handleMessage(msg[11], users[2].getUserid());
		//Enter roomid=2
		erMessage ermsg3=(erMessage) ms.handleMessage(msg[12], 3);
		//Exit roomid=2,exit user is not host
		exMessage exmsg4=(exMessage) ms.handleMessage(msg[13], 3);
	}
    
    @Test
	public void testUserService() throws Exception {
    	String username[]= {"1","null"};
    	String password[]= {"1","error"};
    	User user=us.userValidate(username[0], password[0]);
    	assertEquals("1",user.getUsername());
    	user=us.userValidate(username[0], password[1]);
    	assertEquals(null,user);
    	user=us.userValidate(username[1], password[0]);
    	assertEquals(null,user);
    }
    
    @After
    public void after(){
        System.out.println(" after ：所有的测试方法之后都先执行这个方法");
    }
	int findPosition(List<Player> list,int userId)
	{
		int pos=-1;
		for (Player p:list)
			if (userId==p.getPlayerId()) 
				pos=p.getPosition();
		return pos;
	}
}
