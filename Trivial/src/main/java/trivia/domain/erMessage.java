//EnterRoom
package trivia.domain;

import java.util.List;

public class erMessage extends returnMessage{
	private Player user;
    private List<Player> players;
    private boolean gamestart;
    private int roomId;

    public Player getUser() {
		return user;
	}
	public void setUser(Player user) {
		this.user = user;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public boolean isGamestart() {
		return gamestart;
	}
	public void setGamestart(boolean gamestart) {
		this.gamestart = gamestart;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
}
