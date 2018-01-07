package trivia.domain;

public class Player {
	private int playerId;
	private int position;
	private String playerName;
	
	
	public Player(int gamerId, int position, String playerName) {
		super();
		this.playerId = gamerId;
		this.position = position;
		this.playerName = playerName;
	}
	
	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int gamerId) {
		this.playerId = gamerId;
	}

	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
}
