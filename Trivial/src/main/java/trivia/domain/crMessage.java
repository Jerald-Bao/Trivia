//CreateRoom
package trivia.domain;

public class crMessage extends returnMessage{
	private static String type="CreateRoom";
	private int roomId;
	private Player host;

	
	public static String getType() {
		return type;
	}

	public static void setType(String type) {
		crMessage.type = type;
	}

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
