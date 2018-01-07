//EnterRoom
package trivia.domain;

import java.util.List;

public class erMessage extends returnMessage{
	private static String type="EnterRoom";
	private Player user;
    private List<Player> players;

    
	public static String getType() {
		return type;
	}
	public static void setType(String type) {
		erMessage.type = type;
	}
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
}
