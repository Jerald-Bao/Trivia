//ExitMessage
package trivia.domain;

import java.util.List;

public class exMessage extends returnMessage{
	private static String type="ExitRoom";
	private int exitUserId;
	private Player host;
	private List<GameRoom> RoomList;
	
	public static String getType() {
		return type;
	}
	public static void setType(String type) {
		exMessage.type = type;
	}
	public int getExitUserId() {
		return exitUserId;
	}
	public void setExitUserId(int exitUserId) {
		this.exitUserId = exitUserId;
	}
	public Player getHost() {
		return host;
	}
	public void setHost(Player host) {
		this.host = host;
	}
	public List<GameRoom> getRoomList() {
		return RoomList;
	}
	public void setRoomList(List<GameRoom> roomList) {
		RoomList = roomList;
	}
	
	
}
