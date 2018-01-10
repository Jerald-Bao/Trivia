//CreateRoom
package trivia.domain;

public class crMessage extends returnMessage{
	private int roomId;
	private Player host;

	public int getRoomId() {
		return roomId;
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
	
}
