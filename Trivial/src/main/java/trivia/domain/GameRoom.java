package trivia.domain;

import java.util.ArrayList;
import java.util.List;

public class GameRoom {

	private int roomId;
	private Player host;
    private List<Player> players;
	private int gamerNum=0;
	private Game game;
	
	public GameRoom(){
		players=new ArrayList();
	}
	public int getRoomId()
	{
		return this.roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public Player getHost() {
		return host;
	}
	public void setHost(Player host) {
		this.host = host;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public int getGamerNum() {
		return gamerNum;
	}
	public void setGamerNum(int gamerNum) {
		this.gamerNum = gamerNum;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	
}
