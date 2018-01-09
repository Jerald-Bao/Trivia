//RoomList
package trivia.domain;

import java.util.List;

public class rlMessage extends returnMessage{
	private static String type="RoomList";
	private List<GameRoom> RoomList;
	
	
	public static String getType() {
		return type;
	}

	public static void setType(String type) {
		rlMessage.type = type;
	}

	public List<GameRoom> getRoomList() {
		return RoomList;
	}

	public void setRoomList(List<GameRoom> roomList) {
		RoomList = roomList;
	}
	

}
